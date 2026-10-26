package com.android.server.display;

import android.app.ActivityTaskManager;
import android.app.IActivityTaskManager;
import android.app.TaskStackListener;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.display.BrightnessConfiguration;
import android.hardware.display.DisplayManagerInternal;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.os.Trace;
import android.util.EventLog;
import android.util.MathUtils;
import android.util.Slog;
import android.util.SparseArray;
import android.util.Spline;
import android.util.TimeUtils;
import com.android.internal.display.BrightnessSynchronizer;
import com.android.internal.os.BackgroundThread;
import com.android.server.EventLogTags;
import com.android.server.display.brightness.BrightnessEvent;
import java.io.PrintWriter;

/* JADX INFO: loaded from: /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/miui_securitycoreadd/sources/framework/services/classes.dex */
public class AutomaticBrightnessController {
    private static final long AMBIENT_LIGHT_PREDICTION_TIME_MILLIS = 100;
    public static final int AUTO_BRIGHTNESS_DISABLED = 2;
    public static final int AUTO_BRIGHTNESS_ENABLED = 1;
    public static final int AUTO_BRIGHTNESS_OFF_DUE_TO_DISPLAY_STATE = 3;
    private static final int BRIGHTNESS_ADJUSTMENT_SAMPLE_DEBOUNCE_MILLIS = 10000;
    private static boolean DEBUG = false;
    private static final boolean DEBUG_PRETEND_LIGHT_SENSOR_ABSENT = false;
    private static final int FOLD_DEVICE_LIGHT_SENSOR_WARM_UP_TIME = 250;
    private static final boolean IS_FOLDABLE_DEVICE;
    private static final int MSG_BRIGHTNESS_ADJUSTMENT_SAMPLE = 2;
    private static final int MSG_INVALIDATE_CURRENT_SHORT_TERM_MODEL = 3;
    private static final int MSG_INVALIDATE_PAUSED_SHORT_TERM_MODEL = 7;
    private static final int MSG_RUN_UPDATE = 6;
    private static final int MSG_UPDATE_AMBIENT_LUX = 1;
    private static final int MSG_UPDATE_FOREGROUND_APP = 4;
    private static final int MSG_UPDATE_FOREGROUND_APP_SYNC = 5;
    private static final String TAG = "AutomaticBrightnessController";
    private IActivityTaskManager mActivityTaskManager;
    private float mAmbientBrighteningThreshold;
    private final HysteresisLevels mAmbientBrightnessThresholds;
    private final HysteresisLevels mAmbientBrightnessThresholdsIdle;
    private float mAmbientDarkeningThreshold;
    private final int mAmbientLightHorizonLong;
    private final int mAmbientLightHorizonShort;
    private AmbientLightRingBuffer mAmbientLightRingBuffer;
    private float mAmbientLux;
    private boolean mAmbientLuxValid;
    private boolean mAutoBrightnessValid;
    private AutomaticBrightnessControllerStub mAutomaticBrightnessControllerImpl;
    private final long mBrighteningLightDebounceConfig;
    private float mBrightnessAdjustmentSampleOldBrightness;
    private float mBrightnessAdjustmentSampleOldLux;
    private boolean mBrightnessAdjustmentSamplePending;
    private BrightnessThrottler mBrightnessThrottler;
    private final Callbacks mCallbacks;
    private Clock mClock;
    private Context mContext;
    private BrightnessMappingStrategy mCurrentBrightnessMapper;
    private int mCurrentLightSensorRate;
    private long mDarkeningLightDebounceConfig;
    private int mDisplayPolicy;
    private final float mDozeScaleFactor;
    private AutomaticBrightnessControllerStub.DualSensorPolicyListener mDualSensorPolicyListener;
    private float mFastAmbientLux;
    private int mForegroundAppCategory;
    private String mForegroundAppPackageName;
    private AutomaticBrightnessHandler mHandler;
    private HighBrightnessModeController mHbmController;
    private HysteresisLevelsStub mHysteresisLevelsImpl;
    private final BrightnessMappingStrategy mIdleModeBrightnessMapper;
    private final int mInitialLightSensorRate;
    private final Injector mInjector;
    private final BrightnessMappingStrategy mInteractiveModeBrightnessMapper;
    private boolean mIsBrightnessThrottled;
    private float mLastObservedLux;
    private long mLastObservedLuxTime;
    private final Sensor mLightSensor;
    private long mLightSensorEnableTime;
    private boolean mLightSensorEnabled;
    private final SensorEventListener mLightSensorListener;
    private int mLightSensorWarmUpTimeConfig;
    private boolean mLoggingEnabled;
    private final int mNormalLightSensorRate;
    private PackageManager mPackageManager;
    private final ShortTermModel mPausedShortTermModel;
    private int mPendingForegroundAppCategory;
    private String mPendingForegroundAppPackageName;
    private float mPreThresholdBrightness;
    private float mPreThresholdLux;
    private float mRawScreenAutoBrightness;
    private int mRecentLightSamples;
    private final boolean mResetAmbientLuxAfterWarmUpConfig;
    private float mScreenAutoBrightness;
    private float mScreenBrighteningThreshold;
    private final float mScreenBrightnessRangeMaximum;
    private final float mScreenBrightnessRangeMinimum;
    private final HysteresisLevels mScreenBrightnessThresholds;
    private final HysteresisLevels mScreenBrightnessThresholdsIdle;
    private float mScreenDarkeningThreshold;
    private final SensorManager mSensorManager;
    private final ShortTermModel mShortTermModel;
    private float mSlowAmbientLux;
    private int mState;
    private boolean mSupportDualSensorPolicy;
    private TaskStackListenerImpl mTaskStackListener;
    private boolean mUseDaemonSensorInProgress;
    private final int mWeightingIntercept;

    interface Callbacks {
        void updateBrightness();
    }

    interface Clock {
        long uptimeMillis();
    }

    static {
        IS_FOLDABLE_DEVICE = SystemProperties.getInt("persist.sys.muiltdisplay_type", 0) == 2;
    }

    AutomaticBrightnessController(Callbacks callbacks, Looper looper, SensorManager sensorManager, Sensor lightSensor, BrightnessMappingStrategy interactiveModeBrightnessMapper, int lightSensorWarmUpTime, float brightnessMin, float brightnessMax, float dozeScaleFactor, int lightSensorRate, int initialLightSensorRate, long brighteningLightDebounceConfig, long darkeningLightDebounceConfig, boolean resetAmbientLuxAfterWarmUpConfig, HysteresisLevels ambientBrightnessThresholds, HysteresisLevels screenBrightnessThresholds, HysteresisLevels ambientBrightnessThresholdsIdle, HysteresisLevels screenBrightnessThresholdsIdle, Context context, HighBrightnessModeController hbmController, BrightnessThrottler brightnessThrottler, BrightnessMappingStrategy idleModeBrightnessMapper, int ambientLightHorizonShort, int ambientLightHorizonLong, float userLux, float userBrightness) {
        this(new Injector(), callbacks, looper, sensorManager, lightSensor, interactiveModeBrightnessMapper, lightSensorWarmUpTime, brightnessMin, brightnessMax, dozeScaleFactor, lightSensorRate, initialLightSensorRate, brighteningLightDebounceConfig, darkeningLightDebounceConfig, resetAmbientLuxAfterWarmUpConfig, ambientBrightnessThresholds, screenBrightnessThresholds, ambientBrightnessThresholdsIdle, screenBrightnessThresholdsIdle, context, hbmController, brightnessThrottler, idleModeBrightnessMapper, ambientLightHorizonShort, ambientLightHorizonLong, userLux, userBrightness);
    }

