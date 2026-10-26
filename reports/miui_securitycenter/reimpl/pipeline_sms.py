#!/usr/bin/env python3
"""End-to-end SMS ingestion pipeline for the MIUI Security Center legacy filter (P1).

Raw SMS text (body + optional sender) is pushed through the same stages the APK
runs before scoring, then through the real 5-stage log-linear cascade
(`models/assets_model`, implemented in `reimpl/model.py`):

    ah.b.<init>        sender category `dh.a.d(number)` (+ "+86" strip)
    ah.h.c             body normaliser (trailing trim, separator split,
                       de-obfuscation of separator-laced pieces)
    dh.e.e             rule stages in file order: dh.j (URL), dh.b (e-mail),
                       dh.c (numeric structures); each rewrites the piece list
                       and OR-increments its rule counters (9 features total)
    dh.e.f -> dh.e.a   dictionary segmentation `bh.a.c` over `assets/dict`:
                       keep a segment iff all-CJK (ah.i.a) or in-dict (bh.a.f);
                       collect into a TreeSet -> the model token list
    ah.e.f / ah.e.a    32-bit flag word: bit0 = URL heuristic `ah.i.b` over the
                       pieces, bit1 = phish list (asset absent -> never),
                       plus `assets/pattern` features (ch.d) -> pattern word
    ah.d.h             early exits on the pattern word, then per stage:
                       feature mask (9 rules + stage char set + length bit),
                       token hits, substring-pattern hits, sender-category bias,
                       shifted thresholds (`ah.e.g` <- ch.d.a/ch.d.b), 5-stage
                       cascade with majority vote
    ah.a.a             verdict 0 allow / 2 spam / 1 phishing (phish bit)
    SmartSmsFilterPolicy.handleData / policy.a.C0115a
                       block type 8 (phishing) or 4 (spam)

Everything above is a direct port of the jadx sources in
`reports/miui_securitycenter/sources/classes/`, except the items listed in the
"EXACT vs INFERRED" block below and in `reimpl/GAP_CLOSURE.md`.

EXACT vs INFERRED
-----------------
exact      bh.a.h  dict loader (header + 4 char-class tables + word table);
           bh.a.c  DAG Viterbi segmentation, including the ASCII-run split and
                   the char-class state machine; bh.a.d/f word lookups;
           ah.h.c, ah.h.a, ah.i.*, dh.a.d, dh.j, dh.b, dh.e.e, dh.e.f,
           dh.i.b, dh.h.b, ch.a/ch.b/ch.c/ch.d/ch.e, ah.e.f, ah.e.a, ah.e.g,
           ah.d.h, ah.a.a, model.py scoring/decoding.
inferred   dh.c.i (numeric-structure classifier): the jadx decompilation is
                   consistent for the separator-branch (types Range/Phone/
                   Phone400) but the non-separator branch is a duplicated-block
                   artifact in which every assignment collapsed to -1.  We
                   implement the decompiled control flow literally, so plain
                   digit runs (e.g. an 11-digit mobile number without spaces or
                   dashes) are not typed.  Consequence: rule features 2..8 stay
                   0 unless the number is grouped by a separator, and the
                   `num_types` half of the pattern features never matches.
                   Also: `ch.b`'s num_types branch carries a jadx "code
                   restructure failed" note; we read it as "no match -> false".
inferred   o2.f.p / o2.f.I sender normalisation (miui.telephony PhoneNumber)
                   is not reimplemented: the sender string is used as supplied,
                   after the ah.b "+86" strip.
stub       ah.f.a  false-positive guard (existing SMS/call history) needs the
                   telephony DB; the pipeline assumes no history -> guard
                   inactive.
stub       ah.d.d  phish asset (`assets/phish`) is not shipped in the APK;
                   returns False exactly like the APK's IOException path.
not run    P2 (`1.bin`/`2.bin`/`v.txt` TFLite engine, downloaded at runtime),
           P3 (resource download), P4 (cloud URL check).

Usage
-----
    pipeline_sms.py --demo                       # spam + ham examples
    pipeline_sms.py --body TEXT [--sender ADDR]  # one message
    pipeline_sms.py --sender 10086 --body TEXT --json
"""
from __future__ import annotations

import argparse
import json
import math
import re
import struct
import sys
from pathlib import Path

import numpy as np

ROOT = Path(__file__).resolve().parent.parent
sys.path.insert(0, str(ROOT / "ir"))
sys.path.insert(0, str(ROOT / "reimpl"))

import parse_dict  # noqa: E402
import model as smodel  # noqa: E402

DICT_PATH = ROOT / "models" / "assets_dict"
MODEL_PATH = ROOT / "models" / "assets_model"
PATTERN_PATH = ROOT / "models" / "assets_pattern"
PHISH_PATH = ROOT / "models" / "assets_phish"  # not shipped

NEG = -3.14e100  # bh.a "impossible" score sentinel

# ---------------------------------------------------------------------------
# generic helpers
# ---------------------------------------------------------------------------


def utf16_units(s: str) -> list[int]:
    """Java `String` code units (astral chars become a surrogate pair)."""
    out: list[int] = []
    for ch in s:
        o = ord(ch)
        if o > 0xFFFF:
            o -= 0x10000
            out.append(0xD800 + (o >> 10))
            out.append(0xDC00 + (o & 0x3FF))
        else:
            out.append(o)
    return out


def java_len(s: str) -> int:
    """`String.length()`."""
    return len(s.encode("utf-16-le", "surrogatepass")) // 2


def java_str(s: str) -> str:
    """Python str whose characters are Java code units (surrogates allowed)."""
    return "".join(chr(u) for u in utf16_units(s))


def f32(x: float) -> float:
    return float(np.float32(x))


# ---------------------------------------------------------------------------
# bh.a - assets/dict trie: char-class tables + word table + DAG segmentation
# ---------------------------------------------------------------------------


