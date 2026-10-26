package com.xiaomi.aiautobrt;

/* JADX INFO: loaded from: /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/miui_securitycoreadd/sources/framework/miui-framework.dex */
public final class InterfaceVersionHelper {
    public static final String INTERFACE_NAME_ON_AB_TEST_EXPERIMENT_UPDATED = "onAbTestExperimentUpdated";
    public static final String INTERFACE_NAME_ON_TRAIN_INDICATORS_FINISHED = "onTrainIndicatorsFinished";
    public static final int INVALID_INTERFACE_VERSION = 0;
    public static final int RELEASED_INTERFACE_VERSION = 2;

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:11:0x001e  */
    public static int getInterfaceVersion(String methodName) {
        switch (methodName) {
            case "onTrainIndicatorsFinished":
                return 1;
            case "onAbTestExperimentUpdated":
                return 2;
            default:
                return 0;
        }
    }
}
