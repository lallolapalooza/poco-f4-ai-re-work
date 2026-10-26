# APK inventory: Qualcomm Voice Assist

`/run/media/asdf/30620d0d-1e41-4d55-af08-b4de2fb9c517/xiaomi poco f4 system/system_ext/priv-app/QualcommVoiceActivation/QualcommVoiceActivation.apk`

| Field | Value |
|---|---|
| Package | com.quicinc.voice.activation |
| Version | 6.0.10 |
| Version code | 600010 |
| Label | Qualcomm Voice Assist |
| Min SDK | 29 |
| Target SDK | 34 |
| Main activity | None |
| Size | 47391029 |
| SHA-256 | 565f8818b189d4cc0c90ad383f068093292fe51ac89ca8c1cc1d9680a7a32863 |
| ABIs | ['arm64-v8a'] |
| Components | activities=1, services=16, receivers=1, providers=0 |
| Permissions | 10 |
| Zip entries | 37 |

## Groups

| Group | Files | Uncompressed bytes |
|---|---:|---:|
| dex | 1 | 615428 |
| native_libs | 15 | 28469616 |
| model_assets | 7 | 19645264 |
| configs | 7 | 41771 |
| other | 7 | 61060 |

## AI artifacts

### Model assets (7)

| Member | Size | Magic | TFL3 | Keywords |
|---|---:|---|---|---|
| `assets/ubm/general_UV_024.uim` | 7304344 | c80c18008c746f00 | no |  |
| `assets/1.5_stage/63ff0ae5_en-US_002.uim` | 452536 | c80c1800ace70600 | no |  |
| `assets/1st_stage/63ff0ae5_en-US_001.uim` | 206792 | c80c1800bc270300 | no |  |
| `assets/1st_stage/63ff0ae5_en-US_036.uim` | 208840 | c80c1800bc2f0300 | no |  |
| `assets/1st_stage/63ff0ae5_en-US_038.uim` | 175560 | c80c1800bcad0200 | no |  |
| `assets/1st_stage/63ff0ae5_en-US_040.uim` | 198088 | c80c1800bc050300 | no |  |
| `assets/ubm/general_UV_015.uim` | 11099104 | c80c1800d45ba900 | no |  |

### Native libs matching the parent keyword set (7)

| Library | Size | Match |
|---|---:|---|
| `lib/arm64-v8a/libSNPE.so` | 12684040 | snpe |
| `lib/arm64-v8a/libSnpeHtpV75Stub.so` | 376312 | snpe |
| `lib/arm64-v8a/libdnnvad_jni.so` | 19296 | dnn |
| `lib/arm64-v8a/libeai_floating.so` | 190616 | ai |
| `lib/arm64-v8a/libqvasml.so` | 2378216 | ml |
| `lib/arm64-v8a/libtensorflowlite_c.so` | 2697384 | tensorflow |
| `lib/arm64-v8a/libwhisper_lib.so` | 306232 | whisper |

### DEX markers (0)

_none_

### Asset keyword hits (0)

_none_

## Extraction

Extracted 7 model(s), 7 lib(s), 38297360 bytes; limits {'max_file_mb': 60.0, 'max_total_mb': 120.0}.

## Permissions

- `android.permission.CAPTURE_AUDIO_HOTWORD`
- `android.permission.CHANGE_DEVICE_IDLE_TEMP_WHITELIST`
- `android.permission.FORCE_STOP_PACKAGES`
- `android.permission.FOREGROUND_SERVICE`
- `android.permission.FOREGROUND_SERVICE_MICROPHONE`
- `android.permission.MODIFY_AUDIO_SETTINGS`
- `android.permission.RECEIVE_BOOT_COMPLETED`
- `android.permission.RECORD_AUDIO`
- `android.permission.START_ACTIVITIES_FROM_BACKGROUND`
- `android.permission.WAKE_LOCK`