class DictTrie:
    """`bh.a` loaded from `assets/dict`.

    The header and the word table come from `ir/parse_dict.py`; the four
    char-class tables that `bh.a.e` binary-searches are parsed here from the
    same bytes (they sit between the header and the word table and
    `parse_dict` reports only their record counts).
    """

    def __init__(self, path: Path):
        raw = path.read_bytes()
        info = parse_dict.parse(str(path))
        self.info = {k: v for k, v in info.items() if not k.startswith("_")}
        self.char_w = info["char_weights"]          # 4 doubles  (bh.a.f5605a)
        self.trans = info["transition"]             # 4x4 doubles (f5606b)
        self.n_lengths = info["n_lengths"]          # f5609e
        self.total_freq = info["total_freq"]        # f5612h
        self.min_freq = info["min_freq"]            # f5613i

        # --- char-class tables: 4 groups of (u16 char, f32 weight) -----------
        off = info["header_end"]
        self.class_tab: list[dict[int, float]] = []
        for count in info["prefix_groups"]:
            tab: dict[int, float] = {}
            for _ in range(count):
                ch, w = struct.unpack_from(">Hf", raw, off)
                off += 6
                tab[ch] = float(w)
            self.class_tab.append(tab)
        assert off == info["header_end"] + info["prefix_bytes"]
        # the words table is the remainder; parse_dict already decoded it
        assert off + sum(
            c * (2 * (j + 1) + 4) for j, c in enumerate(info["counts"])
        ) == info["size"]

        # --- word table, grouped by length (bh.a.d binary-searches each) -----
        self.words: list[dict[str, int]] = [{} for _ in range(self.n_lengths)]
        for w, f in info["_words"]:
            self.words[len(w) - 1][w] = f

    # --- bh.a.e(char class, char) --------------------------------------------
    def class_weight(self, cls: int, ch: str) -> float:
        return self.class_tab[cls].get(ord(ch), NEG)

    # --- bh.a.d(char[], off, len): word frequency (0 = absent) --------------
    def freq(self, chars: list[str], start: int, length: int) -> int:
        if length <= 0 or length > self.n_lengths:
            return 0
        word = "".join(chars[start:start + length])
        return self.words[length - 1].get(word, 0)

    # --- bh.a.f(String) ------------------------------------------------------
    def contains(self, word: str) -> bool:
        chars = list(word)
        return self.freq(chars, 0, len(chars)) > 0

    # --- bh.a.b(char[], lo, hi): char-class Viterbi over a non-ASCII run -----
    def _run(self, chars: list[str], lo: int, hi: int) -> list[str]:
        n = hi - lo
        score = [[0.0] * 4 for _ in range(n)]
        back = [[0] * 4 for _ in range(n)]
        for k in range(4):
            score[0][k] = self.char_w[k] + self.class_weight(k, chars[lo])
        for pos in range(1, n):
            c = chars[lo + pos]
            for k in range(4):
                best = NEG
                best_p = 0
                for p in range(4):
                    v = score[pos - 1][p] + self.trans[p][k]
                    if v > best:
                        best_p = p
                        best = v
                ek = self.class_weight(k, c)
                if best == NEG or ek == NEG:
                    back[pos][k] = 1
                score[pos][k] = best + ek
        state = [0] * n
        state[n - 1] = 1 if score[n - 1][1] > score[n - 1][3] else 3
        for pos in range(n - 2, -1, -1):
            state[pos] = back[pos + 1][state[pos + 1]]

        row0 = (1, 0, 0, 0)
        row1 = (2, 0, 1, 2)
        out: list[str] = []
        i = 0
        while i < n:
            s = row0[state[i]]          # 1 only for state 0
            j = i + 1
            while j < n and s == 1:
                s = row1[state[j]]
                j += 1
            if s == 0:
                out.append("".join(chars[lo + i:lo + j]))
                i = j
            else:
                if j >= n:
                    out.extend(chars[lo + i:lo + n])
                    break
                last = j - 1
                while i < last:
                    out.append(chars[lo + i])
                    i += 1
                i = last
        return out

    # --- bh.a.c(String) ------------------------------------------------------
    def segment(self, text: str) -> list[str]:
        chars = list(text)
        n = len(chars)
        best = [0.0] * (n + 1)
        nxt = [i + 1 for i in range(n + 1)]
        # Math.log((minFreq * 1.0f) / totalFreq) - float division in the APK
        log_unk = math.log(f32(self.min_freq) / f32(self.total_freq))
        for i in range(n - 1, -1, -1):
            best[i] = 2.0 * log_unk + best[i + 1]
            for j in range(i + 1, n + 1):
                f = self.freq(chars, i, j - i)
                if f == 0:
                    continue
                v = math.log(f32(f) / f32(self.total_freq)) + best[j]
                if v > best[i]:
                    nxt[i] = j
                    best[i] = v
        out: list[str] = []
        i = 0
        while i < n:
            j = nxt[i]
            if j == i + 1:
                run_end = i
                while run_end < n and nxt[run_end] == run_end + 1:
                    run_end += 1
                while i < run_end:
                    a = i
                    while a < run_end and not (0 <= ord(chars[a]) < 128):
                        a += 1
                    if i < a:
                        out.extend(self._run(chars, i, a))
                    i = a
                    while i < run_end and 0 <= ord(chars[i]) < 128:
                        i += 1
                    if a < i and a < run_end:
                        out.append("".join(chars[a:i]))
                i = run_end
            else:
                out.append("".join(chars[i:j]))
                i = nxt[i]
        return out


# ---------------------------------------------------------------------------
# ah.i - character predicates
# ---------------------------------------------------------------------------


def i_f(ch: str) -> bool:
    o = ord(ch)
    return (0x4E00 <= o <= 0x9FFF or 0x30 <= o <= 0x39 or 0x61 <= o <= 0x7A
            or 0x41 <= o <= 0x5A or 0xFF10 <= o <= 0xFF19)


def i_d(ch: str) -> bool:
    return 0x4E00 <= ord(ch) <= 0x9FFF


def i_e(ch: str) -> bool:
    return i_f(ch) or ch == "." or ch == " "


def i_a(s: str) -> bool:
    """All-CJK (`ah.i.a`)."""
    return all(i_d(c) for c in s)