    AutomaticBrightnessController(Injector injector, Callbacks callbacks, Looper looper, SensorManager sensorManager, Sensor lightSensor, BrightnessMappingStrategy interactiveModeBrightnessMapper, int lightSensorWarmUpTime, float brightnessMin, float brightnessMax, float dozeScaleFactor, int lightSensorRate, int initialLightSensorRate, long brighteningLightDebounceConfig, long darkeningLightDebounceConfig, boolean resetAmbientLuxAfterWarmUpConfig, HysteresisLevels ambientBrightnessThresholds, HysteresisLevels screenBrightnessThresholds, HysteresisLevels ambientBrightnessThresholdsIdle, HysteresisLevels screenBrightnessThresholdsIdle, Context context, HighBrightnessModeController hbmController, BrightnessThrottler brightnessThrottler, BrightnessMappingStrategy idleModeBrightnessMapper, int ambientLightHorizonShort, int ambientLightHorizonLong, float userLux, float userBrightness) {
        this.mScreenAutoBrightness = Float.NaN;
        this.mRawScreenAutoBrightness = Float.NaN;
        this.mDisplayPolicy = 0;
        this.mState = 2;
        this.mLightSensorListener = new SensorEventListener() { // from class: com.android.server.display.AutomaticBrightnessController.2
            @Override // android.hardware.SensorEventListener
            public void onSensorChanged(SensorEvent event) {
                if (AutomaticBrightnessController.this.mLightSensorEnabled) {
                    long time = AutomaticBrightnessController.this.mClock.uptimeMillis();
                    float lux = event.values[0];
                    AutomaticBrightnessController.this.handleLightSensorEvent(time, lux);
                }
            }

            @Override // android.hardware.SensorEventListener
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
            }
        };
        this.mDualSensorPolicyListener = new AutomaticBrightnessControllerStub.DualSensorPolicyListener() { // from class: com.android.server.display.AutomaticBrightnessController.3
            @Override // com.android.server.display.AutomaticBrightnessControllerStub.DualSensorPolicyListener
            public float getBrighteningThreshold() {
                return AutomaticBrightnessController.this.mAmbientBrighteningThreshold;
            }

            @Override // com.android.server.display.AutomaticBrightnessControllerStub.DualSensorPolicyListener
            public float getDarkeningThreshold() {
                return AutomaticBrightnessController.this.mAmbientDarkeningThreshold;
            }

            @Override // com.android.server.display.AutomaticBrightnessControllerStub.DualSensorPolicyListener
            public void updateAmbientLux(int event, float lux, boolean needUpdateLux, boolean needUpdateBrightness) {
                AutomaticBrightnessController.this.setAmbientLux(event, lux, needUpdateLux, needUpdateBrightness);
            }

            @Override // com.android.server.display.AutomaticBrightnessControllerStub.DualSensorPolicyListener
            public float calculateSlowAmbientLux(long time) {
                AutomaticBrightnessController automaticBrightnessController = AutomaticBrightnessController.this;
                return automaticBrightnessController.calculateAmbientLux(time, automaticBrightnessController.mAmbientLightHorizonLong);
            }

            @Override // com.android.server.display.AutomaticBrightnessControllerStub.DualSensorPolicyListener
            public float calculateFastAmbientLux(long time) {
                AutomaticBrightnessController automaticBrightnessController = AutomaticBrightnessController.this;
                return automaticBrightnessController.calculateAmbientLux(time, automaticBrightnessController.mAmbientLightHorizonShort);
            }

            @Override // com.android.server.display.AutomaticBrightnessControllerStub.DualSensorPolicyListener
            public long updateNextBrighteningTransition(long time) {
                return AutomaticBrightnessController.this.nextAmbientLightBrighteningTransition(time);
            }

            @Override // com.android.server.display.AutomaticBrightnessControllerStub.DualSensorPolicyListener
            public long updateNextDarkeningTransition(long time) {
                return AutomaticBrightnessController.this.nextAmbientLightDarkeningTransition(time);
            }

            @Override // com.android.server.display.AutomaticBrightnessControllerStub.DualSensorPolicyListener
            public void sendUpdateAmbientLuxMessage(long time) {
                AutomaticBrightnessController.this.mHandler.sendEmptyMessageAtTime(1, time);
            }

            @Override // com.android.server.display.AutomaticBrightnessControllerStub.DualSensorPolicyListener
            public void removeUpdateAmbientLuxMessage() {
                AutomaticBrightnessController.this.mHandler.removeMessages(1);
            }

            @Override // com.android.server.display.AutomaticBrightnessControllerStub.DualSensorPolicyListener
            public boolean useDaemonSensorPolicyInProgress() {
                return AutomaticBrightnessController.this.mUseDaemonSensorInProgress;
            }

            @Override // com.android.server.display.AutomaticBrightnessControllerStub.DualSensorPolicyListener
            public float getAmbientLux() {
                return AutomaticBrightnessController.this.mAmbientLux;
            }
        };
        this.mInjector = injector;
        this.mClock = injector.createClock();
        this.mContext = context;
        this.mCallbacks = callbacks;
        this.mSensorManager = sensorManager;
        this.mCurrentBrightnessMapper = interactiveModeBrightnessMapper;
        this.mScreenBrightnessRangeMinimum = brightnessMin;
        this.mScreenBrightnessRangeMaximum = brightnessMax;
        this.mLightSensorWarmUpTimeConfig = lightSensorWarmUpTime;
        this.mDozeScaleFactor = dozeScaleFactor;
        this.mNormalLightSensorRate = lightSensorRate;
        this.mInitialLightSensorRate = initialLightSensorRate;
        this.mCurrentLightSensorRate = -1;
        this.mBrighteningLightDebounceConfig = brighteningLightDebounceConfig;
        this.mDarkeningLightDebounceConfig = darkeningLightDebounceConfig;
        this.mResetAmbientLuxAfterWarmUpConfig = resetAmbientLuxAfterWarmUpConfig;
        this.mAmbientLightHorizonLong = ambientLightHorizonLong;
        this.mAmbientLightHorizonShort = ambientLightHorizonShort;
        this.mWeightingIntercept = ambientLightHorizonLong;
        this.mAmbientBrightnessThresholds = ambientBrightnessThresholds;
        this.mAmbientBrightnessThresholdsIdle = ambientBrightnessThresholdsIdle;
        this.mScreenBrightnessThresholds = screenBrightnessThresholds;
        this.mScreenBrightnessThresholdsIdle = screenBrightnessThresholdsIdle;
        this.mShortTermModel = new ShortTermModel();
        this.mPausedShortTermModel = new ShortTermModel();
        this.mHandler = new AutomaticBrightnessHandler(looper);
        this.mAmbientLightRingBuffer = new AmbientLightRingBuffer(lightSensorRate, ambientLightHorizonLong, this.mClock);
        this.mLightSensor = lightSensor;
        HysteresisLevelsStub hysteresisLevelsStub = HysteresisLevelsStub.getInstance();
        this.mHysteresisLevelsImpl = hysteresisLevelsStub;
        hysteresisLevelsStub.initialize(hbmController, ambientBrightnessThresholds);
        AutomaticBrightnessControllerStub automaticBrightnessControllerStub = AutomaticBrightnessControllerStub.getInstance();
        this.mAutomaticBrightnessControllerImpl = automaticBrightnessControllerStub;
        automaticBrightnessControllerStub.initialize(sensorManager, this.mContext, looper, lightSensor, lightSensorWarmUpTime, lightSensorRate, brighteningLightDebounceConfig, darkeningLightDebounceConfig, ambientLightHorizonLong, ambientLightHorizonShort, this.mHysteresisLevelsImpl, this.mDualSensorPolicyListener, this);
        this.mSupportDualSensorPolicy = this.mAutomaticBrightnessControllerImpl.supportDualSensorPolicy();
        this.mActivityTaskManager = ActivityTaskManager.getService();
        this.mPackageManager = this.mContext.getPackageManager();
        this.mTaskStackListener = new TaskStackListenerImpl();
        this.mForegroundAppPackageName = null;
        this.mPendingForegroundAppPackageName = null;
        this.mForegroundAppCategory = -1;
        this.mPendingForegroundAppCategory = -1;
        this.mHbmController = hbmController;
        this.mBrightnessThrottler = brightnessThrottler;
        this.mInteractiveModeBrightnessMapper = interactiveModeBrightnessMapper;
        this.mIdleModeBrightnessMapper = idleModeBrightnessMapper;
        switchToInteractiveScreenBrightnessMode();
        setScreenBrightnessByUser(userLux, userBrightness);
    }

    public boolean setLoggingEnabled(boolean loggingEnabled) {
        if (this.mLoggingEnabled == loggingEnabled) {
            return false;
        }
        BrightnessMappingStrategy brightnessMappingStrategy = this.mInteractiveModeBrightnessMapper;
        if (brightnessMappingStrategy != null) {
            brightnessMappingStrategy.setLoggingEnabled(loggingEnabled);
        }
        BrightnessMappingStrategy brightnessMappingStrategy2 = this.mIdleModeBrightnessMapper;
        if (brightnessMappingStrategy2 != null) {
            brightnessMappingStrategy2.setLoggingEnabled(loggingEnabled);
        }
        this.mLoggingEnabled = loggingEnabled;
        return true;
    }

    public float getAutomaticScreenBrightness() {
        return getAutomaticScreenBrightness(null);
    }

    public float getAutomaticScreenBrightness(BrightnessEvent brightnessEvent) {
        if (brightnessEvent != null) {
            brightnessEvent.setLux(this.mAmbientLuxValid ? this.mAmbientLux : Float.NaN);
            brightnessEvent.setPreThresholdLux(this.mPreThresholdLux);
            brightnessEvent.setPreThresholdBrightness(this.mPreThresholdBrightness);
            brightnessEvent.setRecommendedBrightness(this.mScreenAutoBrightness);
            brightnessEvent.setFlags(brightnessEvent.getFlags() | (!this.mAmbientLuxValid ? 2 : 0) | (this.mDisplayPolicy == 1 ? 4 : 0) | (this.mCurrentBrightnessMapper.isForIdleMode() ? 16 : 0));
        }
        if (!this.mAmbientLuxValid) {
            if (!this.mUseDaemonSensorInProgress || Float.isNaN(this.mScreenAutoBrightness)) {
                return Float.NaN;
            }
            if (DEBUG) {
                Slog.d(TAG, "getAutomaticScreenBrightness: apply daemon sensor policy");
            }
            this.mUseDaemonSensorInProgress = false;
            return this.mScreenAutoBrightness;
        }
        if (this.mDisplayPolicy == 1) {
            return this.mScreenAutoBrightness * this.mDozeScaleFactor;
        }
        return this.mScreenAutoBrightness;
    }

    float getRawAutomaticScreenBrightness() {
        return this.mRawScreenAutoBrightness;
    }

    public boolean hasValidAmbientLux() {
        return this.mAmbientLuxValid;
    }

    public float getAutomaticScreenBrightnessAdjustment() {
        return this.mCurrentBrightnessMapper.getAutoBrightnessAdjustment();
    }

    public void configure(int state, BrightnessConfiguration configuration, float brightness, boolean userChangedBrightness, float adjustment, boolean userChangedAutoBrightnessAdjustment, int displayPolicy, boolean shouldResetShortTermModel) {
        this.mState = state;
        boolean dozing = displayPolicy == 1;
        boolean changed = setBrightnessConfiguration(configuration, shouldResetShortTermModel) | setDisplayPolicy(displayPolicy);
        if (userChangedAutoBrightnessAdjustment) {
            changed |= setAutoBrightnessAdjustment(adjustment);
        }
        boolean enable = this.mState == 1;
        if (userChangedBrightness && enable) {
            changed |= setScreenBrightnessByUser(brightness);
        }
        boolean userInitiatedChange = userChangedBrightness || userChangedAutoBrightnessAdjustment;
        if (userInitiatedChange && enable && !dozing) {
            prepareBrightnessAdjustmentSample();
        }
        boolean changed2 = setLightSensorEnabled(enable && !dozing) | changed;
        if (enable && !dozing && !this.mAmbientLuxValid && Float.isNaN(this.mScreenAutoBrightness)) {
            setDaemonSensorAmbientLux();
        }
        this.mAutomaticBrightnessControllerImpl.configure(this.mState, this.mScreenAutoBrightness, displayPolicy);
        if (this.mIsBrightnessThrottled != this.mBrightnessThrottler.isThrottled()) {
            this.mIsBrightnessThrottled = this.mBrightnessThrottler.isThrottled();
            changed2 = true;
        }
        if (changed2) {
            updateAutoBrightness(false, userInitiatedChange);
        }
    }

    public void stop() {
        setLightSensorEnabled(false);
        this.mAutomaticBrightnessControllerImpl.stop();
    }

    public boolean hasUserDataPoints() {
        return this.mCurrentBrightnessMapper.hasUserDataPoints();
    }

    public boolean isDefaultConfig() {
        if (isInIdleMode()) {
            return false;
        }
        return this.mInteractiveModeBrightnessMapper.isDefaultConfig();
    }

    public BrightnessConfiguration getDefaultConfig() {
        return this.mInteractiveModeBrightnessMapper.getDefaultConfig();
    }

    public void update() {
        this.mHandler.sendEmptyMessage(6);
    }

    float getAmbientLux() {
        return this.mAmbientLux;
    }

    float getSlowAmbientLux() {
        return this.mSlowAmbientLux;
    }

    float getFastAmbientLux() {
        return this.mFastAmbientLux;
    }

    private boolean setDisplayPolicy(int policy) {
        if (this.mDisplayPolicy == policy) {
            return false;
        }
        int oldPolicy = this.mDisplayPolicy;
        this.mDisplayPolicy = policy;
        if (this.mLoggingEnabled) {
            Slog.d(TAG, "Display policy transitioning from " + oldPolicy + " to " + policy);
        }
        if (!isInteractivePolicy(policy) && isInteractivePolicy(oldPolicy) && !isInIdleMode()) {
            this.mHandler.sendEmptyMessageDelayed(3, this.mCurrentBrightnessMapper.getShortTermModelTimeout());
            return true;
        }
        if (isInteractivePolicy(policy) && !isInteractivePolicy(oldPolicy)) {
            this.mHandler.removeMessages(3);
            return true;
        }
        return true;
    }

    private static boolean isInteractivePolicy(int policy) {
        return policy == 3 || policy == 2;
    }

    private boolean setScreenBrightnessByUser(float brightness) {
        if (!this.mAmbientLuxValid) {
            return false;
        }
        return setScreenBrightnessByUser(this.mAmbientLux, brightness);
    }

    private boolean setScreenBrightnessByUser(float lux, float brightness) {
        if (lux == -1.0f || brightness == -1.0f) {
            return false;
        }
        AutomaticBrightnessControllerStub automaticBrightnessControllerStub = this.mAutomaticBrightnessControllerImpl;
        if (automaticBrightnessControllerStub != null) {
            automaticBrightnessControllerStub.setScreenBrightnessByUser(lux, brightness, this.mForegroundAppPackageName);
        }
        this.mCurrentBrightnessMapper.addUserDataPoint(lux, brightness);
        this.mShortTermModel.setUserBrightness(lux, brightness);
        return true;
    }

    public void resetShortTermModel() {
        this.mCurrentBrightnessMapper.clearUserDataPoints();
        this.mShortTermModel.reset();
    }

    public boolean setBrightnessConfiguration(BrightnessConfiguration configuration, boolean shouldResetShortTermModel) {
        if (this.mInteractiveModeBrightnessMapper.setBrightnessConfiguration(configuration)) {
            if (!isInIdleMode() && shouldResetShortTermModel) {
                resetShortTermModel();
                return true;
            }
            return true;
        }
        return false;
    }

    public boolean isInIdleMode() {
        return this.mCurrentBrightnessMapper.isForIdleMode();
    }

    public void dump(PrintWriter pw) {
        pw.println();
        pw.println("Automatic Brightness Controller Configuration:");
        pw.println("  mState=" + configStateToString(this.mState));
        pw.println("  mScreenBrightnessRangeMinimum=" + this.mScreenBrightnessRangeMinimum);
        pw.println("  mScreenBrightnessRangeMaximum=" + this.mScreenBrightnessRangeMaximum);
        pw.println("  mDozeScaleFactor=" + this.mDozeScaleFactor);
        pw.println("  mInitialLightSensorRate=" + this.mInitialLightSensorRate);
        pw.println("  mNormalLightSensorRate=" + this.mNormalLightSensorRate);
        pw.println("  mLightSensorWarmUpTimeConfig=" + this.mLightSensorWarmUpTimeConfig);
        pw.println("  mBrighteningLightDebounceConfig=" + this.mBrighteningLightDebounceConfig);
        pw.println("  mDarkeningLightDebounceConfig=" + this.mDarkeningLightDebounceConfig);
        pw.println("  mResetAmbientLuxAfterWarmUpConfig=" + this.mResetAmbientLuxAfterWarmUpConfig);
        pw.println("  mAmbientLightHorizonLong=" + this.mAmbientLightHorizonLong);
        pw.println("  mAmbientLightHorizonShort=" + this.mAmbientLightHorizonShort);
        pw.println("  mWeightingIntercept=" + this.mWeightingIntercept);
        pw.println();
        pw.println("Automatic Brightness Controller State:");
        pw.println("  mLightSensor=" + this.mLightSensor);
        pw.println("  mLightSensorEnabled=" + this.mLightSensorEnabled);
        pw.println("  mLightSensorEnableTime=" + TimeUtils.formatUptime(this.mLightSensorEnableTime));
        pw.println("  mCurrentLightSensorRate=" + this.mCurrentLightSensorRate);
        pw.println("  mAmbientLux=" + this.mAmbientLux);
        pw.println("  mAmbientLuxValid=" + this.mAmbientLuxValid);
        pw.println("  mPreThesholdLux=" + this.mPreThresholdLux);
        pw.println("  mPreThesholdBrightness=" + this.mPreThresholdBrightness);
        pw.println("  mAutoBrightnessValid=" + this.mAutoBrightnessValid);
        pw.println("  mAmbientBrighteningThreshold=" + this.mAmbientBrighteningThreshold);
        pw.println("  mAmbientDarkeningThreshold=" + this.mAmbientDarkeningThreshold);
        pw.println("  mScreenBrighteningThreshold=" + this.mScreenBrighteningThreshold);
        pw.println("  mScreenDarkeningThreshold=" + this.mScreenDarkeningThreshold);
        pw.println("  mLastObservedLux=" + this.mLastObservedLux);
        pw.println("  mLastObservedLuxTime=" + TimeUtils.formatUptime(this.mLastObservedLuxTime));
        pw.println("  mRecentLightSamples=" + this.mRecentLightSamples);
        pw.println("  mAmbientLightRingBuffer=" + this.mAmbientLightRingBuffer);
        pw.println("  mScreenAutoBrightness=" + this.mScreenAutoBrightness);
        pw.println("  mDisplayPolicy=" + DisplayManagerInternal.DisplayPowerRequest.policyToString(this.mDisplayPolicy));
        pw.println("  mShortTermModelTimeout(active)=" + this.mInteractiveModeBrightnessMapper.getShortTermModelTimeout());
        if (this.mIdleModeBrightnessMapper != null) {
            pw.println("  mShortTermModelTimeout(idle)=" + this.mIdleModeBrightnessMapper.getShortTermModelTimeout());
        }
        pw.println("  mShortTermModel=");
        this.mShortTermModel.dump(pw);
        pw.println("  mPausedShortTermModel=");
        this.mPausedShortTermModel.dump(pw);
        pw.println();
        pw.println("  mBrightnessAdjustmentSamplePending=" + this.mBrightnessAdjustmentSamplePending);
        pw.println("  mBrightnessAdjustmentSampleOldLux=" + this.mBrightnessAdjustmentSampleOldLux);
        pw.println("  mBrightnessAdjustmentSampleOldBrightness=" + this.mBrightnessAdjustmentSampleOldBrightness);
        pw.println("  mForegroundAppPackageName=" + this.mForegroundAppPackageName);
        pw.println("  mPendingForegroundAppPackageName=" + this.mPendingForegroundAppPackageName);
        pw.println("  mForegroundAppCategory=" + this.mForegroundAppCategory);
        pw.println("  mPendingForegroundAppCategory=" + this.mPendingForegroundAppCategory);
        pw.println("  Idle mode active=" + this.mCurrentBrightnessMapper.isForIdleMode());
        pw.println();
        pw.println("  mInteractiveMapper=");
        this.mInteractiveModeBrightnessMapper.dump(pw, this.mHbmController.getNormalBrightnessMax());
        if (this.mIdleModeBrightnessMapper != null) {
            pw.println("  mIdleMapper=");
            this.mIdleModeBrightnessMapper.dump(pw, this.mHbmController.getNormalBrightnessMax());
        }
        pw.println();
        pw.println("  mAmbientBrightnessThresholds=");
        this.mAmbientBrightnessThresholds.dump(pw);
        pw.println("  mScreenBrightnessThresholds=");
        this.mScreenBrightnessThresholds.dump(pw);
        pw.println("  mScreenBrightnessThresholdsIdle=");
        this.mScreenBrightnessThresholdsIdle.dump(pw);
        pw.println("  mAmbientBrightnessThresholdsIdle=");
        this.mAmbientBrightnessThresholdsIdle.dump(pw);
        DEBUG = DisplayDebugConfig.DEBUG_ABC;
        this.mAutomaticBrightnessControllerImpl.dump(pw);
    }

    public float[] getLastSensorValues() {
        return this.mAmbientLightRingBuffer.getAllLuxValues();
    }

    public long[] getLastSensorTimestamps() {
        return this.mAmbientLightRingBuffer.getAllTimestamps();
    }

    private String configStateToString(int state) {
        switch (state) {
            case 1:
                return "AUTO_BRIGHTNESS_ENABLED";
            case 2:
                return "AUTO_BRIGHTNESS_DISABLED";
            case 3:
                return "AUTO_BRIGHTNESS_OFF_DUE_TO_DISPLAY_STATE";
            default:
                return String.valueOf(state);
        }
    }

    private boolean setLightSensorEnabled(boolean enable) {
        if (enable) {
            if (!this.mLightSensorEnabled) {
                this.mLightSensorEnabled = true;
                this.mLightSensorEnableTime = this.mClock.uptimeMillis();
                this.mCurrentLightSensorRate = this.mInitialLightSensorRate;
                registerForegroundAppUpdater();
                Sensor lightSensor = this.mAutomaticBrightnessControllerImpl.switchLightSensor(this.mLightSensor);
                this.mSensorManager.registerListener(this.mLightSensorListener, lightSensor, this.mCurrentLightSensorRate * 1000, this.mHandler);
                return true;
            }
        } else if (this.mLightSensorEnabled) {
            this.mLightSensorEnabled = false;
            boolean z = !this.mResetAmbientLuxAfterWarmUpConfig;
            this.mAmbientLuxValid = z;
            if (!z) {
                this.mPreThresholdLux = Float.NaN;
            }
            this.mScreenAutoBrightness = Float.NaN;
            this.mRawScreenAutoBrightness = Float.NaN;
            this.mPreThresholdBrightness = Float.NaN;
            this.mRecentLightSamples = 0;
            this.mAmbientLightRingBuffer.clear();
            this.mCurrentLightSensorRate = -1;
            this.mHandler.removeMessages(1);
            unregisterForegroundAppUpdater();
            this.mSensorManager.unregisterListener(this.mLightSensorListener);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleLightSensorEvent(long time, float lux) {
        Trace.traceCounter(131072L, "ALS", (int) lux);
        this.mHandler.removeMessages(1);
        if (this.mAmbientLightRingBuffer.size() == 0) {
            adjustLightSensorRate(this.mNormalLightSensorRate);
        }
        if (this.mAmbientLuxValid) {
            if (!this.mAutomaticBrightnessControllerImpl.dropAmbientLuxIfNeeded()) {
                if (lux <= this.mAmbientLightRingBuffer.getMaxLux(time, this.mAmbientLightHorizonShort) && this.mAutomaticBrightnessControllerImpl.dropDecreaseLuxIfNeeded()) {
                    return;
                }
            } else {
                return;
            }
        }
        applyLightSensorMeasurement(time, lux);
        updateAmbientLux(time);
    }

    private void applyLightSensorMeasurement(long time, float lux) {
        this.mRecentLightSamples++;
        this.mAmbientLightRingBuffer.prune(time - ((long) this.mAmbientLightHorizonLong));
        this.mAmbientLightRingBuffer.push(time, lux);
        this.mLastObservedLux = lux;
        this.mLastObservedLuxTime = time;
    }

    private void adjustLightSensorRate(int lightSensorRate) {
        if (lightSensorRate != this.mCurrentLightSensorRate) {
            if (this.mLoggingEnabled) {
                Slog.d(TAG, "adjustLightSensorRate: previousRate=" + this.mCurrentLightSensorRate + ", currentRate=" + lightSensorRate);
            }
            this.mCurrentLightSensorRate = lightSensorRate;
            this.mSensorManager.unregisterListener(this.mLightSensorListener);
            this.mSensorManager.registerListener(this.mLightSensorListener, this.mLightSensor, lightSensorRate * 1000, this.mHandler);
        }
    }

    private boolean setAutoBrightnessAdjustment(float adjustment) {
        return this.mCurrentBrightnessMapper.setAutoBrightnessAdjustment(adjustment);
    }

    private void setAmbientLux(float lux) {
        if (this.mLoggingEnabled) {
            Slog.d(TAG, "setAmbientLux(" + lux + ")");
        }
        if (lux < 0.0f) {
            Slog.w(TAG, "Ambient lux was negative, ignoring and setting to 0");
            lux = 0.0f;
        }
        this.mAmbientLux = lux;
        if (isInIdleMode()) {
            this.mAmbientBrighteningThreshold = this.mAmbientBrightnessThresholdsIdle.getBrighteningThreshold(lux);
            this.mAmbientDarkeningThreshold = this.mAmbientBrightnessThresholdsIdle.getDarkeningThreshold(lux);
        } else {
            this.mAmbientBrighteningThreshold = this.mHysteresisLevelsImpl.getBrighteningThreshold(lux);
            this.mAmbientDarkeningThreshold = this.mHysteresisLevelsImpl.getDarkeningThreshold(lux);
        }
        this.mHbmController.onAmbientLuxChange(this.mAmbientLux);
        Slog.i(TAG, "AmbientLux=" + lux + ", mAmbientDarkeningThreshold=" + this.mAmbientDarkeningThreshold + ", mAmbientBrighteningThreshold=" + this.mAmbientBrighteningThreshold);
        resetShortTermModelIfNeeded();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float calculateAmbientLux(long now, long horizon) {
        long j = now;
        if (this.mLoggingEnabled) {
            Slog.d(TAG, "calculateAmbientLux(" + j + ", " + horizon + ")");
        }
        int N = this.mAmbientLightRingBuffer.size();
        if (N == 0) {
            Slog.e(TAG, "calculateAmbientLux: No ambient light readings available");
            return -1.0f;
        }
        int endIndex = 0;
        long horizonStartTime = j - horizon;
        for (int i = 0; i < N - 1 && this.mAmbientLightRingBuffer.getTime(i + 1) <= horizonStartTime; i++) {
            endIndex++;
        }
        if (this.mLoggingEnabled) {
            Slog.d(TAG, "calculateAmbientLux: selected endIndex=" + endIndex + ", point=(" + this.mAmbientLightRingBuffer.getTime(endIndex) + ", " + this.mAmbientLightRingBuffer.getLux(endIndex) + ")");
        }
        float sum = 0.0f;
        float totalWeight = 0.0f;
        long endTime = AMBIENT_LIGHT_PREDICTION_TIME_MILLIS;
        int i2 = N - 1;
        while (i2 >= endIndex) {
            long eventTime = this.mAmbientLightRingBuffer.getTime(i2);
            if (i2 == endIndex && eventTime < horizonStartTime) {
                eventTime = horizonStartTime;
            }
            long horizonStartTime2 = horizonStartTime;
            int endIndex2 = endIndex;
            long startTime = eventTime - j;
            float weight = calculateWeight(startTime, endTime);
            float lux = this.mAmbientLightRingBuffer.getLux(i2);
            if (this.mLoggingEnabled) {
                Slog.d(TAG, "calculateAmbientLux: [" + startTime + ", " + endTime + "]: lux=" + lux + ", weight=" + weight);
            }
            totalWeight += weight;
            sum += lux * weight;
            endTime = startTime;
            i2--;
            j = now;
            endIndex = endIndex2;
            horizonStartTime = horizonStartTime2;
        }
        if (this.mLoggingEnabled) {
            Slog.d(TAG, "calculateAmbientLux: totalWeight=" + totalWeight + ", newAmbientLux=" + (sum / totalWeight));
        }
        return sum / totalWeight;
    }

    private float calculateWeight(long startDelta, long endDelta) {
        return weightIntegral(endDelta) - weightIntegral(startDelta);
    }

    private float weightIntegral(long x) {
        return x * ((x * 0.5f) + this.mWeightingIntercept);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long nextAmbientLightBrighteningTransition(long time) {
        int N = this.mAmbientLightRingBuffer.size();
        long earliestValidTime = time;
        for (int i = N - 1; i >= 0 && this.mAmbientLightRingBuffer.getLux(i) > this.mAmbientBrighteningThreshold; i--) {
            earliestValidTime = this.mAmbientLightRingBuffer.getTime(i);
        }
        return this.mBrighteningLightDebounceConfig + earliestValidTime;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long nextAmbientLightDarkeningTransition(long time) {
        int N = this.mAmbientLightRingBuffer.size();
        long earliestValidTime = time;
        for (int i = N - 1; i >= 0 && this.mAmbientLightRingBuffer.getLux(i) < this.mAmbientDarkeningThreshold; i--) {
            earliestValidTime = this.mAmbientLightRingBuffer.getTime(i);
        }
        return this.mDarkeningLightDebounceConfig + earliestValidTime;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateAmbientLux() {
        long time = this.mClock.uptimeMillis();
        this.mAmbientLightRingBuffer.prune(time - ((long) this.mAmbientLightHorizonLong));
        updateAmbientLux(time);
    }

    /* JADX WARN: Code duplicated, block: B:57:0x0167  */
    /* JADX WARN: Code duplicated, block: B:59:0x0172  */
    /* JADX WARN: Code duplicated, block: B:61:0x0186  */
    /* JADX WARN: Code duplicated, block: B:62:0x0189  */
    private void updateAmbientLux(long time) {
        String str;
        if (!this.mAmbientLuxValid) {
            long timeWhenSensorWarmedUp = ((long) this.mLightSensorWarmUpTimeConfig) + this.mLightSensorEnableTime;
            boolean z = IS_FOLDABLE_DEVICE;
            if (z && !this.mAutoBrightnessValid) {
                timeWhenSensorWarmedUp += 250;
            }
            if (time < timeWhenSensorWarmedUp) {
                if (this.mLoggingEnabled) {
                    Slog.d(TAG, "updateAmbientLux: Sensor not ready yet: time=" + time + ", timeWhenSensorWarmedUp=" + timeWhenSensorWarmedUp);
                }
                this.mHandler.sendEmptyMessageAtTime(1, timeWhenSensorWarmedUp);
                return;
            }
            if (z && !this.mAutoBrightnessValid) {
                int N = this.mAmbientLightRingBuffer.size();
                if (N > 1) {
                    Slog.i(TAG, "clear mAmbientLightRingBuffer=" + this.mAmbientLightRingBuffer + ", use LastObserved(" + this.mLastObservedLuxTime + "," + this.mLastObservedLux + ")");
                    this.mAmbientLightRingBuffer.clear();
                    this.mAmbientLightRingBuffer.push(this.mLastObservedLuxTime, this.mLastObservedLux);
                }
            }
            if (!this.mSupportDualSensorPolicy) {
                setAmbientLux(calculateAmbientLux(time, this.mAmbientLightHorizonShort));
            } else {
                this.mAutomaticBrightnessControllerImpl.setAmbientLuxWhenInvalid(AutomaticBrightnessControllerStub.HANDLE_MAIN_LUX_EVENT, calculateAmbientLux(time, this.mAmbientLightHorizonShort));
            }
            this.mAmbientLuxValid = true;
            this.mAutoBrightnessValid = true;
            this.mAutomaticBrightnessControllerImpl.notifyUnregisterDaemonSensor();
            if (this.mLoggingEnabled) {
                Slog.d(TAG, "updateAmbientLux: Initializing: mAmbientLightRingBuffer=" + this.mAmbientLightRingBuffer + ", mAmbientLux=" + this.mAmbientLux);
            }
            if (!this.mSupportDualSensorPolicy || this.mAutomaticBrightnessControllerImpl.updateBrightnessUsingMainLightSensor()) {
                updateAutoBrightness(true, false);
            }
        }
        if (this.mAmbientLightRingBuffer.mCount > 0) {
            AmbientLightRingBuffer ambientLightRingBuffer = this.mAmbientLightRingBuffer;
            float lux = ambientLightRingBuffer.getLux(ambientLightRingBuffer.mCount - 1);
            if (this.mAutomaticBrightnessControllerImpl.shouldSkipBrighteningTransition(this.mLightSensorEnableTime, lux, this.mAmbientLux, this.mAmbientBrighteningThreshold)) {
                setAmbientLux(AutomaticBrightnessControllerStub.HANDLE_MAIN_LUX_EVENT, lux, true, true);
                return;
            }
        }
        if (this.mSupportDualSensorPolicy && this.mAutomaticBrightnessControllerImpl.updateDualSensorPolicy(time, AutomaticBrightnessControllerStub.HANDLE_MAIN_LUX_EVENT)) {
            return;
        }
        long nextBrightenTransition = nextAmbientLightBrighteningTransition(time);
        long nextDarkenTransition = nextAmbientLightDarkeningTransition(time);
        this.mSlowAmbientLux = calculateAmbientLux(time, this.mAmbientLightHorizonLong);
        float fCalculateAmbientLux = calculateAmbientLux(time, this.mAmbientLightHorizonShort);
        this.mFastAmbientLux = fCalculateAmbientLux;
        float f = this.mSlowAmbientLux;
        float f2 = this.mAmbientBrighteningThreshold;
        if (f < f2 || fCalculateAmbientLux < f2 || nextBrightenTransition > time) {
            float f3 = this.mAmbientDarkeningThreshold;
            if (f <= f3 && fCalculateAmbientLux <= f3 && nextDarkenTransition <= time) {
                this.mPreThresholdLux = this.mAmbientLux;
                setAmbientLux(fCalculateAmbientLux);
                if (this.mLoggingEnabled) {
                    StringBuilder sbAppend = new StringBuilder().append("updateAmbientLux: ");
                    if (this.mFastAmbientLux > this.mAmbientLux) {
                        str = "Brightened";
                    } else {
                        str = "Darkened";
                    }
                    Slog.d(TAG, sbAppend.append(str).append(": mAmbientBrighteningThreshold=").append(this.mAmbientBrighteningThreshold).append(", mAmbientDarkeningThreshold=").append(this.mAmbientDarkeningThreshold).append(", mAmbientLightRingBuffer=").append(this.mAmbientLightRingBuffer).append(", mAmbientLux=").append(this.mAmbientLux).toString());
                }
                updateAutoBrightness(true, false);
                nextBrightenTransition = nextAmbientLightBrighteningTransition(time);
                nextDarkenTransition = nextAmbientLightDarkeningTransition(time);
            }
        } else {
            this.mPreThresholdLux = this.mAmbientLux;
            setAmbientLux(fCalculateAmbientLux);
            if (this.mLoggingEnabled) {
                StringBuilder sbAppend2 = new StringBuilder().append("updateAmbientLux: ");
                if (this.mFastAmbientLux > this.mAmbientLux) {
                    str = "Brightened";
                } else {
                    str = "Darkened";
                }
                Slog.d(TAG, sbAppend2.append(str).append(": mAmbientBrighteningThreshold=").append(this.mAmbientBrighteningThreshold).append(", mAmbientDarkeningThreshold=").append(this.mAmbientDarkeningThreshold).append(", mAmbientLightRingBuffer=").append(this.mAmbientLightRingBuffer).append(", mAmbientLux=").append(this.mAmbientLux).toString());
            }
            updateAutoBrightness(true, false);
            nextBrightenTransition = nextAmbientLightBrighteningTransition(time);
            nextDarkenTransition = nextAmbientLightDarkeningTransition(time);
        }
        long nextTransitionTime = Math.min(nextDarkenTransition, nextBrightenTransition);
        long nextTransitionTime2 = nextTransitionTime > time ? nextTransitionTime : ((long) this.mNormalLightSensorRate) + time;
        if (this.mLoggingEnabled) {
            Slog.d(TAG, "updateAmbientLux: Scheduling ambient lux update for " + nextTransitionTime2 + TimeUtils.formatUptime(nextTransitionTime2));
        }
        this.mHandler.sendEmptyMessageAtTime(1, nextTransitionTime2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateAutoBrightness(boolean sendUpdate, boolean isManuallySet) {
        if (!this.mAmbientLuxValid && !this.mUseDaemonSensorInProgress) {
            return;
        }
        float value = this.mAutomaticBrightnessControllerImpl.getCustomBrightness(this.mAmbientLux, this.mForegroundAppPackageName, this.mForegroundAppCategory, this.mScreenAutoBrightness, this.mCurrentBrightnessMapper.getBrightness(this.mAmbientLux, this.mForegroundAppPackageName, this.mForegroundAppCategory), isManuallySet);
        this.mRawScreenAutoBrightness = value;
        float newScreenAutoBrightness = clampScreenBrightness(value);
        float f = this.mScreenAutoBrightness;
        boolean currentBrightnessWithinAllowedRange = BrightnessSynchronizer.floatEquals(f, clampScreenBrightness(f));
        boolean withinThreshold = !Float.isNaN(this.mScreenAutoBrightness) && newScreenAutoBrightness > this.mScreenDarkeningThreshold && newScreenAutoBrightness < this.mScreenBrighteningThreshold;
        if (withinThreshold && !isManuallySet && currentBrightnessWithinAllowedRange) {
            if (this.mLoggingEnabled) {
                Slog.d(TAG, "ignoring newScreenAutoBrightness: " + this.mScreenDarkeningThreshold + " < " + newScreenAutoBrightness + " < " + this.mScreenBrighteningThreshold);
            }
        } else if (!BrightnessSynchronizer.floatEquals(this.mScreenAutoBrightness, newScreenAutoBrightness)) {
            Slog.i(TAG, "updateAutoBrightness: mScreenAutoBrightness=" + this.mScreenAutoBrightness + ", newScreenAutoBrightness=" + newScreenAutoBrightness + " (checkedAutoBrightness=" + BrightnessSynchronizer.brightnessFloatToInt(newScreenAutoBrightness) + "), mAmbientLux=" + this.mAmbientLux + ", mUseDaemonSensorInProgress=" + this.mUseDaemonSensorInProgress);
            if (!withinThreshold) {
                this.mPreThresholdBrightness = this.mScreenAutoBrightness;
            }
            this.mScreenAutoBrightness = newScreenAutoBrightness;
            if (isInIdleMode()) {
                this.mScreenBrighteningThreshold = clampScreenBrightness(this.mScreenBrightnessThresholdsIdle.getBrighteningThreshold(newScreenAutoBrightness));
                this.mScreenDarkeningThreshold = clampScreenBrightness(this.mScreenBrightnessThresholdsIdle.getDarkeningThreshold(newScreenAutoBrightness));
            } else {
                this.mScreenBrighteningThreshold = clampScreenBrightness(this.mScreenBrightnessThresholds.getBrighteningThreshold(newScreenAutoBrightness));
                this.mScreenDarkeningThreshold = clampScreenBrightness(this.mScreenBrightnessThresholds.getDarkeningThreshold(newScreenAutoBrightness));
            }
            if (sendUpdate) {
                this.mCallbacks.updateBrightness();
            }
        }
    }

    private float clampScreenBrightness(float value) {
        float minBrightness = Math.min(this.mHbmController.getCurrentBrightnessMin(), this.mBrightnessThrottler.getBrightnessCap());
        float maxBrightness = Math.min(this.mHbmController.getCurrentBrightnessMax(), this.mBrightnessThrottler.getBrightnessCap());
        return MathUtils.constrain(value, minBrightness, maxBrightness);
    }

    private void prepareBrightnessAdjustmentSample() {
        if (!this.mBrightnessAdjustmentSamplePending) {
            this.mBrightnessAdjustmentSamplePending = true;
            this.mBrightnessAdjustmentSampleOldLux = this.mAmbientLuxValid ? this.mAmbientLux : -1.0f;
            this.mBrightnessAdjustmentSampleOldBrightness = this.mScreenAutoBrightness;
        } else {
            this.mHandler.removeMessages(2);
        }
        this.mHandler.sendEmptyMessageDelayed(2, 10000L);
    }

    private void cancelBrightnessAdjustmentSample() {
        if (this.mBrightnessAdjustmentSamplePending) {
            this.mBrightnessAdjustmentSamplePending = false;
            this.mHandler.removeMessages(2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void collectBrightnessAdjustmentSample() {
        if (this.mBrightnessAdjustmentSamplePending) {
            this.mBrightnessAdjustmentSamplePending = false;
            if (this.mAmbientLuxValid) {
                float f = this.mScreenAutoBrightness;
                if (f >= 0.0f || f == -1.0f) {
                    if (this.mLoggingEnabled) {
                        Slog.d(TAG, "Auto-brightness adjustment changed by user: lux=" + this.mAmbientLux + ", brightness=" + this.mScreenAutoBrightness + ", ring=" + this.mAmbientLightRingBuffer);
                    }
                    EventLog.writeEvent(EventLogTags.AUTO_BRIGHTNESS_ADJ, Float.valueOf(this.mBrightnessAdjustmentSampleOldLux), Float.valueOf(this.mBrightnessAdjustmentSampleOldBrightness), Float.valueOf(this.mAmbientLux), Float.valueOf(this.mScreenAutoBrightness));
                }
            }
        }
    }

    private void registerForegroundAppUpdater() {
        try {
            this.mActivityTaskManager.registerTaskStackListener(this.mTaskStackListener);
            updateForegroundApp();
        } catch (RemoteException e) {
            if (this.mLoggingEnabled) {
                Slog.e(TAG, "Failed to register foreground app updater: " + e);
            }
        }
    }

    private void unregisterForegroundAppUpdater() {
        try {
            this.mActivityTaskManager.unregisterTaskStackListener(this.mTaskStackListener);
        } catch (RemoteException e) {
        }
        this.mForegroundAppCategory = -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateForegroundApp() {
        if (this.mLoggingEnabled) {
            Slog.d(TAG, "Attempting to update foreground app");
        }
        this.mInjector.getBackgroundThreadHandler().post(new Runnable() { // from class: com.android.server.display.AutomaticBrightnessController.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    ActivityTaskManager.RootTaskInfo info = AutomaticBrightnessController.this.mActivityTaskManager.getFocusedRootTaskInfo();
                    if (info != null && info.topActivity != null && info.getWindowingMode() != 5 && info.getWindowingMode() != 6 && !AutomaticBrightnessController.this.mActivityTaskManager.isInSplitScreenWindowingMode()) {
                        String packageName = info.topActivity.getPackageName();
                        String currentForegroundAppPackageName = AutomaticBrightnessController.this.mForegroundAppPackageName;
                        if (currentForegroundAppPackageName != null && currentForegroundAppPackageName.equals(packageName)) {
                            return;
                        }
                        AutomaticBrightnessController.this.mPendingForegroundAppPackageName = packageName;
                        AutomaticBrightnessController.this.mPendingForegroundAppCategory = -1;
                        try {
                            ApplicationInfo app = AutomaticBrightnessController.this.mPackageManager.getApplicationInfo(packageName, 4194304);
                            AutomaticBrightnessController.this.mPendingForegroundAppCategory = app.category;
                        } catch (PackageManager.NameNotFoundException e) {
                        }
                        AutomaticBrightnessController.this.mHandler.sendEmptyMessage(5);
                    }
                } catch (RemoteException e2) {
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateForegroundAppSync() {
        if (this.mLoggingEnabled) {
            Slog.d(TAG, "Updating foreground app: packageName=" + this.mPendingForegroundAppPackageName + ", category=" + this.mPendingForegroundAppCategory);
        }
        String str = this.mPendingForegroundAppPackageName;
        this.mForegroundAppPackageName = str;
        this.mPendingForegroundAppPackageName = null;
        this.mForegroundAppCategory = this.mPendingForegroundAppCategory;
        this.mPendingForegroundAppCategory = -1;
        updateCustomSceneState(str);
        updateAutoBrightness(true, false);
    }

    void switchToIdleMode() {
        if (this.mIdleModeBrightnessMapper == null || this.mCurrentBrightnessMapper.isForIdleMode()) {
            return;
        }
        Slog.i(TAG, "Switching to Idle Screen Brightness Mode");
        ShortTermModel tempShortTermModel = new ShortTermModel();
        tempShortTermModel.set(this.mCurrentBrightnessMapper.getUserLux(), this.mCurrentBrightnessMapper.getUserBrightness(), true);
        this.mHandler.sendEmptyMessageAtTime(7, this.mClock.uptimeMillis() + this.mCurrentBrightnessMapper.getShortTermModelTimeout());
        Slog.i(TAG, "mPreviousShortTermModel" + this.mPausedShortTermModel);
        this.mCurrentBrightnessMapper = this.mIdleModeBrightnessMapper;
        ShortTermModel shortTermModel = this.mPausedShortTermModel;
        if (shortTermModel != null && !shortTermModel.maybeReset(this.mAmbientLux)) {
            setScreenBrightnessByUser(this.mPausedShortTermModel.mAnchor, this.mPausedShortTermModel.mBrightness);
        }
        this.mPausedShortTermModel.copyFrom(tempShortTermModel);
        update();
    }

    void switchToInteractiveScreenBrightnessMode() {
        if (!this.mCurrentBrightnessMapper.isForIdleMode()) {
            return;
        }
        Slog.i(TAG, "Switching to Interactive Screen Brightness Mode");
        ShortTermModel tempShortTermModel = new ShortTermModel();
        tempShortTermModel.set(this.mCurrentBrightnessMapper.getUserLux(), this.mCurrentBrightnessMapper.getUserBrightness(), true);
        this.mHandler.removeMessages(7);
        this.mHandler.sendEmptyMessageAtTime(7, this.mClock.uptimeMillis() + this.mCurrentBrightnessMapper.getShortTermModelTimeout());
        Slog.i(TAG, "mPreviousShortTermModel" + this.mPausedShortTermModel.toString());
        this.mCurrentBrightnessMapper = this.mInteractiveModeBrightnessMapper;
        if (!this.mPausedShortTermModel.maybeReset(this.mAmbientLux)) {
            setScreenBrightnessByUser(this.mPausedShortTermModel.mAnchor, this.mPausedShortTermModel.mBrightness);
        }
        this.mPausedShortTermModel.copyFrom(tempShortTermModel);
        update();
    }

    public float convertToNits(float brightness) {
        BrightnessMappingStrategy brightnessMappingStrategy = this.mCurrentBrightnessMapper;
        if (brightnessMappingStrategy != null) {
            return brightnessMappingStrategy.convertToNits(brightness);
        }
        return -1.0f;
    }

    public float convertToAdjustedNits(float brightness) {
        BrightnessMappingStrategy brightnessMappingStrategy = this.mCurrentBrightnessMapper;
        if (brightnessMappingStrategy != null) {
            return brightnessMappingStrategy.convertToAdjustedNits(brightness);
        }
        return -1.0f;
    }

    public float convertToFloatScale(float nits) {
        BrightnessMappingStrategy brightnessMappingStrategy = this.mCurrentBrightnessMapper;
        if (brightnessMappingStrategy != null) {
            return brightnessMappingStrategy.convertToFloatScale(nits);
        }
        return Float.NaN;
    }

    public void recalculateSplines(boolean applyAdjustment, float[] adjustment) {
        this.mCurrentBrightnessMapper.recalculateSplines(applyAdjustment, adjustment);
        resetShortTermModel();
        if (applyAdjustment) {
            setScreenBrightnessByUser(getAutomaticScreenBrightness());
        }
    }

    private void resetShortTermModelIfNeeded() {
        this.mShortTermModel.maybeReset(this.mAmbientLux);
    }

    private class ShortTermModel {
        private float mAnchor;
        private float mBrightness;
        private boolean mIsValid;

        private ShortTermModel() {
            this.mAnchor = -1.0f;
            this.mBrightness = -1.0f;
            this.mIsValid = false;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void reset() {
            this.mAnchor = -1.0f;
            this.mBrightness = -1.0f;
            this.mIsValid = false;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void invalidate() {
            this.mIsValid = false;
            if (AutomaticBrightnessController.this.mLoggingEnabled) {
                Slog.d(AutomaticBrightnessController.TAG, "ShortTermModel: invalidate user data");
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setUserBrightness(float lux, float brightness) {
            this.mAnchor = lux;
            this.mBrightness = brightness;
            this.mIsValid = true;
            if (AutomaticBrightnessController.this.mLoggingEnabled) {
                Slog.d(AutomaticBrightnessController.TAG, "ShortTermModel: anchor=" + this.mAnchor);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean maybeReset(float currentLux) {
            if (!this.mIsValid && this.mAnchor != -1.0f) {
                if (AutomaticBrightnessController.this.mCurrentBrightnessMapper != null && AutomaticBrightnessController.this.mCurrentBrightnessMapper.shouldResetShortTermModel(currentLux, this.mAnchor) && !AutomaticBrightnessController.this.mAutomaticBrightnessControllerImpl.isDisableResetShortTermModel()) {
                    AutomaticBrightnessController.this.resetShortTermModel();
                } else {
                    this.mIsValid = true;
                }
                return this.mIsValid;
            }
            return false;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void set(float anchor, float brightness, boolean valid) {
            this.mAnchor = anchor;
            this.mBrightness = brightness;
            this.mIsValid = valid;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void copyFrom(ShortTermModel from) {
            this.mAnchor = from.mAnchor;
            this.mBrightness = from.mBrightness;
            this.mIsValid = from.mIsValid;
        }

        public String toString() {
            return " mAnchor: " + this.mAnchor + "\n mBrightness: " + this.mBrightness + "\n mIsValid: " + this.mIsValid;
        }

        void dump(PrintWriter pw) {
            pw.println(this);
        }
    }

    private final class AutomaticBrightnessHandler extends Handler {
        public AutomaticBrightnessHandler(Looper looper) {
            super(looper, null, true);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    AutomaticBrightnessController.this.updateAmbientLux();
                    break;
                case 2:
                    AutomaticBrightnessController.this.collectBrightnessAdjustmentSample();
                    break;
                case 3:
                    AutomaticBrightnessController.this.mShortTermModel.invalidate();
                    break;
                case 4:
                    AutomaticBrightnessController.this.updateForegroundApp();
                    break;
                case 5:
                    AutomaticBrightnessController.this.updateForegroundAppSync();
                    break;
                case 6:
                    AutomaticBrightnessController.this.updateAutoBrightness(true, false);
                    break;
                case 7:
                    AutomaticBrightnessController.this.mPausedShortTermModel.invalidate();
                    break;
            }
        }
    }

    class TaskStackListenerImpl extends TaskStackListener {
        TaskStackListenerImpl() {
        }

        public void onTaskStackChanged() {
            AutomaticBrightnessController.this.mHandler.sendEmptyMessage(4);
        }
    }

    private static final class AmbientLightRingBuffer {
        private static final float BUFFER_SLACK = 1.5f;
        private int mCapacity;
        Clock mClock;
        private int mCount;
        private int mEnd;
        private float[] mRingLux;
        private long[] mRingTime;
        private int mStart;

        public AmbientLightRingBuffer(long lightSensorRate, int ambientLightHorizon, Clock clock) {
            if (lightSensorRate <= 0) {
                throw new IllegalArgumentException("lightSensorRate must be above 0");
            }
            int iCeil = (int) Math.ceil((ambientLightHorizon * BUFFER_SLACK) / lightSensorRate);
            this.mCapacity = iCeil;
            this.mRingLux = new float[iCeil];
            this.mRingTime = new long[iCeil];
            this.mClock = clock;
        }

        public float getLux(int index) {
            return this.mRingLux[offsetOf(index)];
        }

        public float[] getAllLuxValues() {
            int i = this.mCount;
            float[] values = new float[i];
            if (i == 0) {
                return values;
            }
            int i2 = this.mStart;
            if (i2 < this.mEnd) {
                System.arraycopy(this.mRingLux, i2, values, 0, i);
            } else {
                System.arraycopy(this.mRingLux, i2, values, 0, this.mCapacity - i2);
                System.arraycopy(this.mRingLux, 0, values, this.mCapacity - this.mStart, this.mEnd);
            }
            return values;
        }

        public long getTime(int index) {
            return this.mRingTime[offsetOf(index)];
        }

        public long[] getAllTimestamps() {
            int i = this.mCount;
            long[] values = new long[i];
            if (i == 0) {
                return values;
            }
            int i2 = this.mStart;
            if (i2 < this.mEnd) {
                System.arraycopy(this.mRingTime, i2, values, 0, i);
            } else {
                System.arraycopy(this.mRingTime, i2, values, 0, this.mCapacity - i2);
                System.arraycopy(this.mRingTime, 0, values, this.mCapacity - this.mStart, this.mEnd);
            }
            return values;
        }

        public void push(long time, float lux) {
            int next = this.mEnd;
            int i = this.mCount;
            int i2 = this.mCapacity;
            if (i == i2) {
                int newSize = i2 * 2;
                float[] newRingLux = new float[newSize];
                long[] newRingTime = new long[newSize];
                int i3 = this.mStart;
                int length = i2 - i3;
                System.arraycopy(this.mRingLux, i3, newRingLux, 0, length);
                System.arraycopy(this.mRingTime, this.mStart, newRingTime, 0, length);
                int i4 = this.mStart;
                if (i4 != 0) {
                    System.arraycopy(this.mRingLux, 0, newRingLux, length, i4);
                    System.arraycopy(this.mRingTime, 0, newRingTime, length, this.mStart);
                }
                this.mRingLux = newRingLux;
                this.mRingTime = newRingTime;
                next = this.mCapacity;
                this.mCapacity = newSize;
                this.mStart = 0;
            }
            this.mRingTime[next] = time;
            this.mRingLux[next] = lux;
            int i5 = next + 1;
            this.mEnd = i5;
            if (i5 == this.mCapacity) {
                this.mEnd = 0;
            }
            this.mCount++;
        }

        public void prune(long horizon) {
            if (this.mCount == 0) {
                return;
            }
            while (true) {
                int i = this.mCount;
                if (i <= 1) {
                    break;
                }
                int next = this.mStart + 1;
                int i2 = this.mCapacity;
                if (next >= i2) {
                    next -= i2;
                }
                if (this.mRingTime[next] > horizon) {
                    break;
                }
                this.mStart = next;
                this.mCount = i - 1;
            }
            long[] jArr = this.mRingTime;
            int i3 = this.mStart;
            if (jArr[i3] < horizon) {
                jArr[i3] = horizon;
            }
        }

        public int size() {
            return this.mCount;
        }

        public void clear() {
            this.mStart = 0;
            this.mEnd = 0;
            this.mCount = 0;
        }

        public String toString() {
            StringBuilder buf = new StringBuilder();
            buf.append('[');
            int i = 0;
            while (true) {
                int i2 = this.mCount;
                if (i < i2) {
                    long next = i + 1 < i2 ? getTime(i + 1) : this.mClock.uptimeMillis();
                    if (i != 0) {
                        buf.append(", ");
                    }
                    buf.append(getLux(i));
                    buf.append(" / ");
                    buf.append(next - getTime(i));
                    buf.append("ms");
                    i++;
                } else {
                    buf.append(']');
                    return buf.toString();
                }
            }
        }

        private int offsetOf(int index) {
            if (index >= this.mCount || index < 0) {
                throw new ArrayIndexOutOfBoundsException(index);
            }
            int index2 = index + this.mStart;
            int i = this.mCapacity;
            if (index2 >= i) {
                return index2 - i;
            }
            return index2;
        }

        public float getMaxLux(long now, long horizon) {
            int N = this.mCount;
            if (N == 0) {
                Slog.e(AutomaticBrightnessController.TAG, "getMax: No ambient light readings available");
                return -1.0f;
            }
            int endIndex = 0;
            long horizonStartTime = now - horizon;
            for (int i = 0; i < N - 1 && getTime(i + 1) <= horizonStartTime; i++) {
                endIndex++;
            }
            int i2 = N - 1;
            float maxLux = getLux(i2);
            for (int i3 = N - 1; i3 >= endIndex; i3--) {
                maxLux = Math.max(getLux(i3), maxLux);
            }
            return maxLux;
        }
    }

    public static class Injector {
        public Handler getBackgroundThreadHandler() {
            return BackgroundThread.getHandler();
        }

        Clock createClock() {
            return new Clock() { // from class: com.android.server.display.AutomaticBrightnessController$Injector$$ExternalSyntheticLambda0
                @Override // com.android.server.display.AutomaticBrightnessController.Clock
                public final long uptimeMillis() {
                    return SystemClock.uptimeMillis();
                }
            };
        }
    }

    protected float getUserDataPoint() {
        return this.mCurrentBrightnessMapper.getUserDataPoint();
    }

    protected Spline getBrightnessSpline() {
        return this.mCurrentBrightnessMapper.getBrightnessSpline();
    }

    public AutomaticBrightnessControllerStub getImpl() {
        return this.mAutomaticBrightnessControllerImpl;
    }

    void setAmbientLux(int event, float lux, boolean needUpdateLux, boolean needUpdateBrightness) {
        if (!this.mSupportDualSensorPolicy) {
            setAmbientLux(lux);
            if (needUpdateBrightness) {
                updateAutoBrightness(true, false);
                return;
            }
            return;
        }
        if (lux < 0.0f) {
            Slog.w(TAG, "Ambient lux was negative, ignoring and setting to 0");
            lux = 0.0f;
        }
        this.mPreThresholdLux = needUpdateLux ? this.mAmbientLux : this.mPreThresholdLux;
        float ambientLux = this.mAutomaticBrightnessControllerImpl.getAmbientLux(event, this.mAmbientLux, lux, needUpdateLux);
        this.mAmbientLux = ambientLux;
        this.mAutomaticBrightnessControllerImpl.updateAmbientLuxDirection(needUpdateBrightness, ambientLux, this.mPreThresholdLux);
        Slog.d(TAG, "setAmbientLux: " + (event == AutomaticBrightnessControllerStub.HANDLE_MAIN_LUX_EVENT ? "handle main light sensor event" : "handle assist light sensor event") + ", needUpdateLux: " + needUpdateLux + ", mAmbientLux: " + this.mAmbientLux + ", mPreThresholdLux: " + this.mPreThresholdLux);
        if (this.mAutomaticBrightnessControllerImpl.updateMainLightSensorAmbientThreshold(event)) {
            float mainAmbientLux = this.mAutomaticBrightnessControllerImpl.getMainAmbientLux();
            this.mAmbientBrighteningThreshold = this.mHysteresisLevelsImpl.getBrighteningThreshold(mainAmbientLux);
            this.mAmbientDarkeningThreshold = this.mHysteresisLevelsImpl.getDarkeningThreshold(mainAmbientLux);
        }
        if (needUpdateLux) {
            this.mHbmController.onAmbientLuxChange(this.mAmbientLux);
            resetShortTermModelIfNeeded();
            if (needUpdateBrightness) {
                updateAutoBrightness(true, false);
            }
        }
    }

    private void setDaemonSensorAmbientLux() {
        SparseArray<Float> daemonSensorArray = this.mAutomaticBrightnessControllerImpl.fillInLuxFromDaemonSensor();
        float mainLux = daemonSensorArray.get(AutomaticBrightnessControllerStub.HANDLE_MAIN_LUX_EVENT).floatValue();
        float assistLux = daemonSensorArray.get(AutomaticBrightnessControllerStub.HANDLE_ASSIST_LUX_EVENT).floatValue();
        if (!this.mSupportDualSensorPolicy) {
            if (!Float.isNaN(mainLux)) {
                this.mUseDaemonSensorInProgress = true;
                setAmbientLux(mainLux);
                return;
            }
            return;
        }
        if (!Float.isNaN(mainLux)) {
            this.mUseDaemonSensorInProgress = true;
            this.mAutomaticBrightnessControllerImpl.setAmbientLuxWhenInvalid(AutomaticBrightnessControllerStub.HANDLE_MAIN_LUX_EVENT, mainLux);
        } else if (!Float.isNaN(assistLux)) {
            this.mUseDaemonSensorInProgress = true;
            this.mAutomaticBrightnessControllerImpl.setAmbientLuxWhenInvalid(AutomaticBrightnessControllerStub.HANDLE_ASSIST_LUX_EVENT, assistLux);
        }
    }

    public void updateForegroundAppWindowChanged() {
        this.mHandler.removeMessages(4);
        this.mHandler.sendEmptyMessage(4);
    }

    private void updateCustomSceneState(String packageName) {
        this.mAutomaticBrightnessControllerImpl.updateCustomSceneState(packageName);
    }
}
