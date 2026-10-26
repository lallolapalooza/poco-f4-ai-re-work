# Data-pipeline integration contract (per app)

Every report must contain `PIPELINE.md` with these sections. Mark every row
`confirmed`, `inferred`, or `unknown`; link the evidence file/offset/class/method.

## 1. Data sources / triggers
- UI/UX trigger, service/binder entry, broadcast, job, sensor, camera, audio, text, DB row.
- Exact class + method, or native JNI symbol + address.
- Lifecycle: init, per-event/per-frame, teardown.

## 2. Transport and format
- Video: GL texture (2D/OES), YUV/NV21, Bitmap, surface, encoder; width/height/stride,
  color space, rotation/orientation, timestamp clock, camera facing.
- Audio: sample rate, channels, PCM format, frame/hop, buffer size, timestamps.
- Text/other: encoding, tokenizer, schema/protobuf, units.
- Thread/queue: caller thread, worker/handler, queue depth, drop/skip policy.

## 3. Preprocessing
- Exact operations in order: rotate/crop/resize, color convert, mean/scale, feature
  extraction (MFCC/mel/HOG/tokenizer), normalization constants, padding.
- Where implemented (class/method/address), and whether it matches the model's
  expected contract.

## 4. Model invocation
- Engine/runtime (TFLite, SNPE, ByteNN, MACE, custom), session creation, model path,
  hash, tensor names/shapes/dtypes, batching, delegates/threads, cadence.
- How the preprocessed buffer is bound (texture id, ByteBuffer, direct pointer).

## 5. Output postprocessing
- Tensor decode, anchors/NMS, thresholds, argmax/softmax, landmark/mask reshape,
  coordinate transforms back to source space, temporal smoothing/tracking.
- Result structs/classes and every field the app consumes.

## 6. Consumer / action
- Render/effect node, UI, storage, network, notification, intent, IR/BLE/audio out.
- What changes in the user-visible data path.

## 7. Failure and fallback
- Model missing/download failure, unsupported device, timeout, fallback path.

## 8. Confidence and open questions
- Table: claim | confidence | evidence | what would settle it.

## 9. Reimplementation mapping
- Which JAX module reproduces which stage; exact input/output contracts; what is
  ARCHITECTURE_ONLY and why.
