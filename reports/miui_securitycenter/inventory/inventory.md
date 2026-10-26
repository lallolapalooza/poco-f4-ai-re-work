# APK inventory: Security

`/run/media/asdf/30620d0d-1e41-4d55-af08-b4de2fb9c517/xiaomi poco f4 system/product/priv-app/MIUISecurityCenterGlobal/MIUISecurityCenterGlobal.apk`

| Field | Value |
|---|---|
| Package | com.miui.securitycenter |
| Version | 8.9.4-240516.1.1 |
| Version code | 40000894 |
| Label | Security |
| Min SDK | 23 |
| Target SDK | 33 |
| Main activity | com.miui.securityscan.MainActivity |
| Size | 93987501 |
| SHA-256 | 35eefc4d8bbf03bbf0b759199ab258b5113c4dfdba59188fa8136c2b187c56c6 |
| ABIs | ['arm64-v8a'] |
| Components | activities=249, services=59, receivers=19, providers=39 |
| Permissions | 124 |
| Zip entries | 8839 |

## Groups

| Group | Files | Uncompressed bytes |
|---|---:|---:|
| dex | 5 | 35928556 |
| native_libs | 11 | 7678424 |
| model_assets | 1 | 680154 |
| configs | 5191 | 50193238 |
| other | 3631 | 29242622 |

## AI artifacts

### Model assets (1)

| Member | Size | Magic | TFL3 | Keywords |
|---|---:|---|---|---|
| `assets/model` | 680154 | 0000000100000000 | no | model |

### Native libs matching the parent keyword set (1)

| Library | Size | Match |
|---|---:|---|
| `lib/arm64-v8a/libtensorflowlite_jni.so` | 1700448 | tensorflow |

### DEX markers (2)

| Marker | Occurrences |
|---|---:|
| `Lorg/tensorflow/lite` | 16 |
| `mibrain` | 1 |

### Asset keyword hits (2)

| Asset | Size |
|---|---:|
| `assets/beauty_face_support_activity_list.json` | 448 |
| `assets/redmi_performance_mode/Ai-Model.json` | 119414 |

## Extraction

Extracted 1 model(s), 1 lib(s), 2380602 bytes; limits {'max_file_mb': 50.0, 'max_total_mb': 90.0}.

## Permissions

