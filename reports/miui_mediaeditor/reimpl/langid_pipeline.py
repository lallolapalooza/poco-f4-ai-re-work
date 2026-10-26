#!/usr/bin/env python3
"""Integer featurizer for the ML Kit language-ID TFLite model (NGramHash op).

Everything here is recovered from the model's custom-op options and from
liblanguage_id_l2c_jni.so (the TFLite custom-op kernels shipped in the APK):

NGramHash  (kernel prepare=0x3fb7c, invoke=0x3fc0c, registration @0xfce08)
  * options are a flexbuffer map:
        {"max_splits": 128, "ngram_lengths": [1,2,3,4], "seed": 10911,
         "vocab_sizes": [5500, 5500, 13000, 13000]}
  * the input string is split into at most `max_splits` word tokens (a token is
    a maximal run of alphanumeric codepoints; UTF-8 codepoints are decoded by
    0x48b24 and classified by 0x48e48),
  * for every n in ngram_lengths and every n-gram of codepoints *inside* one
    token the op emits `murmur64a(utf8(ngram), seed) % vocab_sizes[n] + 1`
    (hash function @0x407c8 = MurmurHash64A, r=47; +1 so that 0 stays free for
    padding), flattened over orders and zero padded to the longest order.

KmeansEmbeddingLookup (prepare=0x3ee44, invoke=0x3f2d0)
  * h in 1..V -> row h of c_matrix (UINT8 [V+1, 6]); 6 codes, each indexing one
    row of b_matrix (FLOAT32 [256,4]) -> 24 floats
        emb(h) = concat_{j<6} b_matrix[ c_matrix[h][j] ]
  * the output embedding is the *mean* of emb(h) over the non-zero hashes of
    that order (0 terminates the list).
"""

from __future__ import annotations

import unicodedata

import numpy as np

MASK64 = (1 << 64) - 1
M64 = 0xC6A4A7935BD1E995
R = 47

NGRAM_LENGTHS = (1, 2, 3, 4)
VOCAB_SIZES = (5500, 5500, 13000, 13000)
SEED = 10911
MAX_SPLITS = 128


def murmur64a(data: bytes, seed: int = SEED) -> int:
    """MurmurHash64A (MurmurHash2, 64-bit) - matches 0x407c8 in the APK's .so."""
    n = len(data)
    h = ((n * M64) & MASK64) ^ (seed & MASK64)
    nblocks = n // 8
    for i in range(nblocks):
        k = int.from_bytes(data[8 * i:8 * i + 8], "little")
        k = (k * M64) & MASK64
        k ^= k >> R
        k = (k * M64) & MASK64
        h ^= k
        h = (h * M64) & MASK64
    tail = data[8 * nblocks:]
    if tail:
        h ^= int.from_bytes(tail, "little")
        h = (h * M64) & MASK64
    h ^= h >> R
    h = (h * M64) & MASK64
    h ^= h >> R
    return h


def is_word_char(ch: str) -> bool:
    """Letter or combining mark - mirrors the UnicodeSet in 0x48e48.

    The set at vaddr 0xfd008 (383 ranges, first ranges 0x41-0x5a / 0x61-0x7a /
    0xc0-0xd6 ...) is exactly the letter ranges; the second set at 0xfe4f0 adds
    combining marks (it contains U+093E, the Devanagari vowel sign that the
    letter set does not).  Digits (U+0030-0x39) are in neither set.
    """
    return unicodedata.category(ch)[0] in ("L", "M")


def tokenize(text: str, max_splits: int = MAX_SPLITS) -> list[str]:
    """Maximal runs of word characters (see report.md, 0x40294/0x48e48)."""
    toks, cur = [], []
    for ch in text:
        if is_word_char(ch):
            cur.append(ch)
        elif cur:
            toks.append("".join(cur))
            cur = []
            if len(toks) >= max_splits:
                return toks
    if cur and len(toks) < max_splits:
        toks.append("".join(cur))
    return toks


def ngram_hashes(text: str, seed: int = SEED,
                 vocab_sizes: tuple[int, ...] = VOCAB_SIZES,
                 ngram_lengths: tuple[int, ...] = NGRAM_LENGTHS,
                 max_splits: int = MAX_SPLITS) -> list[list[int]]:
    """Per n-gram order list of hashes (0-free; 0 is the padding sentinel)."""
    toks = tokenize(text, max_splits)
    out: list[list[int]] = []
    for oi, n in enumerate(ngram_lengths):
        vocab = vocab_sizes[oi]
        vals: list[int] = []
        for tok in toks:
            if len(tok) < n:
                continue
            for i in range(len(tok) - n + 1):
                gram = tok[i:i + n].encode("utf-8")
                vals.append(murmur64a(gram, seed) % vocab + 1)
        out.append(vals)
    return out


def featurize(text: str, **kw) -> np.ndarray:
    """Text -> int32 [4, N] hash tensor (N = longest n-gram list, 0 padded)."""
    per_order = ngram_hashes(text, **kw)
    n = max([len(v) for v in per_order] + [1])
    arr = np.zeros((4, n), dtype=np.int32)
    for o, vals in enumerate(per_order):
        arr[o, :len(vals)] = vals
    return arr


if __name__ == "__main__":
    import sys

    t = sys.argv[1] if len(sys.argv) > 1 else "Hello world"
    print("tokens :", tokenize(t))
    h = featurize(t)
    print("hashes :", h.shape, h[:, :8].tolist())
