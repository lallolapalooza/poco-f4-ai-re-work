package com.miui.extraphoto.refocus.core.relighting;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.miui.extraphoto.common.BuildUtil;
import com.miui.extraphoto.common.storage.Storage;
import com.miui.extraphoto.common.utils.ImageUtils;
import com.miui.extraphoto.common.utils.MathUtils;
import com.miui.extraphoto.common.utils.MediaStoreUtils;
import com.miui.extraphoto.common.utils.ToastUtils;
import com.miui.extraphoto.common.widget.recyclerview.BlankDivider;
import com.miui.extraphoto.common.widget.recyclerview.OnItemClickListener;
import com.miui.extraphoto.common.widget.recyclerview.SimpleRecyclerViewMiuix;
import com.miui.extraphoto.refocus.DualPhotoOperatingFragment;
import com.miui.extraphoto.refocus.R$dimen;
import com.miui.extraphoto.refocus.R$id;
import com.miui.extraphoto.refocus.R$layout;
import com.miui.extraphoto.refocus.R$string;
import com.miui.extraphoto.refocus.RefocusActivity;
import com.miui.extraphoto.refocus.ViewInterface;
import com.miui.extraphoto.refocus.manager.DualPhotoFileUtils;
import com.miui.extraphoto.refocus.manager.DualPhotoJni;
import com.miui.extraphoto.refocus.manager.DualPhotoMiMovieUtils;
import com.miui.mediaeditor.bizlibs.storage.utils.EditorFileUtils;
import com.miui.mediaeditor.storage.entrance.XResult;
import com.miui.mediaeditor.storage.entrance.XStorage;
import com.miui.mediaeditor.storage.utils.BaseMediaUtils;
import com.miui.mediaeditor.storage.utils.BaseStorageUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public class RelightingOperatingFragment extends DualPhotoOperatingFragment<RelightingGestureFragment> {
    private boolean mIsMovieRelighting;
    private SimpleRecyclerViewMiuix mRecyclerView;
    private RelightingEffectAdapter mRelightingEffectAdapter;
    private RelightingProcessor mRelightingProcessor;
    private RelightingResourceLoader mResourceLoader;
    private SaveRunnable mSaveRunnable;
    private List<RelightingConfig> mRelightingConfigs = new ArrayList();
    private boolean mViewReady = false;
    private boolean mDataReady = false;
    private AtomicBoolean mIsInit = new AtomicBoolean(false);
    private AtomicBoolean mInitSuccess = new AtomicBoolean(false);
    private boolean mNativeReady = false;
    private int mCameraDefendId = -1;
    private boolean mIsSoftImage = false;
    private boolean mInTouchMode = false;
    private Runnable mChangeToDefaultRunnable = new Runnable() { // from class: com.miui.extraphoto.refocus.core.relighting.RelightingOperatingFragment.1
        @Override // java.lang.Runnable
        public void run() {
            RelightingOperatingFragment.this.mRelightingProcessor.changeToDefault(((DualPhotoOperatingFragment) RelightingOperatingFragment.this).mPhotoInfoProvider, ((DualPhotoOperatingFragment) RelightingOperatingFragment.this).mDualPhotoNativeContext);
            RelightingOperatingFragment.this.refreshDisplay();
        }
    };
    private View.OnClickListener mExitListener = new View.OnClickListener() { // from class: com.miui.extraphoto.refocus.core.relighting.RelightingOperatingFragment.2
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ((DualPhotoOperatingFragment) RelightingOperatingFragment.this).mViewInterface.onExit();
        }
    };
    private View.OnClickListener mSaveListener = new View.OnClickListener() { // from class: com.miui.extraphoto.refocus.core.relighting.RelightingOperatingFragment.3
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            RelightingOperatingFragment.this.onSave();
        }
    };
    private OnItemClickListener mOnItemClickListener = new OnItemClickListener() { // from class: com.miui.extraphoto.refocus.core.relighting.RelightingOperatingFragment.4
        @Override // com.miui.extraphoto.common.widget.recyclerview.OnItemClickListener
        public boolean OnItemClick(RecyclerView recyclerView, View view, int i) {
            if (RelightingOperatingFragment.this.mInTouchMode) {
                return false;
            }
            RelightingOperatingFragment.this.smoothScrollToCenter((SimpleRecyclerViewMiuix) recyclerView, i);
            RelightingOperatingFragment.this.mRelightingEffectAdapter.setSelection(i);
            RelightingOperatingFragment.this.onSelectPosition(i);
            return true;
        }
    };
    private final Runnable mShowLoadingRunnable = new Runnable() { // from class: com.miui.extraphoto.refocus.core.relighting.RelightingOperatingFragment$$ExternalSyntheticLambda0
        @Override // java.lang.Runnable
        public final void run() {
            this.f$0.safeShowProcessLoading();
        }
    };
    private Runnable notifyProcessDoneRunnable = new Runnable() { // from class: com.miui.extraphoto.refocus.core.relighting.RelightingOperatingFragment.5
        @Override // java.lang.Runnable
        public void run() {
            RelightingOperatingFragment.this.safeDismissProcessLoading();
        }
    };
    private Runnable notifyInitFailedRunnable = new Runnable() { // from class: com.miui.extraphoto.refocus.core.relighting.RelightingOperatingFragment.6
        @Override // java.lang.Runnable
        public void run() {
            FragmentActivity activity = RelightingOperatingFragment.this.getActivity();
            if (activity instanceof RefocusActivity) {
                ((RefocusActivity) activity).setSaveButtonEnable(false);
            }
            RelightingOperatingFragment.this.safeDismissProcessLoading();
            if (activity == null) {
                return;
            }
            int initResult = RelightingOperatingFragment.this.mRelightingProcessor.getInitResult();
            if (initResult == 262145) {
                ToastUtils.makeText(RelightingOperatingFragment.this.getActivity(), R$string.refocus_type_relighting_no_face);
            } else if (initResult == 262146) {
                ToastUtils.makeText(RelightingOperatingFragment.this.getActivity(), R$string.refocus_type_relighting_small_face);
            }
        }
    };
    private RelightingResourceLoader.OnResourceLoadListener mResourceLoadListener = new RelightingResourceLoader.OnResourceLoadListener() { // from class: com.miui.extraphoto.refocus.core.relighting.RelightingOperatingFragment.7
        @Override // com.miui.extraphoto.refocus.core.relighting.RelightingResourceLoader.OnResourceLoadListener
        public void onResourceLoad(List<RelightingConfig> list) {
            RelightingOperatingFragment.this.mRelightingConfigs.clear();
            RelightingOperatingFragment.this.mRelightingConfigs.addAll(list);
            RelightingOperatingFragment.this.mDataReady = true;
            RelightingOperatingFragment.this.notifyInitView();
        }
    };

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mIsMovieRelighting = false;
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mIsMovieRelighting = arguments.getBoolean("isMovieRelighting");
        }
        loadResource(this.mIsMovieRelighting);
    }

    @Override // com.miui.extraphoto.refocus.DualPhotoOperatingFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override // com.miui.extraphoto.refocus.DualPhotoOperatingFragment
    protected boolean isEmpty() {
        if (getActivity() instanceof RefocusActivity) {
            return !((RefocusActivity) getActivity()).getSaveButtonEnable();
        }
        return true;
    }

    @Override // com.miui.extraphoto.refocus.DualPhotoOperatingFragment
    protected void onSaveFromConfirmDialog() {
        onSave();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R$layout.refocus_fragment_dynamic, viewGroup, false);
    }

    /* JADX WARN: Code duplicated, block: B:51:0x010b  */
    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mRecyclerView = (SimpleRecyclerViewMiuix) view.findViewById(R$id.list);
        this.mRelightingEffectAdapter = new RelightingEffectAdapter(getActivity(), this.mRelightingConfigs, this.mIsMovieRelighting);
        setSmothScrollToCenterLinearLayoutManager(this.mRecyclerView);
        this.mRecyclerView.setAdapter(this.mRelightingEffectAdapter);
        int dimensionPixelSize = getResources().getDimensionPixelSize(R$dimen.refocus_menu_item_margin);
        this.mRecyclerView.addItemDecoration(new BlankDivider(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, 0, 0, 0));
        byte b = 1;
        this.mViewReady = true;
        notifyInitView();
        byte b2 = 0;
        if (getActivity() instanceof RefocusActivity) {
            ((RefocusActivity) getActivity()).setSaveButtonEnable(false);
        }
        String portraitType = this.mPhotoInfoProvider.getPortraitType();
        this.mIsSoftImage = !"portrait".equals(portraitType);
        if (this.mPhotoInfoProvider.getRelightType() == 2) {
            portraitType.hashCode();
            switch (portraitType.hashCode()) {
                case -1310970107:
                    b = !portraitType.equals("megvii_portrait_google") ? (byte) -1 : (byte) 0;
                    break;
                case -933466402:
                    if (!portraitType.equals("mi_portrait")) {
                        b = -1;
                    }
                    break;
                case 155334763:
                    b = !portraitType.equals("soft-portrait-enc") ? (byte) -1 : (byte) 2;
                    break;
                case 729267099:
                    b = !portraitType.equals("portrait") ? (byte) -1 : (byte) 3;
                    break;
                default:
                    b = -1;
                    break;
            }
            switch (b) {
                case 0:
                    this.mRelightingProcessor = new MegviiRelightingProcessor();
                    break;
                case 1:
                    this.mRelightingProcessor = new MiBrainRelightingProcessor();
                    break;
                case 2:
                    this.mRelightingProcessor = new MiBrainRelightingProcessorSoftEncrypted();
                    break;
                case 3:
                    this.mRelightingProcessor = new MiBrainRelightingProcessorPortrait();
                    break;
            }
        }
        if (this.mPhotoInfoProvider.getRelightType() == 0 || this.mPhotoInfoProvider.getRelightType() == 1) {
            int iHashCode = portraitType.hashCode();
            if (iHashCode != -1161274914) {
                if (iHashCode != 155334763) {
                    if (iHashCode == 729267099 && portraitType.equals("portrait")) {
                        b2 = 2;
                    } else {
                        b2 = -1;
                    }
                } else if (!portraitType.equals("soft-portrait-enc")) {
                    b2 = -1;
                }
            } else if (portraitType.equals("soft-portrait")) {
                b2 = 1;
            } else {
                b2 = -1;
            }
            if (b2 == 0) {
                this.mRelightingProcessor = new RelightingProcessorSoftEncrypted();
            } else if (b2 == 1) {
                this.mRelightingProcessor = new RelightingProcessorSoftPortrait();
            } else {
                this.mRelightingProcessor = new RelightingProcessorPortrait();
            }
        }
    }

    @Override // com.miui.extraphoto.refocus.DualPhotoOperatingFragment
    public void notifyInitLoadProcess() {
        this.mNativeReady = true;
        notifyFirstProcess();
    }

    @Override // com.miui.extraphoto.refocus.DualPhotoOperatingFragment
    public void notifyInitLoadUi() {
        this.mCameraDefendId = this.mPhotoInfoProvider.getRelightingIndex();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initRelightingEffect() {
        if (this.mIsInit.get()) {
            return;
        }
        this.mInitSuccess.set(this.mRelightingProcessor.initProcessor(this.mPhotoInfoProvider, this.mDualPhotoNativeContext));
        this.mIsInit.set(true);
    }

    private void loadResource(boolean z) {
        if (this.mResourceLoader == null) {
            RelightingResourceLoader relightingResourceLoader = new RelightingResourceLoader(z);
            this.mResourceLoader = relightingResourceLoader;
            relightingResourceLoader.setResourceLoadListener(this.mResourceLoadListener);
            this.mResourceLoader.loadDynamicResource(getActivity().getAssets());
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        RelightingEffectAdapter relightingEffectAdapter = this.mRelightingEffectAdapter;
        if (relightingEffectAdapter != null) {
            relightingEffectAdapter.setOnItemClickListener(null);
        }
        RelightingResourceLoader relightingResourceLoader = this.mResourceLoader;
        if (relightingResourceLoader != null) {
            relightingResourceLoader.setResourceLoadListener(null);
        }
        runOnRenderThread(new DestroyRunnable(this.mRelightingProcessor));
        removeRunnable(this.notifyProcessDoneRunnable);
        removeRunnable(this.notifyInitFailedRunnable);
        removeRunnable(this.mShowLoadingRunnable);
        SaveRunnable saveRunnable = this.mSaveRunnable;
        if (saveRunnable != null) {
            removeOnRenderThreadRunnable(saveRunnable);
        }
    }

    private static class DestroyRunnable implements Runnable {
        private final RelightingProcessor mRelightingProcessor;

        private DestroyRunnable(RelightingProcessor relightingProcessor) {
            this.mRelightingProcessor = relightingProcessor;
        }

        @Override // java.lang.Runnable
        public void run() {
            RelightingProcessor relightingProcessor = this.mRelightingProcessor;
            if (relightingProcessor != null) {
                relightingProcessor.release();
            }
        }
    }

    private class GesturePositionRunnable implements Runnable {
        private final float mCenterX;
        private final float mCenterY;
        private final float mCurrentX;
        private final float mCurrentY;

        private GesturePositionRunnable(float f, float f2, float f3, float f4) {
            this.mCenterX = f;
            this.mCenterY = f2;
            this.mCurrentX = f3;
            this.mCurrentY = f4;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (RelightingOperatingFragment.this.getActivity() instanceof RefocusActivity) {
                ((RefocusActivity) RelightingOperatingFragment.this.getActivity()).setSaveButtonEnable(RelightingOperatingFragment.this.mInitSuccess.get());
            }
            ((RelightingGestureFragment) ((DualPhotoOperatingFragment) RelightingOperatingFragment.this).mGestureFragment).notifyGestureVisibility(RelightingOperatingFragment.this.mRelightingProcessor.supportAdjustLightSource());
            ((RelightingGestureFragment) ((DualPhotoOperatingFragment) RelightingOperatingFragment.this).mGestureFragment).setPosition(this.mCenterX, this.mCenterY, this.mCurrentX, this.mCurrentY);
        }
    }

    @Override // com.miui.extraphoto.refocus.DualPhotoOperatingFragment
    public void onSave() {
        this.mViewInterface.onShowSaveLoading();
        RelightingConfig relightingConfig = this.mRelightingConfigs.get(this.mRelightingEffectAdapter.getSelection());
        if (relightingConfig != null) {
            SaveRunnable saveRunnable = new SaveRunnable(relightingConfig);
            this.mSaveRunnable = saveRunnable;
            runOnRenderThread(saveRunnable);
        }
    }

    @Override // com.miui.extraphoto.refocus.DualPhotoOperatingFragment
    public void onExit() {
        this.mViewInterface.onExit();
    }

    private class SaveRunnable implements Runnable {
        private final RelightingConfig mRelightingConfig;

        private SaveRunnable(RelightingConfig relightingConfig) {
            this.mRelightingConfig = relightingConfig;
        }

        @Override // java.lang.Runnable
        public void run() throws Throwable {
            String strSafeGenerateImagePath = EditorFileUtils.safeGenerateImagePath(BaseStorageUtils.getRelativeFolder(((DualPhotoOperatingFragment) RelightingOperatingFragment.this).mPhotoInfoProvider.getSourcePath()), "jpg", false);
            XResult xResultInsert = XStorage.insert(strSafeGenerateImagePath, "RelightingOperatingFragment");
            if (xResultInsert.isSuccess()) {
                Storage storage = new Storage(strSafeGenerateImagePath, xResultInsert.getUri());
                Bitmap bitmapCreateBitmap = Bitmap.createBitmap(((DualPhotoOperatingFragment) RelightingOperatingFragment.this).mPhotoInfoProvider.getProcessWidth(), ((DualPhotoOperatingFragment) RelightingOperatingFragment.this).mPhotoInfoProvider.getProcessHeight(), Bitmap.Config.ARGB_8888);
                DualPhotoJni.configBitmapByNativeImage(bitmapCreateBitmap, ((DualPhotoOperatingFragment) RelightingOperatingFragment.this).mDualPhotoNativeContext.getEffectImage().pointer);
                Bitmap bitmapGenerateBitmapWithOrientation = ImageUtils.generateBitmapWithOrientation(bitmapCreateBitmap, ((DualPhotoOperatingFragment) RelightingOperatingFragment.this).mPhotoInfoProvider.getDepthDataOrientation());
                if (((DualPhotoOperatingFragment) RelightingOperatingFragment.this).mPhotoInfoProvider.isMirror()) {
                    bitmapGenerateBitmapWithOrientation = ImageUtils.generateMirrorBitmap(bitmapGenerateBitmapWithOrientation);
                }
                Bitmap bitmap = bitmapGenerateBitmapWithOrientation;
                if (((DualPhotoOperatingFragment) RelightingOperatingFragment.this).mPhotoInfoProvider.isMiMovie()) {
                    DualPhotoMiMovieUtils.fillBlackBorderForBitmap(bitmap);
                }
                ((DualPhotoOperatingFragment) RelightingOperatingFragment.this).mDualPhotoNativeContext.setCanRefreshDisplay(false);
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inMutable = true;
                Bitmap bitmapDecodeOriginBitmap = ((DualPhotoOperatingFragment) RelightingOperatingFragment.this).mPhotoInfoProvider.decodeOriginBitmap(options);
                ((DualPhotoOperatingFragment) RelightingOperatingFragment.this).mDualPhotoNativeContext.generateNativeImage(bitmapDecodeOriginBitmap, ((DualPhotoOperatingFragment) RelightingOperatingFragment.this).mPhotoInfoProvider);
                RelightingOperatingFragment.this.mRelightingProcessor.doSave(((DualPhotoOperatingFragment) RelightingOperatingFragment.this).mPhotoInfoProvider, ((DualPhotoOperatingFragment) RelightingOperatingFragment.this).mDualPhotoNativeContext, this.mRelightingConfig.getEffectType());
                DualPhotoJni.configBitmapByNativeImage(bitmapDecodeOriginBitmap, ((DualPhotoOperatingFragment) RelightingOperatingFragment.this).mDualPhotoNativeContext.getEffectImage().pointer);
                if (((DualPhotoOperatingFragment) RelightingOperatingFragment.this).mPhotoInfoProvider.isMiMovie()) {
                    DualPhotoMiMovieUtils.fillBlackBorderForBitmap(bitmapDecodeOriginBitmap);
                }
                Bitmap bitmapGenerateBitmapWithDegree = ImageUtils.generateBitmapWithDegree(bitmapDecodeOriginBitmap, MathUtils.positiveModule(((DualPhotoOperatingFragment) RelightingOperatingFragment.this).mPhotoInfoProvider.getDepthDataDegree() - ((DualPhotoOperatingFragment) RelightingOperatingFragment.this).mPhotoInfoProvider.getDegree(), 360));
                if (RelightingOperatingFragment.this.isDetached() || RelightingOperatingFragment.this.getContext() == null) {
                    return;
                }
                DualPhotoFileUtils.writeBitmap(storage, bitmapGenerateBitmapWithDegree, ((DualPhotoOperatingFragment) RelightingOperatingFragment.this).mPhotoInfoProvider, RelightingOperatingFragment.this.getResources());
                BaseMediaUtils.triggerScanFile(strSafeGenerateImagePath);
                MediaStoreUtils.upsert(RelightingOperatingFragment.this.getActivity().getApplicationContext(), strSafeGenerateImagePath);
                RelightingOperatingFragment relightingOperatingFragment = RelightingOperatingFragment.this;
                relightingOperatingFragment.runOnMainThread(new DualPhotoOperatingFragment.SaveFinishRunnable(((DualPhotoOperatingFragment) relightingOperatingFragment).mViewInterface, bitmap, strSafeGenerateImagePath, false, "relighting", this.mRelightingConfig.name));
            }
        }
    }

    public void setLightSource(float f, float f2) {
        float scale = this.mDualPhotoNativeContext.getScale();
        float f3 = f * scale;
        float f4 = f2 * scale;
        RelightingConfig relightingConfig = this.mRelightingConfigs.get(this.mRelightingEffectAdapter.getSelection());
        if (relightingConfig != null) {
            this.mDisplay.getRenderHandler().removeCallbacksAndMessages("token_set_light_source");
            this.mDisplay.getRenderHandler().postAtTime(new SetLightSourceRunnable(Math.round(f3), Math.round(f4), relightingConfig), "token_set_light_source", 0L);
        }
    }

    public void onGestureDown() {
        this.mInTouchMode = true;
    }

    public void onGestureUp() {
        this.mInTouchMode = false;
    }

    private class SetLightSourceRunnable implements Runnable {
        private final RelightingConfig mRelightingConfig;
        private final int x;
        private final int y;

        private SetLightSourceRunnable(int i, int i2, RelightingConfig relightingConfig) {
            this.x = i;
            this.y = i2;
            this.mRelightingConfig = relightingConfig;
        }

        @Override // java.lang.Runnable
        public void run() {
            RelightingOperatingFragment.this.mRelightingProcessor.setLightSource(((DualPhotoOperatingFragment) RelightingOperatingFragment.this).mPhotoInfoProvider, this.x, this.y);
            RelightingOperatingFragment.this.mRelightingProcessor.enableRelightingEffect(this.mRelightingConfig.getEffectType(), ((DualPhotoOperatingFragment) RelightingOperatingFragment.this).mDualPhotoNativeContext);
            RelightingOperatingFragment.this.refreshDisplay();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onSelectPosition(int i) {
        boolean z = true;
        boolean z2 = i != 0;
        if (this.mIsInit.get() && !this.mInitSuccess.get()) {
            z = false;
        }
        boolean z3 = z2 & this.mInitSuccess.get();
        if (getActivity() instanceof RefocusActivity) {
            ((RefocusActivity) getActivity()).setSaveButtonEnable(z3);
        }
        if (i == 0) {
            runOnRenderThread(this.mChangeToDefaultRunnable);
            ((RelightingGestureFragment) this.mGestureFragment).notifyGestureVisibility(false);
        } else {
            if (z) {
                RelightingConfig relightingConfig = this.mRelightingConfigs.get(i);
                if (relightingConfig != null) {
                    removeRunnable(this.mShowLoadingRunnable);
                    runOnMainThreadDelayed(this.mShowLoadingRunnable, 1000L);
                    runOnRenderThread(new ChangeEffectRunnable(relightingConfig));
                    return;
                }
                return;
            }
            ((RelightingGestureFragment) this.mGestureFragment).notifyGestureVisibility(false);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean z) {
        if (z) {
            if (getActivity() instanceof RefocusActivity) {
                ((RefocusActivity) getActivity()).setSaveButtonEnable(false);
            }
            this.mRecyclerView.scrollToPosition(0);
            this.mRelightingEffectAdapter.setSelection(0);
            return;
        }
        if (getActivity() instanceof RefocusActivity) {
            ((RefocusActivity) getActivity()).setSaveButtonEnable(false);
        }
        this.mRecyclerView.scrollToPosition(0);
        this.mRelightingEffectAdapter.setSelection(0);
        runOnRenderThread(this.mChangeToDefaultRunnable);
    }

    private class ChangeEffectRunnable implements Runnable {
        private final RelightingConfig mConfig;

        ChangeEffectRunnable(RelightingConfig relightingConfig) {
            this.mConfig = relightingConfig;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (!RelightingOperatingFragment.this.mIsInit.get()) {
                RelightingOperatingFragment.this.initRelightingEffect();
                if (!RelightingOperatingFragment.this.mInitSuccess.get()) {
                    RelightingOperatingFragment relightingOperatingFragment = RelightingOperatingFragment.this;
                    relightingOperatingFragment.runOnMainThread(relightingOperatingFragment.notifyInitFailedRunnable);
                    RelightingOperatingFragment relightingOperatingFragment2 = RelightingOperatingFragment.this;
                    relightingOperatingFragment2.removeRunnable(relightingOperatingFragment2.mShowLoadingRunnable);
                    return;
                }
            }
            RelightingOperatingFragment.this.mRelightingProcessor.enableRelightingEffect(this.mConfig.getEffectType(), ((DualPhotoOperatingFragment) RelightingOperatingFragment.this).mDualPhotoNativeContext);
            RelightingOperatingFragment.this.refreshDisplay();
            RelightingOperatingFragment relightingOperatingFragment3 = RelightingOperatingFragment.this;
            relightingOperatingFragment3.removeRunnable(relightingOperatingFragment3.mShowLoadingRunnable);
            RelightingOperatingFragment relightingOperatingFragment4 = RelightingOperatingFragment.this;
            relightingOperatingFragment4.runOnMainThread(relightingOperatingFragment4.notifyProcessDoneRunnable);
            float scale = ((DualPhotoOperatingFragment) RelightingOperatingFragment.this).mDualPhotoNativeContext.getScale();
            RelightingOperatingFragment relightingOperatingFragment5 = RelightingOperatingFragment.this;
            relightingOperatingFragment5.runOnMainThread(new GesturePositionRunnable(relightingOperatingFragment5.mRelightingProcessor.getFaceX() / scale, RelightingOperatingFragment.this.mRelightingProcessor.getFaceY() / scale, RelightingOperatingFragment.this.mRelightingProcessor.getLightingSourceX() / scale, RelightingOperatingFragment.this.mRelightingProcessor.getLightingSourceY() / scale));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void safeShowProcessLoading() {
        ViewInterface viewInterface = this.mViewInterface;
        if (viewInterface != null) {
            viewInterface.onShowProcessLoading();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void safeDismissProcessLoading() {
        ViewInterface viewInterface = this.mViewInterface;
        if (viewInterface != null) {
            viewInterface.dismissProcessLoading();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyInitView() {
        if (this.mDataReady && this.mViewReady) {
            int i = 0;
            while (i < this.mRelightingConfigs.size()) {
                RelightingConfig relightingConfig = this.mRelightingConfigs.get(i);
                if (this.mIsSoftImage && "0x3".equals(relightingConfig.relightingType)) {
                    this.mRelightingConfigs.remove(relightingConfig);
                } else {
                    if ("0x18".equals(relightingConfig.relightingType) && !BuildUtil.isIndiaRegionOrDevice()) {
                        this.mRelightingConfigs.remove(relightingConfig);
                    }
                    i++;
                }
                i--;
                i++;
            }
            this.mRelightingEffectAdapter.notifyDataSetChanged();
            this.mRelightingEffectAdapter.setOnItemClickListener(this.mOnItemClickListener);
            if (this.mViewInterface.isInitFinish()) {
                onSelectPosition(0);
            } else {
                notifyRefreshFirstUi();
                notifyFirstProcess();
            }
        }
    }

    private void notifyRefreshFirstUi() {
        int iFindInitEffectPosition = findInitEffectPosition(this.mCameraDefendId);
        this.mRelightingEffectAdapter.setSelection(iFindInitEffectPosition);
        this.mRecyclerView.scrollToPosition(iFindInitEffectPosition);
    }

    private void notifyFirstProcess() {
        if (this.mViewReady && this.mDataReady && this.mNativeReady) {
            this.mOnItemClickListener.OnItemClick(this.mRecyclerView, null, findInitEffectPosition(this.mCameraDefendId));
        }
    }

    private int findInitEffectPosition(int i) {
        for (int i2 = 0; i2 < this.mRelightingConfigs.size(); i2++) {
            if (this.mRelightingConfigs.get(i2).cameraDefinedType == i) {
                return i2;
            }
        }
        return 0;
    }
}
