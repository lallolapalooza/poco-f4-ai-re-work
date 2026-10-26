#!/usr/bin/env python3
"""Registry of the 33 usable TFLite models of com.miui.mediaeditor 1.9.0.6.

Every entry in ``models/tflite/*.tflite`` appears exactly once.  An entry either

* points at a JAX reimplementation (``jax`` set) that was produced from the
  real weights with ``tools/tflite2jax.py`` —
    - ``jax="bundles"``: the module already lived in ``reimpl/bundles/<dir>``
      and is reused here instead of being generated twice, or
    - ``jax="generated"``: converted for this runner into
      ``reimpl/model_runners/generated/<name>``;
* or has no JAX module, in which case the runner executes the real ``.tflite``
  weights through the LiteRT reference interpreter (``ai_edge_litert``) and
  records why the conversion was not possible (``no_jax_reason``).

``family`` selects the pre/post-processing contract documented in
``PIPELINE.md`` §3/§5; see ``preprocess.py`` / ``postprocess.py``.

Bundle mapping is from ``PIPELINE.md`` §4.2 and the on-disk file names
(``models/tflite/model__<bundle>__<model>.tflite``).
"""

from __future__ import annotations

import os

HERE = os.path.dirname(os.path.abspath(__file__))
REIMPL = os.path.dirname(HERE)                     # reports/<slug>/reimpl
REPORT = os.path.dirname(REIMPL)                   # reports/<slug>
TFLITE_DIR = os.path.join(REPORT, "models", "tflite")
BUNDLE_ROOT = os.path.join(REIMPL, "bundles")
GENERATED_ROOT = os.path.join(HERE, "generated")

MODEL_PREFIX = "model__"


class Spec:
    """One TFLite model plus the data-path contract around it."""

    __slots__ = (
        "name", "bundle", "family", "jax", "jax_dir", "no_jax_reason",
        "hard_skip", "input_roles", "note",
    )

    def __init__(self, name, bundle, family, jax=None, jax_dir=None,
                 no_jax_reason=None, input_roles=None, note="", hard_skip=False):
        self.name = name                      # canonical file stem
        self.bundle = bundle                  # FuAI bundle (short name)
        self.family = family                  # pre/post-processing family
        self.jax = jax                        # None | "bundles" | "generated"
        self.jax_dir = jax_dir                # absolute when jax is set
        self.no_jax_reason = no_jax_reason
        self.hard_skip = hard_skip
        self.input_roles = input_roles or {}  # input-name -> role override
        self.note = note

    # -- paths ---------------------------------------------------------
    @property
    def tflite_path(self) -> str:
        return os.path.join(TFLITE_DIR, self.name + ".tflite")

    @property
    def short(self) -> str:
        """Model name without the ``model__<bundle>__`` prefix."""
        base = self.name[len(MODEL_PREFIX):] if self.name.startswith(MODEL_PREFIX) else self.name
        parts = base.split("__", 1)
        return parts[1] if len(parts) == 2 else base

    @property
    def runnable(self) -> bool:
        return self.jax is not None

    def to_json(self) -> dict:
        return {
            "name": self.name,
            "short": self.short,
            "bundle": self.bundle,
            "family": self.family,
            "tflite": os.path.relpath(self.tflite_path, REPORT),
            "runner": self.jax or "litert",
            "jax_dir": os.path.relpath(self.jax_dir, REPORT) if self.jax_dir else None,
            "no_jax_reason": self.no_jax_reason,
            "hard_skip": self.hard_skip,
            "note": self.note,
        }


BUNDLE_LANDMARK = "ai_face_processor_detect_landmark"
BUNDLE_BEAUTY_TYPE = "ai_beauty_type_processor"
BUNDLE_FACE_BEAUTY = "ai_face_beauty_processor"
BUNDLE_HUMAN = "ai_human_processor_beautybody_mb"


def _n(bundle: str, model: str) -> str:
    return f"{MODEL_PREFIX}{bundle}__{model}"


def _bundle(model_dir: str) -> str:
    return os.path.join(BUNDLE_ROOT, model_dir)


