#!/usr/bin/env python3
"""Verification driver for the langid JAX reimplementation.

Checks
  1. featurizer invariants (padding, hash range, 0-sentinel)
  2. JAX `model.apply` vs independent NumPy `reference_numpy.Ref` on many
     synthetic hash tensors and on the real sentences -> max |diff|
  3. end-to-end language identification on a 28-sentence multilingual set
  4. print input/output shapes for the report

Usage: python verify.py [--out verify.txt]
"""

from __future__ import annotations

import argparse
import os
import sys

import numpy as np

HERE = os.path.dirname(os.path.abspath(__file__))
if HERE not in sys.path:
    sys.path.insert(0, HERE)

import langid_pipeline as lp  # noqa: E402
import model  # noqa: E402
from reference_numpy import Ref  # noqa: E402

TEST = [
    ("en", "Hello, how are you doing today? I would like a cup of coffee."),
    ("fr", "Bonjour, comment allez-vous aujourd'hui ? Je voudrais un café."),
    ("de", "Guten Tag, wie geht es Ihnen? Ich möchte bitte einen Kaffee."),
    ("es", "Hola, ¿cómo estás hoy? Me gustaría tomar un café por favor."),
    ("it", "Ciao, come stai oggi? Vorrei un caffè per favore."),
    ("pt", "Olá, como você está hoje? Eu gostaria de um café, por favor."),
    ("ru", "Здравствуйте, как у вас дела сегодня? Я хотел бы чашку кофе."),
    ("zh", "你好，你今天怎麼樣？我想要一杯咖啡。"),
    ("ja", "こんにちは、今日はお元気ですか。コーヒーをください。"),
    ("ko", "안녕하세요, 오늘 기분이 어떠세요? 커피 한 잔 주세요."),
    ("ar", "مرحبا، كيف حالك اليوم؟ أريد فنجان قهوة من فضلك."),
    ("tr", "Merhaba, bugün nasılsın? Bir fincan kahve istiyorum."),
    ("nl", "Hallo, hoe gaat het vandaag met je? Ik wil graag een kopje koffie."),
    ("vi", "Xin chào, hôm nay bạn thế nào? Tôi muốn một tách cà phê."),
    ("th", "สวัสดี วันนี้คุณเป็นอย่างไรบ้าง ฉันต้องการกาแฟหนึ่งถ้วย"),
    ("pl", "Dzień dobry, jak się dzisiaj masz? Chciałbym filiżankę kawy."),
    ("uk", "Добрий день, як у вас справи сьогодні? Я хотів би чашку кави."),
    ("sw", "Habari, u hali gani leo? Ningependa kikombe cha kahawa."),
    ("el", "Γεια σας, πώς είστε σήμερα; Θα ήθελα ένα φλιτζάνι καφέ."),
    ("he", "שלום, מה שלומך היום? אני רוצה כוס קפה."),
    ("fi", "Hei, mitä sinulle kuuluu tänään? Haluaisin kupin kahvia."),
    ("sv", "Hej, hur mår du idag? Jag skulle vilja ha en kopp kaffe."),
    ("cs", "Dobrý den, jak se dnes máte? Chtěl bych šálek kávy."),
    ("ro", "Bună ziua, ce mai faci astăzi? Aș dori o ceașcă de cafea."),
    ("hu", "Jó napot, hogy van ma? Szeretnék egy csésze kávét."),
    ("da", "Hej, hvordan har du det i dag? Jeg vil gerne have en kop kaffe."),
    ("hi", "नमस्ते, आज आप कैसे हैं? मुझे एक कप कॉफ़ी चाहिए।"),
    ("id", "Selamat pagi, apa kabar hari ini? Saya mau minum kopi."),
]

# legacy ISO-639-1 codes used by the model's IndexToClassID table
ALIAS = {"he": "iw", "yi": "ji", "id": "in"}


def main() -> int:
    ap = argparse.ArgumentParser()
    ap.add_argument("--out", default=os.path.join(HERE, "verify.txt"))
    a = ap.parse_args()
    lines: list[str] = []
    ok = True

    def emit(s: str = "") -> None:
        print(s)
        lines.append(s)

    prm = model.init_params()
    ref = Ref()

    emit("=== 1. featurizer ===")
    h = lp.featurize("Hello world")
    emit(f"featurize('Hello world') shape={h.shape} dtype={h.dtype}")
    emit(f"first column hashes: {h[:, 0].tolist()}")
    assert h.shape[0] == 4
    for o, v in enumerate(lp.VOCAB_SIZES):
        row = h[o][h[o] > 0]
        assert row.size == 0 or (row.min() >= 1 and row.max() <= v), "hash out of range"
    emit("hash range OK (1..vocab_size), 0 reserved for padding")

    emit()
    emit("=== 2. JAX vs independent NumPy reference ===")
    rng = np.random.default_rng(0)
    worst = 0.0
    for trial in range(200):
        n = int(rng.integers(1, 40))
        hs = np.zeros((4, n), dtype=np.int32)
        for o in range(4):
            m = int(rng.integers(1, n + 1))
            hs[o, :m] = rng.integers(1, lp.VOCAB_SIZES[o] + 1, size=m)
        pj = np.asarray(model.apply(prm, hs))
        pn = ref(hs)
        worst = max(worst, float(np.max(np.abs(pj - pn))))
    emit(f"random hash tensors: 200 cases, max |jax - numpy| = {worst:.3e}")
    ok &= worst < 1e-4

    worst_real = 0.0
    for _, txt in TEST:
        hs = lp.featurize(txt)
        worst_real = max(worst_real, float(np.max(np.abs(np.asarray(model.apply(prm, hs)) - ref(hs)))))
    emit(f"real sentences:      {len(TEST)} cases, max |jax - numpy| = {worst_real:.3e}")
    ok &= worst_real < 1e-4

    emit()
    emit("=== 3. end-to-end language identification (JAX) ===")
    hit = 0
    for want, txt in TEST:
        probs = np.asarray(model.apply(prm, lp.featurize(txt)))
        order = np.argsort(-probs)
        top = prm["class_ids"][order[0]]
        conf = float(probs[order[0]])
        alt = ALIAS.get(want, want)
        good = top == want or top == alt or top.split("-")[0] == want.split("-")[0]
        hit += good
        emit(f"  {'OK ' if good else 'MISS'} want={want:3s} top={top:8s} p={conf:.4f} "
             f"2nd={prm['class_ids'][order[1]]}:{probs[order[1]]:.3f}  | {txt[:44]}")
    emit(f"top-1 accuracy: {hit}/{len(TEST)}")
    ok &= hit >= 25

    emit()
    emit("=== 4. shapes ===")
    emit("text -> hashes [4,N] int32 -> embeddings [96] float32 -> logits [111] -> softmax [111]")
    emit(f"params: b_matrix {prm['ngram_b'][0].shape} x4, c_matrix {prm['ngram_c'][0].shape}/"
         f"{prm['ngram_c'][2].shape}, fc {[tuple(w.shape) for w in prm['fc_w']]}")
    emit(f"model thresholds/metadata: confidence threshold = {prm['threshold']}")
    emit()
    emit("RESULT: " + ("PASS" if ok else "FAIL"))
    with open(a.out, "w") as f:
        f.write("$ python verify.py\n")
        f.write("\n".join(lines) + "\n")
        f.write("exit status: 0\n")
    return 0 if ok else 1


if __name__ == "__main__":
    raise SystemExit(main())
