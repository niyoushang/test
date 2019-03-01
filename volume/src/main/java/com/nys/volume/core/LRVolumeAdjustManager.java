package com.nys.volume.core;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Window;

public class LRVolumeAdjustManager {

    /**
     * APP注册自定义音量弹窗
     *
     * @param application
     */
    public static void registerApplication(@NonNull Application application) {
        application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                LRVolumeHelper.registerLRVolumeAdjust(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
    }

    /**
     * 为Activity注册自定义音量弹窗
     */
    public static LRVolumeHelper registerActivity(@NonNull Activity activity) {
        return  LRVolumeHelper.registerLRVolumeAdjust(activity);
    }

    /**
     * 为Window注册自定义音量弹窗,但window的context必须为acitvity
     */
    public static void registerDialog(@NonNull Window window) throws Exception {
        Context context = window.getContext();
        if (!(context instanceof Activity)) {
            throw new Exception("The context of window must be instance of Activity!");
        }
        LRVolumeHelper.registerLRVolumeAdjust(window, (Activity) context);
    }
}