# tflite2jax limitations seen on these graphs (verbatim from
# generated/*/unsupported.json); PRELU / SPLIT are unimplemented builtins.
_PRELU = "PRELU: not lowered by tools/tflite2jax.py (no JAX implementation in this tool)"
_SPLIT = "SPLIT: not lowered by tools/tflite2jax.py (no JAX implementation in this tool)"
_DYN_RESHAPE = (
    "dynamic RESHAPE shape: RESHAPE target is computed from SHAPE/PACK at run "
    "time; tools/tflite2jax.py requires a constant shape (status "
    "unsupported_params)"
)

SPECS = [
    # ------------------------------------------------------------------
    # ai_beauty_type_processor.bundle (FUAITYPE_FACEPROCESSOR_IMAGE_BEAUTY)
    # ------------------------------------------------------------------
    Spec(_n(BUNDLE_BEAUTY_TYPE, "dense_eb"), BUNDLE_BEAUTY_TYPE, "landmarks_score",
         no_jax_reason=_DYN_RESHAPE,
         note="40x40x1 crop -> 13 dense eyebrow points + 2-class score"),
    Spec(_n(BUNDLE_BEAUTY_TYPE, "dense_eye_iris"), BUNDLE_BEAUTY_TYPE, "landmarks_multi",
         no_jax_reason=_DYN_RESHAPE,
         note="eye crop + 8 eye points + heatmap -> 8/25 points + score"),
    Spec(_n(BUNDLE_BEAUTY_TYPE, "dense_iris"), BUNDLE_BEAUTY_TYPE, "landmarks_multi",
         jax="generated", jax_dir=os.path.join(GENERATED_ROOT, _n(BUNDLE_BEAUTY_TYPE, "dense_iris")),
         note="64x64x1 eye crop -> two 25-point iris meshes"),
    Spec(_n(BUNDLE_BEAUTY_TYPE, "dense_mouth"), BUNDLE_BEAUTY_TYPE, "landmarks_multi",
         jax="generated", jax_dir=os.path.join(GENERATED_ROOT, _n(BUNDLE_BEAUTY_TYPE, "dense_mouth")),
         note="mouth crops -> 56 dense + 18 upper/lower mouth points"),
    Spec(_n(BUNDLE_BEAUTY_TYPE, "face_landmark_lite_210818_360"),
         BUNDLE_BEAUTY_TYPE, "landmarks",
         jax="generated",
         jax_dir=os.path.join(GENERATED_ROOT, _n(BUNDLE_BEAUTY_TYPE, "face_landmark_lite_210818_360")),
         note="64x64x1 -> 154 values = 77 landmarks"),
    Spec(_n(BUNDLE_BEAUTY_TYPE, "model_hair"), BUNDLE_BEAUTY_TYPE, "landmarks",
         no_jax_reason=_PRELU,
         note="96x96x1 -> 7 hairline points"),
    Spec(_n(BUNDLE_BEAUTY_TYPE, "mouth_checker_14"), BUNDLE_BEAUTY_TYPE, "binary_class",
         jax="generated",
         jax_dir=os.path.join(GENERATED_ROOT, _n(BUNDLE_BEAUTY_TYPE, "mouth_checker_14")),
         note="40x40x3 RGB -> 2-class mouth-open softmax"),
    Spec(_n(BUNDLE_BEAUTY_TYPE, "n-0.5_416_0.05_4aspect6"), BUNDLE_BEAUTY_TYPE,
         "detector_anchor", no_jax_reason=_SPLIT,
         note="416x416x3 -> 7098 anchors (score/ltrb/classes)"),
    Spec(_n(BUNDLE_BEAUTY_TYPE, "n-small_256_0.05_only_a5"), BUNDLE_BEAUTY_TYPE,
         "detector_anchor", no_jax_reason=_SPLIT,
         note="256x256x3 -> 3072 anchors (score/ltrb/classes)"),
    Spec(_n(BUNDLE_BEAUTY_TYPE, "s1_112_98"), BUNDLE_BEAUTY_TYPE, "landmarks_score",
         jax="generated",
         jax_dir=os.path.join(GENERATED_ROOT, _n(BUNDLE_BEAUTY_TYPE, "s1_112_98")),
         note="112x112x1 -> 98 points + 2-class score"),
    Spec(_n(BUNDLE_BEAUTY_TYPE, "s2"), BUNDLE_BEAUTY_TYPE,
         "landmarks", no_jax_reason=_DYN_RESHAPE,
         note="112x112 crop + heatmap + 77 points -> 77 refined points"),
    Spec(_n(BUNDLE_BEAUTY_TYPE, "s3"), BUNDLE_BEAUTY_TYPE,
         "landmarks_multi", no_jax_reason=_DYN_RESHAPE,
         note="5 eye/mouth crops + heatmaps + points -> 5 refined point sets"),

    # ------------------------------------------------------------------
    # ai_face_beauty_processor.bundle (FUAITYPE_IMAGE_BEAUTY)
    # ------------------------------------------------------------------
    Spec(_n(BUNDLE_FACE_BEAUTY, "Fprs.0003.Exp007e33_quan1_sm"),
         BUNDLE_FACE_BEAUTY, "mask_multiclass",
         jax="bundles", jax_dir=_bundle("Fprs.0003.Exp007e33_quan1_sm"),
         note="512x384x3 aligned face -> 15-class parsing softmax"),
    Spec(_n(BUNDLE_FACE_BEAUTY, "FprsTeeth.0001.Exp002pe99_quan1_sm"),
         BUNDLE_FACE_BEAUTY, "mask_multiclass",
         jax="generated",
         jax_dir=os.path.join(GENERATED_ROOT, _n(BUNDLE_FACE_BEAUTY, "FprsTeeth.0001.Exp002pe99_quan1_sm")),
         note="192x256x3 -> 4-class teeth parsing softmax (channel 3)"),
    Spec(_n(BUNDLE_FACE_BEAUTY, "hair_1209a_acnenet_crop0.1pos0.1_3x24x24_quat"),
         BUNDLE_FACE_BEAUTY, "binary_class",
         jax="bundles", jax_dir=_bundle("hair_1209a_acnenet_crop0.1pos0.1_3x24x24_quat"),
         note="24x24x3 del-spot patch -> 2-class (hair/acne) softmax"),
    Spec(_n(BUNDLE_FACE_BEAUTY, "highlight_1209a_acnenet_crop0.2pos0.1_3x24x24_quat"),
         BUNDLE_FACE_BEAUTY, "binary_class",
         jax="generated",
         jax_dir=os.path.join(GENERATED_ROOT, _n(BUNDLE_FACE_BEAUTY, "highlight_1209a_acnenet_crop0.2pos0.1_3x24x24_quat")),
         note="24x24x3 del-spot patch -> 2-class (highlight) softmax"),
    Spec(_n(BUNDLE_FACE_BEAUTY, "lines_1209a_acnenet_crop0.1pos0.1_3x24x24_quat"),
         BUNDLE_FACE_BEAUTY, "binary_class",
         jax="generated",
         jax_dir=os.path.join(GENERATED_ROOT, _n(BUNDLE_FACE_BEAUTY, "lines_1209a_acnenet_crop0.1pos0.1_3x24x24_quat")),
         note="24x24x3 del-spot patch -> 2-class (lines/wrinkles) softmax"),
    Spec(_n(BUNDLE_FACE_BEAUTY, "mobile_spade_256_ch16_from16_40000_ft16_ftsave_yunfu"),
         BUNDLE_FACE_BEAUTY, "image_tanh",
         jax="generated",
         jax_dir=os.path.join(GENERATED_ROOT, _n(BUNDLE_FACE_BEAUTY, "mobile_spade_256_ch16_from16_40000_ft16_ftsave_yunfu")),
         note="256x256x3 -> 256x256x3 tanh (even-skin SPADE GAN)"),
    Spec(_n(BUNDLE_FACE_BEAUTY, "mobile_spade_256_ch16_from16_ftsevere_lr0.0001_117_16001"),
         BUNDLE_FACE_BEAUTY, "image_tanh",
         jax="generated",
         jax_dir=os.path.join(GENERATED_ROOT, _n(BUNDLE_FACE_BEAUTY, "mobile_spade_256_ch16_from16_ftsevere_lr0.0001_117_16001")),
         note="256x256x3 -> 256x256x3 tanh (flaw remover SPADE GAN)"),
    Spec(_n(BUNDLE_FACE_BEAUTY, "mole_seg_24x24x3_1206_quat"),
         BUNDLE_FACE_BEAUTY, "mask_binary",
         jax="bundles", jax_dir=_bundle("mole_seg_24x24x3_1206_quat"),
         note="24x24x3 -> 24x24x1 mole mask"),
    Spec(_n(BUNDLE_FACE_BEAUTY, "skin_model_expand_supply_pta18000_quant_resizetrain_seg153"),
         BUNDLE_FACE_BEAUTY, "mask_binary",
         no_jax_reason=(
             "converted module fails numeric parity: fully-int8 graph, JAX "
             "emulation returns an all-zero sigmoid (max_abs 0.996 vs LiteRT); "
             "see generated/<model>/verify.json"
         ),
         note="320x320x3 -> 320x320x1 skin mask, threshold 0.6, keep_block_radio 0.01"),

    # ------------------------------------------------------------------
    # ai_face_processor_detect_landmark.bundle (FUAITYPE_FACEPROCESSOR_DETECT_LANDMARKS)
    # ------------------------------------------------------------------
    Spec(_n(BUNDLE_LANDMARK, "Fpe.0007.Exp101.ERefine3e70_refine1"),
         BUNDLE_LANDMARK, "landmarks_refine", no_jax_reason=_PRELU,
         note="features [1,364,4,48] + 91 points -> refined 91 points"),
    Spec(_n(BUNDLE_LANDMARK, "Fpe.0007.Exp101.ERefine3e70_refine2"),
         BUNDLE_LANDMARK, "landmarks_refine", no_jax_reason=_PRELU,
         note="features [1,364,4,32] + 91 points -> refined 91 points"),
    Spec(_n(BUNDLE_LANDMARK, "Fpe.0007.Exp101.ERefine3e70_refine3"),
         BUNDLE_LANDMARK, "landmarks_refine", no_jax_reason=_PRELU,
         note="features [1,364,4,24] + 91 points -> 5 point sets"),
    Spec(_n(BUNDLE_LANDMARK, "Fpe.0007.Exp101.XScore.s76e150_base"),
         BUNDLE_LANDMARK, "landmarks_refine", no_jax_reason=_PRELU,
         note="112x112x1 -> 91 points + 2 scores + 3 feature maps"),
    Spec(_n(BUNDLE_LANDMARK, "face_landmark_mb360_64"), BUNDLE_LANDMARK, "landmarks",
         jax="bundles", jax_dir=_bundle("face_landmark_mb360_64"),
         note="64x64x1 -> 154 values = 77 landmarks"),
    Spec(_n(BUNDLE_LANDMARK, "facecheck40_dense_2000000_regress"), BUNDLE_LANDMARK,
         "binary_score", jax="bundles",
         jax_dir=_bundle("facecheck40_dense_2000000_regress"),
         note="40x40x1 -> 1 face score"),
    Spec(_n(BUNDLE_LANDMARK, "head"), BUNDLE_LANDMARK, "vector14",
         jax="bundles", jax_dir=_bundle("head"),
         note="[1,126] landmark features -> 14 head-pose/bbox values"),
    Spec(_n(BUNDLE_LANDMARK, "n-0.5_416_0.05_4aspect6"), BUNDLE_LANDMARK,
         "detector_anchor", no_jax_reason=_SPLIT,
         note="416x416x3 -> 7098 anchors (score/ltrb/classes)"),
    Spec(_n(BUNDLE_LANDMARK, "n-small_256_0.05_only_a5"), BUNDLE_LANDMARK,
         "detector_anchor", no_jax_reason=_SPLIT,
         note="256x256x3 -> 3072 anchors (score/ltrb/classes)"),
    Spec(_n(BUNDLE_LANDMARK, "s1"), BUNDLE_LANDMARK, "landmarks_score",
         jax="bundles", jax_dir=_bundle("s1"),
         note="64x64x1 -> 91 points + 2-class score"),

    # ------------------------------------------------------------------
    # ai_human_processor_beautybody_mb.bundle (FUAITYPE_HUMAN_PROCESSOR_IMAGE_BEAUTY)
    # ------------------------------------------------------------------
    Spec(_n(BUNDLE_HUMAN, "FULLBODY_POFH2D_20210302_0001_e1604_q"), BUNDLE_HUMAN,
         "pose_heatmaps",
         no_jax_reason=(
             "converted module fails numeric parity: fully-int8 graph, JAX "
             "emulation decorrelates (uint8 outputs 0% equal, heatmap dequant "
             "max_abs 254.8); see generated/<model>/verify.json"
         ),
         note="256x192x3 uint8 -> joint heatmaps 64x48x31 + POFs + bbfs"),
    Spec(_n(BUNDLE_HUMAN, "human_detector_retinanet_mobilenetv2_1.0_quant"),
         BUNDLE_HUMAN, "detector_boxes", hard_skip=True,
         no_jax_reason=(
             _DYN_RESHAPE + "; also not runnable on stock LiteRT: the CONV_2D "
             "weights carry per-channel zero_point -127, which LiteRT rejects "
             "('unsupported zero-point value (-127) in channel 130', XNNPACK and "
             "reference kernels) although FuAI's vendored runtime accepts it"
         ),
         note="320x320x3 -> 19125 box scores + 4 box regressions"),
]