def i_b(pieces: list[str]) -> bool:
    """`ah.i.b` - URL-ish heuristic over the piece list."""
    cjk: list[str] = []
    other: list[str] = []
    total = 0
    for piece in pieces:
        total += len(piece)
        f = i_e(piece[0])
        start = 0
        for k in range(1, len(piece)):
            fk = i_e(piece[k])
            if f != fk:
                (cjk if f else other).append(piece[start:k])
                f = fk
                start = k
        (cjk if f else other).append(piece[start:])

    longest = 0
    long_cjk = 0
    cjk_len = 0
    for s in cjk:
        longest = max(longest, len(s))
        if len(s) > 3:
            long_cjk += 1
        cjk_len += len(s)
    other_gt1 = sum(1 for s in other if len(s) > 1)
    return ((len(cjk) > total // 4 or cjk_len * 3 < total)
            and long_cjk <= 1 and longest < 8 and other_gt1 * 2 > len(other))


# ---------------------------------------------------------------------------
# ah.h - body normaliser
# ---------------------------------------------------------------------------


AH_H_B = {",", ".", ";", ":", "!", "\uff0c", "\u3002", "\uff1b", "\u3001",
          "\uff1a", "\uff01"}


def ah_h_a(piece: str) -> str | None:
    """De-obfuscation: keep the alnum/CJK chars of a separator-laced piece."""
    kept: list[str] = []
    isolated = 0
    content = 0
    for k, ch in enumerate(piece):
        if i_f(ch):
            content += 1
            kept.append(ch)
            prev_ok = k == 0 or not i_f(piece[k - 1])
            next_ok = k == len(piece) - 1 or not i_f(piece[k + 1])
            if prev_ok and next_ok:
                isolated += 1
    if content == 0:
        return None
    if isolated >= 3 and isolated / content >= 0.5:
        return "".join(kept)
    return None


def ah_h_c(text: str) -> str:
    """`ah.h.c(body)`."""
    end = len(text) - 1
    while end >= 0 and text[end] in ("\x00", "\n", " ", "\t"):
        end -= 1
    s = text[:end + 1]
    pieces: list[str] = []
    seps: list[str] = []
    start = 0
    k = 0
    while k < len(s):
        if s[k] in AH_H_B:
            pieces.append(s[start:k])
            seps.append(s[k])
            start = k + 1
        k += 1
    if start < k:
        pieces.append(s[start:k])
    changed = False
    for idx in range(len(pieces)):
        rep = ah_h_a(pieces[idx])
        if rep is not None:
            pieces[idx] = rep
            changed = True
    if not changed:
        return s
    out = []
    for idx, sep in enumerate(seps):
        out.append(pieces[idx])
        out.append(sep)
    if len(seps) < len(pieces):
        out.append(pieces[-1])
    return "".join(out)


# ---------------------------------------------------------------------------
# dh.a - sender category
# ---------------------------------------------------------------------------

CATEGORY_NAMES = ("Common", "100+5", "106Short", "106Common", "106Long",
                  "9Pre5", "Other", "Null")


def dh_a_d(s: str | None) -> int:
    """`dh.a.d(number)` -> 0..7."""
    if not s:
        return 7
    if s[0] != "1" or len(s) < 5:
        return 5 if (s[0] == "9" and len(s) == 5) else 6
    c = s[1]
    if len(s) == 11 and (c in "358" or (c == "4" and s[2] == "7")):
        return 0
    if c != "0":
        return 6
    if len(s) == 5 and s[2] == "0":
        return 1
    if s[2] != "6":
        return 6
    if len(s) < 9:
        return 2
    return 3 if len(s) < 12 else 4


# ---------------------------------------------------------------------------
# dh.j / dh.b / dh.c - rule stages
# ---------------------------------------------------------------------------

# Java `[[A-Za-z0-9_]-_]` unions the nested class with '-' (literal after a
# nested class); translated to an explicit Python class of the same width.
RE_URL = re.compile(
    r"((https?|ftp|file)://)?(?<![@|A-Za-z0-9_])([A-Za-z0-9_-]+[.])+"
    r"([a-zA-Z]+)(:[1-9]\d*)?([/][A-Za-z0-9_+&#%?=.~|!]*)*",
    re.IGNORECASE | re.ASCII)  # Java Pattern: ASCII-only \d/\w/\s
RE_IP = re.compile(
    r"(((http(s?)|ftp|file):)?//)?"
    r"((25[0-5]|2[0-4]\d|[0-1]?\d?\d)(\.(25[0-5]|2[0-4]\d|[0-1]?\d?\d)){3})"
    r"(:[1-9]\d*)?([/][A-Za-z0-9_+&#%?=.~|!]*)*",
    re.IGNORECASE | re.ASCII)
RE_EMAIL = re.compile(
    r"([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|"
    r"(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)",
    re.ASCII)


def _split_matches(piece: str, pattern: re.Pattern, found: list[str]) -> list[str]:
    """`dh.f` split helper shared by dh.j/dh.b: keep the non-matching parts."""
    out: list[str] = []
    last = 0
    for m in pattern.finditer(piece):
        a, b = m.span()
        if a > last:
            out.append(piece[last:a])
        found.append(piece[a:b])
        last = b
    if last < len(piece) - 1:
        out.append(piece[last:])
    return out


class UrlRule:
    """`dh.j` - URL / domain / IPv4 splitter (rule feature 0)."""

    size = 1

    def __init__(self):
        self.found: list[str] = []

    def reset(self):
        self.found.clear()

    def split(self, pieces: list[str]) -> list[str]:
        out: list[str] = []
        for piece in pieces:
            keep = _split_matches(piece, RE_URL, self.found)
            second: list[str] = []
            for s in keep:
                second.extend(_split_matches(s, RE_IP, self.found))
            out.extend(second)
        return out

    def count(self) -> int:
        return 1 if self.found else 0


class EmailRule:
    """`dh.b` - e-mail splitter (rule feature 1)."""

    size = 1

    def __init__(self):
        self.found: list[str] = []

    def reset(self):
        self.found.clear()

    def split(self, pieces: list[str]) -> list[str]:
        out: list[str] = []
        for piece in pieces:
            out.extend(_split_matches(piece, RE_EMAIL, self.found))
        return out

    def count(self) -> int:
        return 1 if self.found else 0


# --- dh.c literals (extracted verbatim from the decompiled class) -----------
DIGITS_ALPHABETS = ("0123456789", "\uff10\uff11\uff12\uff13\uff14\uff15\uff16"
                    "\uff17\uff18\uff19",
                    "\u96f6\u4e00\u4e8c\u4e09\u56db\u4e94\u516d\u4e03\u516b\u4e5d",
                    "\u96f6\u58f9\u8d30\u53c1\u8086\u4f0d\u9646\u67d2\u634c\u7396")
CIRCLED = ("\u2460\u2461\u2462\u2463\u2464\u2465\u2466\u2467\u2468",
           "\u2488\u2489\u248a\u248b\u248c\u248d\u248e\u248f\u2490",
           "\u24f5\u24f6\u24f7\u24f8\u24f9\u24fa\u24fb\u24fc\u24fd")
CONFUSABLE = (("I|loOBbgqzZ", "11100869922"),
              ("\uff5c\uff4f\uff51\uff4c\uff42\uff2f\uff29\uff22\uff3a\uff5a",
               "1091601822"))
NUM_ALPHABETS = ("0123456789", "\uff10\uff11\uff12\uff13\uff14\uff15\uff16"
                 "\uff17\uff18\uff19",
                 "\u2460\u2461\u2462\u2463\u2464\u2465\u2466\u2467\u2468",
                 "\u2488\u2489\u248a\u248b\u248c\u248d\u248e\u248f\u2490",
                 "\u24f5\u24f6\u24f7\u24f8\u24f9\u24fa\u24fb\u24fc\u24fd",
                 "\u96f6\u4e00\u4e8c\u4e09\u56db\u4e94\u516d\u4e03\u516b\u4e5d",
                 "\u96f6\u58f9\u8d30\u53c1\u8086\u4f0d\u9646\u67d2\u634c\u7396")
NUM_RULE_NAMES = ("BankCard", "Phone", "PhoneMobile", "Phone400", "Range",
                  "Tag", "Confus")
_L = ",-\u2014\u4e00~ \u3000"    # dh.c.l() literal


def dc_g(ch: str) -> str:
    for alphabet in DIGITS_ALPHABETS:
        k = alphabet.find(ch)
        if k != -1:
            return "0123456789"[k]
    for alphabet in CIRCLED:
        k = alphabet.find(ch)
        if k != -1:
            return "0123456789"[k + 1]
    for src, dst in CONFUSABLE:
        k = src.find(ch)
        if k != -1:
            return dst[k]
    return ch


def dc_h(s: str) -> str:
    return "".join(dc_g(c) for c in s if not dc_l(c))


def dc_l(ch: str) -> bool:
    return ch in _L or ch in ("\n", "\t", "\r")


def dc_m(ch: str) -> bool:
    return ch in ("\uff1a", ":") or ch == "."


def dc_j(ch: str) -> int:
    for k, alphabet in enumerate(NUM_ALPHABETS):
        if ch in alphabet:
            return k
    return -1


def dc_k(ch: str) -> bool:
    return ch in CONFUSABLE[0][0] or ch in CONFUSABLE[1][0]


def dc_q(ch: str) -> bool:
    return dc_j(ch) >= 0 or dc_k(ch)


def dc_p(ch: str) -> bool:
    return (dc_q(ch) and ch not in DIGITS_ALPHABETS[2]
            and ch not in DIGITS_ALPHABETS[3])


def dc_r(ch: str) -> int:
    return 1 if ch in DIGITS_ALPHABETS[1] else 0


def dc_n(s: str) -> bool:
    """All digits belong to one alphabet (disregarding separators/dots)."""
    if not s:
        return False
    first = dc_j(s[0])
    if first < 0:
        return False
    for ch in s[1:]:
        if dc_j(ch) != first and not dc_l(ch) and not dc_m(ch):
            return False
    return True


def dc_s(s: str) -> bool:
    """`dh.c.s` - at most one dot (`indexOf('.') == lastIndexOf('.')`)."""
    return s.count(".") <= 1


class NumberRule:
    """`dh.c` - numeric-structure splitter and 7 counters (rule features 2..8).

    The classifier `dh.c.i` is ported as decompiled; see the module docstring
    for the one region the decompiler destroyed.
    """

    size = 7

    def __init__(self):
        self.counts = [0] * 7
        self.structs: list[tuple[str, int]] = []

    def reset(self):
        self.counts = [0] * 7
        self.structs.clear()

    def split(self, pieces: list[str]) -> list[str]:
        out: list[str] = []
        for piece in pieces:
            out.extend(self._split_one(piece))
        return out

    # --- dh.c.d(String) ------------------------------------------------------
    def _split_one(self, s: str) -> list[str]:
        pieces: list[str] = []
        sep_ch = "0"
        dot_ch = "."
        run = -1
        tail = 0
        full_width = 0
        has_sep = False
        sep_pos = -1
        has_dot = False
        last_digit = -1
        k = 0
        while k < len(s):
            ch = s[k]
            if not dc_q(ch) or ch == "\u4e00":
                if run >= 0:
                    if dc_l(ch) and not has_sep:
                        sep_ch, sep_pos, has_sep = ch, k, True
                    elif dc_m(ch) and not has_dot:
                        dot_ch, has_dot = ch, True
                    elif ch != sep_ch and ch != dot_ch:
                        if run > tail:
                            pieces.append(s[tail:run])
                        end = last_digit + 1
                        sub = s[run:end]
                        if has_sep:
                            has_sep = sep_pos < last_digit
                        self._classify(sub, full_width, has_sep, sep_ch,
                                       has_dot, dot_ch)
                        tail = end
                        run = -1
                        full_width = 0
                        has_sep = False
                        sep_pos = -1
                        has_dot = False
            elif run >= 0:
                if dc_p(ch):
                    last_digit = k
            elif dc_p(ch):
                full_width = dc_r(ch)
                run = k
                last_digit = k
            k += 1
        if run >= 0:
            if run > tail:
                pieces.append(s[tail:run])
            end = last_digit + 1
            if end < len(s):
                pieces.append(s[end:])
            self._classify(s[run:end], full_width, has_sep, sep_ch, has_dot,
                           dot_ch)
        else:
            pieces.append(s[tail:])
        return pieces

    # --- dh.c.i(String, boolean, boolean, char, boolean, char) --------------
    def _classify(self, run: str, full_width: int, has_sep: bool, sep_ch: str,
                  has_dot: bool, dot_ch: str) -> int:
        if len(run) <= 2:
            return -1
        confused = not dc_n(run)
        parts: list[str] = []
        typ = 4
        if has_sep:
            start = 0
            prev_sep = False
            for k, ch in enumerate(run):
                if ch == sep_ch:
                    if not prev_sep:
                        parts.append(run[start:k])
                        prev_sep = True
                    start = k + 1
                else:
                    prev_sep = False
            parts.append(run[start:])
            parts = [dc_h(p) for p in parts]
            if sep_ch in "-\u2014\u4e00~" and len(parts) == 2:
                if (parts[0][:1] == "0" and not has_dot
                        and len(parts[0]) in (3, 4)
                        and len(parts[1]) in (7, 8)):
                    typ = 1
                else:
                    a, b = parts[0], parts[1]
                    if has_dot and dot_ch != ".":
                        a = a.replace(dot_ch, ".")
                        b = b.replace(dot_ch, ".")
                    # Double.valueOf(a) < Double.valueOf(b); a parse failure
                    # propagates like NumberFormatException (-> verdict 0)
                    if dc_s(a) and dc_s(b) and float(a) < float(b):
                        if parts[0] == "100" and len(b) == 7 and not has_dot:
                            typ = 3
            self._hit(run, typ, confused, full_width)
            return typ

        # no separator: the jadx decompilation collapsed every branch of the
        # length chain to -1 (duplicated-block artifact) -> no type.
        typ = -1
        self._hit(run, typ, confused, full_width)
        return typ

    def _hit(self, sub: str, typ: int, confused: bool, full_width: int) -> None:
        if typ < 0:
            return
        self.counts[typ] += 1
        if confused or full_width != 0:
            self.counts[6] += 1
        self.structs.append((sub, typ))


# ---------------------------------------------------------------------------
# ch.* - assets/pattern features
# ---------------------------------------------------------------------------


def load_pattern_json(path: Path) -> dict:
    """Tolerant parse of the org.json-lenient asset (bare keys, 0x ints)."""
    text = path.read_text(encoding="utf-8")
    text = re.sub(r'([{,]\s*)([A-Za-z_][A-Za-z0-9_]*)\s*:',
                  r'\1"\2":', text)
    text = re.sub(r'\b0[xX]([0-9a-fA-F]+)\b',
                  lambda m: str(int(m.group(1), 16)), text)
    return json.loads(text)


class PatternMatcher:
    """`ch.e` pattern/neg_pattern/or_pattern trio."""

    def __init__(self, obj: dict):
        # ch.e compiles with Pattern.CASE_INSENSITIVE; Java's \d/\w/\s are
        # ASCII-only, so re.ASCII is required for the same matching.
        self.patterns = [re.compile(p, re.IGNORECASE | re.ASCII)
                         for p in obj.get("pattern", [])]
        self.neg = [re.compile(p, re.IGNORECASE | re.ASCII)
                    for p in obj.get("neg_pattern", [])]
        self.ors = [re.compile(p, re.IGNORECASE | re.ASCII)
                    for p in obj.get("or_pattern", [])]

    def match(self, s: str) -> bool:
        for p in self.patterns:
            if not p.search(s):
                return False
        for p in self.neg:
            if p.search(s):
                return False
        if not self.ors:
            return True
        return any(p.search(s) for p in self.ors)


class AddressRule:
    """`ch.a`."""

    def __init__(self, obj: dict):
        self.types = obj.get("type")
        self.base = PatternMatcher(obj)

    def match(self, category: int, number: str) -> bool:
        if self.types is not None and self.types:
            if category not in self.types:
                return False
        return self.base.match(number)


class BodyRule:
    """`ch.b`."""

    def __init__(self, obj: dict):
        self.lens = []
        for spec in obj.get("body_len", []):
            a, _, b = spec.partition("-")
            if b:
                self.lens.append((int(a), int(b)))
        self.num_types = obj.get("num_types")
        self.struct_types = obj.get("struct_types")
        self.base = PatternMatcher(obj)

    def match(self, body: str, structs, rule_flags: int) -> bool:
        n = java_len(body)
        if self.lens and not any(a <= n <= b for a, b in self.lens):
            return False
        if self.num_types:
            if not structs:
                return False
            if not any(t in self.num_types for _, t in structs):
                return False
        if self.struct_types:
            if not any(t & rule_flags for t in self.struct_types):
                return False
        return self.base.match(body)


class PatternFeature:
    """`ch.c` - one `assets/pattern` feature."""

    def __init__(self, obj: dict, table: dict[str, "PatternFeature"]):
        self.name = obj.get("name")
        self.sms_type = int(obj.get("sms_type", 0))
        self.assist = bool(obj.get("assist", False))
        self.address = AddressRule(obj["address"]) if "address" in obj else None
        self.body = BodyRule(obj["body"]) if "body" in obj else None
        self.combi = [c.split("&") for c in obj.get("combi", [])]
        self.table = table

    def match(self, msg) -> bool:
        if self.address is not None and not self.address.match(
                msg.category, msg.number):
            return False
        if self.body is not None and not self.body.match(
                msg.body, msg.structs, msg.rule_flags):
            return False
        if not self.combi:
            return True
        for group in self.combi:
            if all(self.table[n].match(msg) for n in group):
                return True
        return False


def load_pattern_features(path: Path) -> list[PatternFeature]:
    data = load_pattern_json(path)
    table: dict[str, PatternFeature] = {}
    feats = []
    for obj in data["features"]:
        if "sms_type" not in obj and "assist" not in obj:
            continue
        f = PatternFeature(obj, table)
        # ch.c.c() rejects features with no address/body/combi matcher
        if not (f.address or f.body or f.combi):
            continue
        if f.name:
            table[f.name] = f
        feats.append(f)
    return feats


# ch.d.a / ch.d.b - active/inactive weighted bit counts of the pattern word
USER_MASK = 0x0000FFFF  # miuix MenuBuilder.USER_MASK (decompiled: 65535)
INACTIVE_MASK = 0x0FFF0000
LIVE_MASK = 0x0FFFFFFF


def ch_d_a(word: int) -> int:
    v = (word & USER_MASK) >> 4
    total = 0
    weight = 12
    while v:
        if v & 1:
            total += weight
        v >>= 1
        weight -= 1
    return total


def ch_d_b(word: int) -> int:
    v = (word & INACTIVE_MASK) >> 16
    total = 0
    weight = 1
    while v:
        if v & 1:
            total += weight
        v >>= 1
        weight += 1
    return total


BUFFER_FLAG_FIRST_SAMPLE = 0x08000000  # com.google.android.exoplayer2.C


def shift_thresholds(thr: np.ndarray, word: int) -> np.ndarray:
    """`ah.e.g(double[], ah.b)`."""
    a, b = ch_d_a(word), ch_d_b(word)
    if (word & LIVE_MASK) == 0 or a == b:
        return thr
    return thr + ((a - b) * (thr[0] - thr[1])) / 4.0


# ---------------------------------------------------------------------------
# the pipeline
# ---------------------------------------------------------------------------


class SmsMessage:
    """`ah.b` - one message, its pieces, rule counters, tokens and flags."""

    def __init__(self, sender: str, body: str):
        number = sender or ""
        if len(number) > 3 and number.startswith("+86"):
            number = number[3:]
        self.number = number                    # f505h
        self.category = dh_a_d(number)          # f506i
        self.body = ah_h_c(body)                # f501d
        self.pieces: list[str] = [self.body]    # f500c
        self.rule_counts: list[int] = []        # f503f (9)
        self.tokens: list[str] = []             # f499b
        self.rule_flags = 0                     # f512o (url/phish bits)
        self.pattern_word = 0                   # f513p
        self.structs: list[tuple[str, int]] = []  # f511n


class SmsPipeline:
    def __init__(self, dict_path: Path = DICT_PATH,
                 model_path: Path = MODEL_PATH,
                 pattern_path: Path = PATTERN_PATH):
        self.dict = DictTrie(dict_path)
        self.stages = smodel.load_stages(model_path)
        self.params = smodel.init_params(self.stages)
        self.patterns = load_pattern_features(pattern_path)
        self.url_rule = UrlRule()
        self.mail_rule = EmailRule()
        self.num_rule = NumberRule()
        self.rule_stages = (self.url_rule, self.mail_rule, self.num_rule)
        self.rule_offsets: list[int] = []
        off = 0
        for st in self.rule_stages:
            self.rule_offsets.append(off)
            off += st.size
        self.n_rules = off

    # --- dh.e.e (rule stages) + dh.e.a (token collection) -------------------
    def prepare(self, msg: SmsMessage) -> None:
        pieces = [msg.body]
        counts = [0] * self.n_rules
        for stage, off in zip(self.rule_stages, self.rule_offsets):
            stage.reset()
            pieces = stage.split(pieces)
            if stage is self.num_rule:
                # dh.c.b() copies its 7 raw counters into the vector
                counts[off:off + 7] = list(stage.counts)
            else:
                counts[off] = stage.count()
        msg.pieces = pieces
        msg.rule_counts = counts
        msg.structs = list(self.num_rule.structs)

        tokens: set[str] = set()
        for piece in pieces:
            for seg in self.dict.segment(piece):
                if i_a(seg) or self.dict.contains(seg):
                    tokens.add(seg)
        msg.tokens = sorted(tokens)

    # --- ah.e.f -------------------------------------------------------------
    def rule_bits(self, msg: SmsMessage) -> None:
        flags = 0
        if i_b(msg.pieces):
            flags |= 1
        # ah.d.d: phish list needs dh.j hits and the absent "phish" asset
        if self.url_rule.found and PHISH_PATH.exists():
            flags |= 2
        msg.rule_flags = flags

    # --- ah.e.a -------------------------------------------------------------
    def pattern_bits(self, msg: SmsMessage) -> list[str]:
        word = 0
        names: list[str] = []
        for feat in self.patterns:
            if feat.assist:
                continue
            if feat.match(msg):
                word |= feat.sms_type
                names.append(feat.name)
        msg.pattern_word = word
        return names

    # --- ah.d.h -------------------------------------------------------------
    def score(self, msg: SmsMessage) -> dict:
        self.rule_bits(msg)
        pattern_names = self.pattern_bits(msg)
        text = java_str(msg.body)
        rules_present = [c > 0 for c in msg.rule_counts]
        out = {
            "body": msg.body,
            "number": msg.number,
            "category": msg.category,
            "category_name": CATEGORY_NAMES[msg.category],
            "pieces": msg.pieces,
            "urls": self.url_rule.found,
            "structs": [[s, t] for s, t in msg.structs],
            "rule_counts": msg.rule_counts,
            "tokens": msg.tokens,
            "pattern_word": f"0x{msg.pattern_word:08x}",
            "pattern_features": pattern_names,
            "active_bits": ch_d_a(msg.pattern_word),
            "inactive_bits": ch_d_b(msg.pattern_word),
        }
        if msg.pattern_word & 1:
            out.update(verdict=0, early_exit="pattern bit0 (allow)",
                       blocked=False, block_type=None, should_filter=0,
                       stages=[])
            return out
        if msg.pattern_word & BUFFER_FLAG_FIRST_SAMPLE:
            out.update(verdict=1 if (msg.rule_flags & 2) else 2,
                       early_exit="pattern 0x08000000 (block)",
                       blocked=True, block_type=4, should_filter=2, stages=[])
            return out

        trace = []
        votes = []
        pos = neg = 0
        verdict = None
        for i, st in enumerate(self.stages):
            mask, tok_hit, pat_hit = smodel.build_masks(
                st, text, msg.tokens, rules_present)
            s = smodel.stage_scores(self.params, i, smodel.jnp.asarray(mask),
                                    smodel.jnp.asarray(tok_hit),
                                    smodel.jnp.asarray(pat_hit), msg.category)
            s = np.asarray(s, dtype=np.float64)
            thr = shift_thresholds(np.array(st["thr"], dtype=np.float64),
                                   msg.pattern_word)
            d = float(s[0] - s[1])
            rec = {
                "stage": i,
                "scores": [round(float(s[0]), 6), round(float(s[1]), 6)],
                "d": round(d, 6),
                "thresholds": [round(float(x), 6) for x in thr],
                "feature_bits": int(mask.sum()),
                "char_hits": int(mask[self.n_rules:self.n_rules
                                      + len(st["chars"])].sum()),
                "length_bit": bool(mask[-1]),
                "token_hits": int(tok_hit.sum()),
                "pattern_hits": int(pat_hit.sum()),
            }
            if d < thr[1]:
                rec["decision"] = 0
                trace.append(rec)
                verdict = 0
                break
            if d > thr[0]:
                rec["decision"] = 1
                trace.append(rec)
                verdict = 1
                break
            rec["decision"] = None
            vote = d - float(thr[2])
            rec["vote"] = round(vote, 6)
            votes.append(vote)
            trace.append(rec)
        if verdict is None:
            pos = sum(1 for v in votes if v > 0)
            neg = len(votes) - pos
            verdict = 1 if pos > neg else 0
        out.update(stages=trace, votes=[round(v, 6) for v in votes],
                   pos=pos, neg=neg, early_exit=None)
        blocked = verdict == 1
        raw = 0
        if blocked:
            raw = 1 if (msg.rule_flags & 2) else 2
        out.update(verdict=raw, blocked=blocked,
                   block_type=4 if blocked else None,
                   should_filter=2 if blocked else 0)
        return out


# ---------------------------------------------------------------------------
# self-test
# ---------------------------------------------------------------------------


def selftest(pipe: SmsPipeline) -> int:
    checks: list[tuple[str, bool, str]] = []

    def chk(name: str, ok: bool, detail: str = "") -> None:
        checks.append((name, bool(ok), detail))

    info = pipe.dict.info
    chk("dict parses to EOF", info["exact"],
        f"consumed={info['consumed']} size={info['size']}")
    chk("dict word count", info["words"] == 348982, f"words={info['words']}")
    chk("dict sha256", info["sha256"].startswith("f9908624"),
        info["sha256"][:16])
    chk("char-class tables", [len(t) for t in pipe.dict.class_tab]
        == info["prefix_groups"], f"{[len(t) for t in pipe.dict.class_tab]}")
    chk("model stages/rules", len(pipe.stages) == 5 and pipe.n_rules == 9,
        f"stages={len(pipe.stages)} rules={pipe.n_rules}")
    chk("pattern features", len(pipe.patterns) == 19,
        f"features={len(pipe.patterns)}")

    # --- bh.a.c: segmentation is a partition and finds dictionary words -----
    for s in ("晚上七点老地方见，别迟到", "【京东】尊敬的用户，恭喜您中奖！点击 ",
              "混合abc中文def测试", "hello world 123"):
        seg = pipe.dict.segment(s)
        chk(f"segment partitions {s[:8]!r}", "".join(seg) == s, str(seg))
    seg = pipe.dict.segment("【京东】尊敬的用户，恭喜您中奖！点击 ")
    chk("segment word 京东/中奖/尊敬",
        {"京东", "中奖", "尊敬"} <= set(seg), str(seg))
    chk("bh.a.f word lookup",
        pipe.dict.contains("中奖") and not pipe.dict.contains("qqqqzzz"),
        "中奖 in dict; qqqqzzz absent")
    chk("ascii/non-ascii split",
        pipe.dict.segment("混合abc中文def测试")
        == ["混合", "abc", "中文", "def", "测试"],
        str(pipe.dict.segment("混合abc中文def测试")))

    # --- ah.h.c -------------------------------------------------------------
    chk("ah.h.c de-obfuscation", ah_h_c("点 击 领 取") == "点击领取",
        repr(ah_h_c("点 击 领 取")))
    chk("ah.h.c trailing trim", ah_h_c("hello   ") == "hello",
        repr(ah_h_c("hello   ")))
    chk("ah.h.c passthrough", ah_h_c("正文。") == "正文。", repr(ah_h_c("正文。")))

    # --- dh.a.d / +86 strip -------------------------------------------------
    cats = {n: dh_a_d(n) for n in ("13800138000", "10086", "95555", "10690000")}
    chk("dh.a.d categories", cats == {"13800138000": 0, "10086": 1,
                                      "95555": 5, "10690000": 2}, str(cats))
    chk("+86 strip", SmsMessage("+8610086", "").number == "10086",
        SmsMessage("+8610086", "").number)

    # --- dh.j / dh.b / dh.c -------------------------------------------------
    chk("dh.j url split", pipe.url_rule.split(["见 http://t.cn/abc 领取"])
        == ["见 ", " 领取"], str(pipe.url_rule.split(["见 http://t.cn/abc 领取"])))
    chk("dh.b email split", pipe.mail_rule.split(["a@b.com 你好"])
        == [" 你好"], str(pipe.mail_rule.split(["a@b.com 你好"])))
    num = NumberRule()
    cases = {
        "010-12345678": 1,      # Phone (decompiled separator branch)
        "100-1234567": 3,       # Phone400
        "400-123-4567": 4,      # Range (3 parts -> no special case)
        "13800138000": None,    # no separator -> decompiled branch yields -1
    }
    for s, want in cases.items():
        num.reset()
        num.split([s])
        got = next((i for i, c in enumerate(num.counts) if c), None)
        chk(f"dh.c {s}", got == want, f"counts={num.counts}")
    num.reset()
    chk("dh.c digit removal", num.split(["abc123def"]) == ["a", "c", "def"],
        str(num.split(["abc123def"])))

    # --- ah.i.b URL heuristic ----------------------------------------------
    # e() treats CJK, alnum, '.' and ' ' alike, so a chunk only breaks on other
    # punctuation: the heuristic fires on punctuation-heavy / dotted fragments.
    chk("ah.i.b empty", i_b([]) is False, "no pieces -> False")
    chk("ah.i.b punctuation-heavy", i_b(["!!!!!!!!!!", "abcd"]) is True,
        str(i_b(["!!!!!!!!!!", "abcd"])))
    chk("ah.i.b prose", i_b(["晚上七点老地方见，别迟到"]) is False,
        str(i_b(["晚上七点老地方见，别迟到"])))

    # --- ch.d bit accounting / ah.e.g --------------------------------------
    chk("ch.d.a/ch.d.b", (ch_d_a(0x1000), ch_d_b(0x08000000)) == (4, 12),
        f"{ch_d_a(0x1000)} {ch_d_b(0x08000000)}")
    base = np.array(pipe.stages[0]["thr"], dtype=np.float64)
    chk("ah.e.g shift", np.allclose(shift_thresholds(base, 0x1000),
                                    base + (4 * (base[0] - base[1])) / 4.0),
        str(shift_thresholds(base, 0x1000)))
    chk("early-exit bit", BUFFER_FLAG_FIRST_SAMPLE == 0x08000000,
        hex(BUFFER_FLAG_FIRST_SAMPLE))

    # --- UTF-16 length semantics -------------------------------------------
    st0 = pipe.stages[0]
    chk("java length (astral)",
        java_len("\U0001F600" * 20) == 40 and len("\U0001F600" * 20) == 20,
        "emoji = 2 code units")
    m1, _, _ = smodel.build_masks(st0, java_str("\U0001F600" * 20), [])
    m2, _, _ = smodel.build_masks(st0, java_str("中" * st0["len_threshold"]), [])
    m3, _, _ = smodel.build_masks(st0, java_str("中" * (st0["len_threshold"] - 1)), [])
    chk("length feature uses code units", m1[-1] and m2[-1] and not m3[-1],
        f"{m1[-1]} {m2[-1]} {m3[-1]}")

    # --- cascade vs the independent NumPy reference of model.py -------------
    for sender, body, want in DEMO_CASES:
        res = run_one(pipe, body, sender)
        chk(f"verdict {body[:10]!r}", res["verdict"] == want,
            f"got {res['verdict']} want {want}")
        chk(f"block type {body[:10]!r}",
            (res["block_type"] == 4) if want == 2 else res["block_type"] is None,
            f"block_type={res['block_type']} should_filter={res['should_filter']}")
        if not res["stages"]:
            # ah.d.h returns before scoring on the pattern word
            chk(f"pattern early exit {body[:10]!r}",
                res["early_exit"] is not None, str(res["early_exit"]))
            continue
        msg = SmsMessage(sender, body)
        pipe.prepare(msg)
        fb = (ch_d_a(msg.pattern_word), ch_d_b(msg.pattern_word))
        nv = smodel.classify_numpy(
            pipe.stages, java_str(msg.body), msg.tokens, msg.category,
            [c > 0 for c in msg.rule_counts], [fb] * len(pipe.stages))
        same = (len(nv["stages"]) == len(res["stages"])
                and all(abs(a["score_diff"] - b["d"]) < 1e-6
                        for a, b in zip(nv["stages"], res["stages"]))
                and nv["verdict"] == int(res["blocked"]))
        chk(f"numpy agreement {body[:10]!r}", same,
            f"jax={[s['d'] for s in res['stages']]} "
            f"numpy={[round(s['score_diff'], 6) for s in nv['stages']]}")

    width = max(len(n) for n, _, _ in checks)
    for name, ok, detail in checks:
        print(f"  {'PASS' if ok else 'FAIL'}  {name:<{width}}  {detail}")
    failed = [n for n, ok, _ in checks if not ok]
    print(f"\n{len(checks) - len(failed)}/{len(checks)} checks passed"
          + (f"; failed: {failed}" if failed else ""))
    return 0 if not failed else 1


# ---------------------------------------------------------------------------
# CLI
# ---------------------------------------------------------------------------

SPAM_DEMO = ("【京东】尊敬的用户，恭喜您中奖！点击 http://t.cn/Rx3k2f 领取手机一部，"
             "贷款免息，客服 400-123-4567，名额有限速速领取")
SPAM_VOTE_DEMO = ("尊敬的用户，您有一笔免息贷款待领取，额度最高50万元，"
                  "点击 http://t.cn/A6xyz 立即申请")
HAM_DEMO = "晚上七点老地方见，别迟到"
HAM_CASCADE_DEMO = "会议改到下午三点，材料已经发你邮箱了"

# (sender, body, expected ah.a.a verdict) - verdict 2 = blocked as spam
DEMO_CASES = (
    ("13800138000", SPAM_DEMO, 2),
    ("13800138000", SPAM_VOTE_DEMO, 2),
    ("13800138000", HAM_DEMO, 0),
    ("13800138000", HAM_CASCADE_DEMO, 0),
)


def run_one(pipe: SmsPipeline, body: str, sender: str = "") -> dict:
    try:
        msg = SmsMessage(sender, body)
        pipe.prepare(msg)
        res = pipe.score(msg)
    except Exception as exc:  # j2.a.h wraps the whole engine in catch(Exception)
        return {"body": body, "sender": sender, "error": repr(exc),
                "verdict": 0, "blocked": False, "block_type": None,
                "should_filter": 0, "stages": []}
    res["sender"] = sender
    res["provider"] = {
        "uri": "content://antispam/sms_judge",
        "query": "AntiSpamProvider.query() case 9 -> c(String[])",
        "policy": "SmartSmsFilterPolicy.handleData -> policy.a.C0115a"
                  "(type 8 = phishing, 4 = spam)",
    }
    return res


def main(argv: list[str] | None = None) -> int:
    ap = argparse.ArgumentParser(description=__doc__.splitlines()[0])
    ap.add_argument("--body")
    ap.add_argument("--sender", default="")
    ap.add_argument("--demo", action="store_true")
    ap.add_argument("--selftest", action="store_true")
    ap.add_argument("--json", action="store_true")
    a = ap.parse_args(argv)

    pipe = SmsPipeline()
    print(f"dict: {DICT_PATH.name} words={pipe.dict.info['words']} "
          f"sha256={pipe.dict.info['sha256'][:12]} "
          f"min_freq={pipe.dict.min_freq} total_freq={pipe.dict.total_freq}")
    print(f"model: {MODEL_PATH.name} stages={len(pipe.stages)} "
          f"rules={pipe.n_rules} patterns={len(pipe.patterns)}")

    if a.selftest:
        print()
        return selftest(pipe)

    if a.body is not None:
        cases = [(a.sender, a.body, None)]
    else:
        cases = list(DEMO_CASES)

    rc = 0
    results = []
    for sender, body, expect in cases:
        res = run_one(pipe, body, sender)
        results.append(res)
        if a.json:
            print(json.dumps(res, ensure_ascii=False, indent=1))
            continue
        print(f"\n=== sender={sender!r} category={res['category_name']} "
              f"body={body!r}")
        if "error" in res:
            print(f"    error: {res['error']} -> allow")
            continue
        print(f"    pieces={res['pieces']}")
        print(f"    urls={res['urls']}")
        print(f"    rule_counts={dict(zip(['url', 'email'] + list(NUM_RULE_NAMES),
                                        res['rule_counts']))}")
        print(f"    tokens({len(res['tokens'])})={res['tokens'][:20]}")
        print(f"    pattern_word={res['pattern_word']} "
              f"features={res['pattern_features']} "
              f"active={res['active_bits']} inactive={res['inactive_bits']}")
        for st in res["stages"]:
            print(f"    stage {st['stage']}: d={st['d']:+.3f} "
                  f"thr={st['thresholds']} feats={st['feature_bits']} "
                  f"(chars={st['char_hits']}, len={st['length_bit']}) "
                  f"tokens={st['token_hits']} patterns={st['pattern_hits']} -> "
                  f"{'vote' if st['decision'] is None else st['decision']}")
        print(f"    verdict={res['verdict']} blocked={res['blocked']} "
              f"block_type={res['block_type']} should_filter={res['should_filter']}"
              + (f" expected={expect}" if expect is not None else ""))

    if not a.json:
        got = [r["verdict"] for r in results]
        expect = [e for _, _, e in cases if e is not None]
        ok = got[:len(expect)] == expect
        print(f"\nexpect verdicts {expect} got {got} pass={ok}")
        rc = 0 if ok else 1
    return rc


if __name__ == "__main__":
    sys.exit(main())
