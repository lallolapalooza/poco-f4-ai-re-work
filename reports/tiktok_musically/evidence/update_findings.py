#!/usr/bin/env python3
"""Add the video-frame `data_pipelines` block to findings.json (idempotent)."""
import json
import os

P = os.path.join(os.path.dirname(os.path.dirname(os.path.abspath(__file__))), "findings.json")


def stage(name, detail, evidence, confidence):
    return {"stage": name, "detail": detail, "evidence": evidence, "confidence": confidence}


def main():
    d = json.load(open(P))
    d.setdefault("features", [])

    video_feature = {
        "name": "Video frame -> face DNN (VESDK render path)",
        "entrypoints": [
            "libttvesdk.so render loop (logs 'DrawFrame:LoadResource'@0x5cb5e5, "
            "'DrawFrame:bef_effect_algorithm'@0x5cb62f); frame calls 0x1cf548/0x1cf950/"
            "0x1cf740/0x1cf81c/0x1cf9bc",
            "com.bef.effectsdk.BEFEffectNative.nativeProcessFrame(JIIIID) @libeffect.so 0x2eac68 "
            "(Java SDK binding; no in-app caller)",
            "nativeSetFrameOrientation @0x2ead50, nativeSetCameraPosition @0x2ead0c",
        ],
        "pipeline": [
            "frame descriptor stride 0x48: [+0x00] texture id, [+0x14] width, [+0x18] height, "
            "[+0x20] double timestamp",
            "bef_effect_set_width_height(handle,width,height)",
            "bef_effect_process_texture(handle,texId,arg2,timestamp) @0x3097f0 -> "
            "bef_effect_process_texture_with_loading_timeout @0x3097f8",
            "GL_TEXTURE_2D / RGBA8888 input (glTexImage2D 0xde1/0x1908/0x1401, libttvesdk 0x4b7314)",
            "effect graph FaceDetectTaskTT -> BES::NtfEvent<algorithm_result_face_st> -> "
            "VEFaceDetectInfo (106 points)",
        ],
        "models": ["tt_face_v10.0.model", "tt_face_extra_v13.0.model",
                   "tt_face_attribute_age_v1.1.model"],
        "confidence": "confirmed",
        "notes": "Transport/format/setters confirmed from disassembly. Java arg2 (3rd int) is "
                 "forwarded to bef_effect_process_texture but its meaning is unknown. Same "
                 "binaries as trill; only Java obfuscation names differ.",
    }
    if not any(f.get("name") == video_feature["name"] for f in d["features"]):
        d["features"].append(video_feature)

    render = {
        "id": "vesdk_render_to_face_dnn",
        "name": "VESDK render frame -> face detection/attribute DNN",
        "variant_equivalence": "libeffect.so dad4778a…e76c35 and libttvesdk.so e38abe25…a6ce74 "
                               "are byte-identical to trill; the three model bundles are identical too",
        "stages": [
            stage("1. trigger",
                  "VESDK render loop per frame (libttvesdk.so DrawFrame); effect handle and "
                  "resource finder created once per session",
                  "evidence/vesdk_drawframe_callsites.txt; evidence/libeffect_face_video_strings.txt",
                  "confirmed"),
            stage("2. transport",
                  "GL_TEXTURE_2D RGBA8888 texture + width/height ints + double timestamp; rotation "
                  "and camera facing via separate setters; YUV/GRAY rejected by every DNN task",
                  "native/libeffect_musically_nativeProcessFrame.s; libttvesdk 0x4b7314-0x4b7330 "
                  "(glTexImage2D 0xde1/0x1908/0x1401)",
                  "confirmed"),
            stage("3. call chain",
                  "bef_effect_set_width_height(handle,w,h) then bef_effect_process_texture"
                  "(handle,texId,arg2,ts) -> bef_effect_process_texture_with_loading_timeout"
                  "(handle,…,loadingTimeout=0,ts)",
                  "libeffect 0x2eac68-0x2eaca4; 0x3097f0; 0x3097f8; call sites 0x1cf548/0x1cf950",
                  "confirmed"),
            stage("4. preprocessing",
                  "crop/resize inside BEF::FaceDetectTaskTT from the texture; base120 suggests "
                  "120x120; exact scale/bias unknown; the 'mean' record is anchor geometry, not "
                  "image means",
                  "strings d66161 base120, d66e11 agenet, d85571 pout; report §4.1",
                  "inferred"),
            stage("5. model invocation",
                  "espresso::Thrustor (libbytenn.so) per sub-model record; bundle paths "
                  "assets/model/ttfacemodel/*.model, assets/model/ttfaceattrmodel/"
                  "tt_face_attribute_age_v1.1.model",
                  "report §5; native/elf_info_libeffect.txt imports",
                  "confirmed for loading; tensor shapes unknown"),
            stage("6. output",
                  "algorithm_result_face_st / algorithm_result_face_attrs_st events -> "
                  "VEFaceDetectInfo (rect, score, PointF[106], float[106] visibility, "
                  "yaw/pitch/roll, eyeDistance, faceID, action, trackCount, ext iris/eye/lip/"
                  "eyebrow) or VEFaceAttributeInfo (age first)",
                  "sources/video/com.ss.android.vesdk.faceinfo.VEFaceDetectInfo.java; "
                  "sources/video/X.C64698PZc.java; strings ca414b, ca40e9, dbe58d, dbe232",
                  "confirmed"),
            stage("7. consumer",
                  "effect nodes (face reshape/beauty/makeup), Java face-info API referenced from "
                  "classes8/10.dex, preview surface and encoder through the VESDK filter chain "
                  "(vesdk::pub::Filters::set_bef_effect @0x2eb830)",
                  "report §3; strings ca4550/ca453d/ca4465",
                  "confirmed for effect nodes/events; encoder node inferred"),
            stage("8. failure",
                  "missing model -> 'FaceDetectTaskTT: Error!!! No detector' (no fallback); null "
                  "handle -> -5; missing config -> -6; AB gate enable_fastmode_faceDetect240",
                  "strings d02a24, d02a71, d2505a; 0x309894/0x30989c",
                  "confirmed"),
        ],
    }

    attributes = {
        "id": "face_attribute_age",
        "name": "Face attribute (age) DNN",
        "stages": [
            stage("1. trigger",
                  "FaceAttributesTask inside the same effect graph, after face detection",
                  "strings d25094 FaceAttributesTask.cpp, dde608 N3BEF18FaceAttributesTaskE",
                  "confirmed"),
            stage("2. transport",
                  "texture-derived aligned face crop (task rejects YUV/GRAY); crop size unknown; "
                  "the bgr448 assert claimed in report §6 does not exist in libeffect.so",
                  "strings d250ed, d25120; grep 'bgr448|448' -> no hit",
                  "unknown for crop size"),
            stage("3. model",
                  "tt_face_attribute_age_v1.1.model, single record 'agenet'; other attribute "
                  "models named by the SDK (tt_beauty_attr6_v2.0, tt_face_attribute_v7.2, "
                  "tt_face_attribute_extra_v3.0, tt_face_attribute_exp_v1.0) are not shipped",
                  "strings d9283f, d25007, cd9f40, d914a5, d9288f",
                  "confirmed for file/record; output layout unknown"),
            stage("4. output",
                  "VEFaceAttributeInfo/VEFaceAttribute: age, boyProb, attractive, happyScore, "
                  "expType, expProbs[7], realFaceProb, quality, arousal, valence, sadScore, "
                  "angryScore, surpriseScore, maskProb, wearHatProb, mustacheProb, lipstickProb, "
                  "wearGlassProb, wearSunglassProb, blurScore, illumination; only age has a "
                  "shipped model",
                  "sources/video/com.ss.android.vesdk.faceinfo.VEFaceAttribute.java",
                  "confirmed"),
        ],
    }

    d["data_pipelines"] = [render, attributes]
    d["unknowns"] = [
        "Smash-face payload descriptor schema (op codes, tensor descriptors, offsets, quantisation "
        "tables) — blocks weight slicing and exact topology",
        "Per-record dtype/quantisation (int8 vs fp16 vs fp32; weight regions show both long fp32 "
        "runs and dense int8/int16-like bytes)",
        "Exact input resolution, layout (NCHW/NHWC) and colour order per sub-model",
        "Age-head output layout (regression vs class logits) and class boundaries",
        "Whether the extra bundle is always loaded or only for landmark/eye effects; AB flags that "
        "enable the TT detector",
        "Consumer/semantics of the decoded 'mean' record (anchor geometry) in the detector graph",
        "Function inside libeffect.so that fills the map<string,model*> consumed by ExtractModel "
        "(0x696900); only the stripped binary documents the descriptor schema",
        "Meaning of the 3rd int of BEFEffectNative.processFrame (forwarded to "
        "bef_effect_process_texture, not read by its wrapper)",
        "Clock origin of the per-frame double timestamp (frame PTS seconds assumed; getter not "
        "identified)",
        "Detector NMS/thresholds, landmark coordinate transform back to source space, and temporal "
        "smoothing in the native tracker",
        "Java consumer classes of VEFaceDetectInfo.covert() (only classes8.dex and classes10.dex "
        "reference it; class names obfuscated)",
    ]

    with open(P, "w") as fh:
        json.dump(d, fh, indent=2, ensure_ascii=False)
        fh.write("\n")
    print("features=%d data_pipelines=%d" % (len(d["features"]), len(d["data_pipelines"])))
    json.load(open(P))
    print("findings.json valid")


if __name__ == "__main__":
    main()