BY_NAME = {s.name: s for s in SPECS}
BY_SHORT = {}
for _s in SPECS:
    BY_SHORT.setdefault(_s.short, []).append(_s)


def resolve(name: str) -> Spec:
    """Look a model up by canonical name, short name, or path stem.

    Short names are ambiguous for the two detector nets that ship in both the
    beauty-type and the detect-landmark bundle; those must use the canonical
    ``model__<bundle>__<model>`` form.
    """
    key = name.strip()
    if key.endswith(".tflite"):
        key = key[: -len(".tflite")]
    if key in BY_NAME:
        return BY_NAME[key]
    base = os.path.basename(key)
    if base in BY_NAME:
        return BY_NAME[base]
    matches = BY_SHORT.get(key) or BY_SHORT.get(base)
    if matches and len(matches) == 1:
        return matches[0]
    if matches:
        raise KeyError(
            f"model name {name!r} is ambiguous: "
            + ", ".join(m.name for m in matches)
        )
    # unique case-insensitive prefix of the short name (e.g. "FprsTeeth",
    # "FULLBODY", "face_landmark_mb360")
    pref = [
        s for s in SPECS
        if s.short.lower().startswith(key.lower()) or s.name.lower().startswith(key.lower())
    ]
    if len(pref) == 1:
        return pref[0]
    if len(pref) > 1:
        raise KeyError(
            f"model name {name!r} is ambiguous: " + ", ".join(s.name for s in pref)
        )
    raise KeyError(f"unknown model {name!r}; run --list to see the {len(SPECS)} names")


def check_registry() -> list[str]:
    """Sanity-check the registry against the extracted model files."""
    problems = []
    on_disk = {
        os.path.basename(p)[: -len(".tflite")]
        for p in os.listdir(TFLITE_DIR)
        if p.endswith(".tflite")
    }
    listed = {s.name for s in SPECS}
    if len(SPECS) != 33:
        problems.append(f"registry has {len(SPECS)} entries, expected 33")
    for missing in sorted(on_disk - listed):
        problems.append(f"model not registered: {missing}")
    for extra in sorted(listed - on_disk):
        problems.append(f"registered model missing on disk: {extra}")
    for s in SPECS:
        if s.jax and not os.path.isdir(s.jax_dir or ""):
            problems.append(f"{s.name}: jax dir missing: {s.jax_dir}")
        if s.jax and not os.path.exists(os.path.join(s.jax_dir, "model.py")):
            problems.append(f"{s.name}: model.py missing in {s.jax_dir}")
        if not os.path.exists(s.tflite_path):
            problems.append(f"{s.name}: tflite missing: {s.tflite_path}")
    return problems