- `android.permission.ACCESS_CACHE_FILESYSTEM`
- `android.permission.ACCESS_DOWNLOAD_MANAGER`
- `android.permission.ACCESS_NETWORK_STATE`
- `android.permission.ACCESS_WIFI_STATE`
- `android.permission.ACCESS_WIMAX_STATE`
- `android.permission.AUTHENTICATE_ACCOUNTS`
- `android.permission.BACKUP`
- `android.permission.BATTERY_STATS`
- `android.permission.BLUETOOTH`
- `android.permission.BLUETOOTH_ADMIN`
- `android.permission.CALL_PHONE`
- `android.permission.CHANGE_WIFI_STATE`
- `android.permission.CHANGE_WIMAX_STATE`
- `android.permission.CLEAR_APP_CACHE`
- `android.permission.CLEAR_APP_USER_DATA`
- `android.permission.CONFIGURE_WIFI_DISPLAY`
- `android.permission.CONTROL_VPN`
- `android.permission.DELETE_CACHE_FILES`
- `android.permission.DEVICE_POWER`
- `android.permission.DISABLE_KEYGUARD`
- `android.permission.DOWNLOAD_WITHOUT_NOTIFICATION`
- `android.permission.EXPAND_STATUS_BAR`
- `android.permission.FORCE_STOP_PACKAGES`
- `android.permission.FOREGROUND_SERVICE`
- `android.permission.GET_PACKAGE_SIZE`
- `android.permission.GET_TASKS`
- `android.permission.INTERACT_ACROSS_USERS`
- `android.permission.INTERNET`
- `android.permission.KILL_BACKGROUND_PROCESSES`
- `android.permission.MANAGE_ACCOUNTS`
- `android.permission.MANAGE_NETWORK_POLICY`
- `android.permission.MANAGE_USB`
- `android.permission.MANAGE_USERS`
- `android.permission.MASTER_CLEAR`
- `android.permission.MODIFY_NETWORK_ACCOUNTING`
- `android.permission.MODIFY_PHONE_STATE`
- `android.permission.MOUNT_UNMOUNT_FILESYSTEMS`
- `android.permission.PACKAGE_USAGE_STATS`
- `android.permission.PACKAGE_VERIFICATION_AGENT`
- `android.permission.POST_NOTIFICATIONS`
- `android.permission.READ_CALL_LOG`
- `android.permission.READ_CONTACTS`
- `android.permission.READ_NETWORK_USAGE_HISTORY`
- `android.permission.READ_PHONE_STATE`
- `android.permission.READ_PRIVILEGED_PHONE_STATE`
- `android.permission.READ_SECURE_SETTINGS`
- `android.permission.READ_SETTINGS`
- `android.permission.READ_SYNC_SETTINGS`
- `android.permission.READ_USER_DICTIONARY`
- `android.permission.REAL_GET_TASKS`
- `android.permission.REBOOT`
- `android.permission.RECEIVE_BOOT_COMPLETED`
- `android.permission.SEND_SMS`
- `android.permission.SYSTEM_ALERT_WINDOW`
- `android.permission.UPDATE_APP_OPS_STATS`
- `android.permission.USE_BIOMETRIC`
- `android.permission.USE_CREDENTIALS`
- `android.permission.USE_FINGERPRINT`
- `android.permission.VIBRATE`
- `android.permission.WAKE_LOCK`
- `android.permission.WRITE_APN_SETTINGS`
- `android.permission.WRITE_CALL_LOG`
- `android.permission.WRITE_SETTINGS`
- `android.permission.WRITE_SYNC_SETTINGS`
- `android.permission.WRITE_USER_DICTIONARY`
- `com.android.launcher.permission.INSTALL_SHORTCUT`
- `com.android.settings.permission.CLOUD_SETTINGS_PROVIDER`
- `com.android.settings.permission.POWER_MODE_CHANGE`
- `com.android.settings.permission.PULL_CLOUD_DATA`
- `com.android.settings.permission.SWITCH_DEV_MODE`
- `com.android.updater.permission.ACCESS_UPDATESERVICE`
- `com.android.updater.permission.RECEIVE_BROADCAST`
- `com.cleanmaster.permission.sdk.clean`
- `com.google.android.c2dm.permission.RECEIVE`
- `com.google.android.finsky.permission.BIND_GET_INSTALL_REFERRER_SERVICE`
- `com.google.android.gms.permission.AD_ID`
- `com.mi.health.provider.device.read`
- `com.mipay.wallet.permission.READ_IDENTITY_STATUS`
- `com.miui.calendar.permission.READ_CALENDAR`
- `com.miui.cleanmaster.permission.Clean_Master`
- `com.miui.dock.permission.DOCK_EVENT`
- `com.miui.gamebooster.access.ACTIVITY`
- `com.miui.gamebooster.permission.PARENT_VERIFY`
- `com.miui.gameturbo.permission.ACTIVE_MANAGER`
- `com.miui.guardprovider.permission.Guard_Provider`
- `com.miui.home.launcher.permission.UNINSTALL_SHORTCUT`
- `com.miui.monthreport.permission.DATA_MONTH_REPORT`
- `com.miui.permission.FIREWALL`
- `com.miui.permission.MANAGE_BOOT_TIME`
- `com.miui.permission.MANAGE_SMS_INTERCEPT`
- `com.miui.powerkeeper.permission.REQUEST_WAKE`
- `com.miui.securityadd.permission.GAMEBOOSTER_PROVIDER`
- `com.miui.securityadd.permission.KID_MODE_ACCESS`
- `com.miui.securityadd.permission.SUPERPOWER_RECEIVE`
- `com.miui.securitycenter.DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION`
- `com.miui.securitycenter.permission.ACCESS_SECURITY_CENTER_PROVIDER`
- `com.miui.securitycenter.permission.GAMEBOOSTER_PROVIDER`
- `com.miui.securitycenter.permission.GLOBAL_PACKAGEINSTALLER`
- `com.miui.securitycenter.permission.MIPUSH_RECEIVE`
- `com.miui.securitycenter.permission.PERM_USE_INFO`
- `com.miui.systemAdSolution.LOCAL_AD_PROVIDER`
- `com.miui.systemAdSolution.adSwitch.PROVIDER`
- `com.miui.voiceassist.QUERY_ENABLE`
- `com.miui.yellowpage.permission.ACCESS_YELLOWPAGE`
- `com.xiaomi.calendar.permission.READ_CALENDAR`
- `com.xiaomi.gamecenter.for3thd.migame.xcashier`
- `com.xiaomi.macro.permission`
- `com.xiaomi.permission.ACCESS_SECURITY_DEVICE_CREDENTIAL`
- `com.xiaomi.permission.BIND_CONTINUITY_SERVICE_INTERNAL`
- `com.xiaomi.permission.CLOUD_MANAGER`
- `com.xiaomi.permission.USE_KEYBAG`
- `com.xiaomi.wearable.provider.device.read`
- `com.xiaomi.xmsf.permission.MIPUSH_RECEIVE`
- `com.xiaomi.xmsf.permission.USE_XMSF_TRAFFIC`
- `miui.packageinstaller.permission.ACTION_INFO`
- `miui.permission.ACCESS_BLE_SETTINGS`
- `miui.permission.ACCESS_MILINK_CAST`
- `miui.permission.BACKUP_PROXY`
- `miui.permission.FILE_PROXY`
- `miui.permission.READ_AND_WIRTE_PERMISSION_MANAGER`
- `miui.permission.SHELL`
- `miui.permission.USE_INTERNAL_GENERAL_API`
- `miui.permission.powerkeeper.HIDDEN_MODE_PROVIDER`
- `miui.permission.restore_brightness`
