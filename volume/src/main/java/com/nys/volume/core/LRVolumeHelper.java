package com.nys.volume.core;

import android.app.Activity;
import android.app.Service;
import android.media.AudioManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;

public class LRVolumeHelper {

    private AudioManager mAudioManager;

    private LRVolumeToast mLRVolumeToast;

    private Activity mActivity;

    private boolean isOpne = true;

    public static LRVolumeHelper registerLRVolumeAdjust(@NonNull Activity activity) {
        LRVolumeHelper helper = new LRVolumeHelper(activity);
        return helper;
    }

    public static LRVolumeHelper registerLRVolumeAdjust(@NonNull Window window, @NonNull Activity activity) {
        LRVolumeHelper helper = new LRVolumeHelper(window, activity);
        return helper;
    }

    private LRVolumeHelper(@NonNull Activity activity) {
        this.mActivity = activity;

        Window window = activity.getWindow();
        Window.Callback wrapped = window.getCallback();
        window.setCallback(wrapperWindowCallback(wrapped));
    }

    private LRVolumeHelper(@NonNull Window window, @NonNull Activity activity) {
        this.mActivity = activity;

        Window.Callback wrapped = window.getCallback();
        window.setCallback(wrapperWindowCallback(wrapped));
    }

    private WindowCallbackWrapper wrapperWindowCallback(Window.Callback wrapped) {
        WindowCallbackWrapper wrapper = new WindowCallbackWrapper(wrapped) {

            @Override
            public boolean dispatchKeyEvent(KeyEvent event) {
                final int keyCode = event.getKeyCode();
                final boolean isDown = (event.getAction() == KeyEvent.ACTION_DOWN);
                boolean consumed = onKeyDown(isDown, keyCode);
                return consumed || super.dispatchKeyEvent(event);
            }
        };
        return wrapper;
    }

    /**
     * 处理按键事件
     *
     * @param keyCode
     * @param isActionDown
     * @return
     */
    public boolean onKeyDown(boolean isActionDown, int keyCode) {
        boolean consumed = false;
        Log.e("onKeyDown","------------"+keyCode);
        if(isOpne) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_VOLUME_DOWN:
                    consumed = true;
                    if (isActionDown) {
                        subVolume();
                    } else {
                        adjustSame();
                    }
                    break;
                case KeyEvent.KEYCODE_VOLUME_UP:
                    consumed = true;
                    if (isActionDown) {
                        addVolume();
                    } else {
                        adjustSame();
                    }
                    break;
                default:
                    dismissVolumeToast();
                    break;
            }
        }
        return consumed;
    }

    /**
     * 初始化AudioManager
     */
    private void ensureAudioManager() {
        if (mAudioManager == null) {
            if (mActivity != null) {
                mAudioManager = (AudioManager) mActivity.getSystemService(Service.AUDIO_SERVICE);
            }
        }
    }

    /**
     * 调大音量
     */
    private void addVolume() {
        ensureAudioManager();
        if (mAudioManager != null) {
            int expectVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC) + 1;
            if(expectVolume<=mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)){
                mAudioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_RAISE, AudioManager.FLAG_PLAY_SOUND);
                //处理安全音量 https://yrom.net/blog/2016/08/06/one-little-tip-to-handle-safe-media-volume-level-on-android/
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2 && mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC) < expectVolume) {
                    mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, expectVolume, AudioManager.FLAG_SHOW_UI);
                }
            }

            refreshCurrentVolume();
        }
    }

    /**
     * 调小音量
     */
    private void subVolume() {
        ensureAudioManager();
        if (mAudioManager != null) {
            mAudioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_LOWER, AudioManager.FLAG_PLAY_SOUND);
            refreshCurrentVolume();
        }
    }

    /**
     * 调小音量
     */
    private void adjustSame() {
        ensureAudioManager();
        if (mAudioManager != null) {
            mAudioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_SAME, AudioManager.FLAG_PLAY_SOUND);
            refreshCurrentVolume();
        }
    }

    /**
     * 更新音量弹窗
     */
    private void refreshCurrentVolume() {
        ensureAudioManager();
        if (mAudioManager == null) {
            return;
        }

        if (mLRVolumeToast == null) {
            mLRVolumeToast = LRVolumeToast.buildVolumeToasDialog(mActivity, mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC), mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
            mLRVolumeToast.show();
        } else {
            mLRVolumeToast.showCurrentVolumeByKeyDown(mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC));
        }
    }

    public void dismissVolumeToast() {
        if (mLRVolumeToast != null && mLRVolumeToast.isShowing()) {
            mLRVolumeToast.dismissVolumeToastDialog();
        }
    }


    public boolean isOpne() {
        return isOpne;
    }

    public void setOpne(boolean opne) {
        isOpne = opne;
    }
}
