package com.nys.volume.core;

import android.app.Activity;
import android.app.Dialog;
import android.app.Service;
import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;


/**
 * 音量调节弹窗
 */
public class LRVolumeToast extends Dialog implements WeakHandler.IHandler {
    /**
     * 音量值的4个level
     */
    private static final int VOLUME_LEVEL_0 = 0;
    private static final int VOLUME_LEVEL_1 = 1;
    private static final int VOLUME_LEVEL_2 = 2;
    private static final int VOLUME_LEVEL_3 = 3;
    /**
     * 音量延时消失时间
     */
    private static final int DELAY_HIDE_PERIOD = 2000;
    private static final int MSG_HIDE_TOAST = 1000;

    private Activity mHostActivity;

    /**
     * 当前音量值
     */
    private int mCurrentVolume;
    /**
     * 音量最大值
     */
    private int mMaxVolume;
    /**
     * 音量值Level
     */
    private int mVolumeLevel;

    private ImageView mDialogIcon;

    private CustomSeekBar mProgressBar;

    private WeakHandler mHandler;

    public LRVolumeToast(Activity context, int theme) {
        super(context, theme);
        mHostActivity = context;
        mHandler = new WeakHandler(this);
    }

    /**
     * 构建亮度Toast弹窗
     *
     * @param activity
     * @param currentVolume 当前的音量值
     * @param maxVolume     最大音量值
     * @return
     */
    public static LRVolumeToast buildVolumeToasDialog(Activity activity, int currentVolume, int maxVolume) {
        LRVolumeToast dialog = new LRVolumeToast(activity, R.style.volume_adjust_dialog);
        dialog.mCurrentVolume = currentVolume;
        dialog.mMaxVolume = maxVolume;
        return dialog;
    }

    private void initWindowParams() {
        Window window = getWindow();
        if (window != null) {
            window.addFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
            window.addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            WindowManager.LayoutParams param = window.getAttributes();
            param.gravity = Gravity.TOP;
            param.y = (int) dip2Px(mHostActivity, 84);
            window.setAttributes(param);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.volume_adjust_layout);

        initWindowParams();

        mDialogIcon = findViewById(R.id.commonui_audio_progressbar_icon);
        mProgressBar = findViewById(R.id.commonui_audio_progressbar);

        AudioManager audioManager = (AudioManager) mHostActivity.getSystemService(Service.AUDIO_SERVICE);
        if (mMaxVolume == 0) {
            mMaxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        }
        if (mCurrentVolume == 0) {
            mCurrentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        }
        mProgressBar.setMaxProgress(mMaxVolume);
        mProgressBar.setProgress(mCurrentVolume);

        checkVolumeLevel();
        setVolumeDialogIcon();
    }

    /**
     * 通过触摸事件设置当前亮度值
     *
     * @param volume
     */
    public void showCurrentVolumeByTouchEvent(int volume) {
        mCurrentVolume = volume;
        if (mProgressBar != null) {
            mProgressBar.setProgress(volume);
        }
        checkScreenOritention();
        if (checkVolumeLevel()) {
            setVolumeDialogIcon();
        }
        if (!isShowing()) {
            show();
        }
    }

    /**
     * 通过按键设置当前亮度值
     *
     * @param volume
     */
    public void showCurrentVolumeByKeyDown(int volume) {
        showCurrentVolumeByTouchEvent(volume);
        mHandler.removeMessages(MSG_HIDE_TOAST);
        mHandler.sendEmptyMessageDelayed(MSG_HIDE_TOAST, DELAY_HIDE_PERIOD);
    }

    private void checkScreenOritention() {
        initWindowParams();
        mDialogIcon = findViewById(R.id.commonui_audio_progressbar_icon);
        mProgressBar = findViewById(R.id.commonui_audio_progressbar);
        mProgressBar.setMaxProgress(mMaxVolume);
        setVolumeDialogIcon();
    }

    /**
     * 隐藏亮度弹窗
     */
    public void dismissVolumeToastDialog() {
        mHandler.removeMessages(MSG_HIDE_TOAST);
        if (isShowing()) {
            dismiss();
        }
    }

    /**
     * check音量值Level
     *
     * @return level发生变化
     */
    private boolean checkVolumeLevel() {
        int value = mCurrentVolume * 100 / mMaxVolume;
        boolean levelChange = false;
        if (value >= 66) {
            if (mVolumeLevel != VOLUME_LEVEL_3) {
                levelChange = true;
                mVolumeLevel = VOLUME_LEVEL_3;
            }
        } else if (value >= 33) {
            if (mVolumeLevel != VOLUME_LEVEL_2) {
                levelChange = true;
                mVolumeLevel = VOLUME_LEVEL_2;
            }
        } else if (value > 0) {
            if (mVolumeLevel != VOLUME_LEVEL_1) {
                levelChange = true;
                mVolumeLevel = VOLUME_LEVEL_1;
            }
        } else {
            if (mVolumeLevel != VOLUME_LEVEL_0) {
                levelChange = true;
                mVolumeLevel = VOLUME_LEVEL_0;
            }
        }

        return levelChange;
    }

    private void setVolumeDialogIcon() {
        int resoureId = 0;
        switch (mVolumeLevel) {
            case VOLUME_LEVEL_1:
                resoureId = R.drawable.icon_volume_open;
                break;
            case VOLUME_LEVEL_2:
                resoureId = R.drawable.icon_volume_open;
                break;
            case VOLUME_LEVEL_3:
                resoureId = R.drawable.icon_volume_open;
                break;
            default:
                resoureId = R.drawable.icon_volume_close;
                break;
        }
        mDialogIcon.setImageDrawable(ContextCompat.getDrawable(mHostActivity, resoureId));
    }

    @Override
    public void handleMsg(Message msg) {
        if (msg == null) {
            return;
        }
        switch (msg.what) {
            case MSG_HIDE_TOAST:
                dismissVolumeToastDialog();
                break;
            default:
                break;
        }
    }

    /**
     * 返回当前View展示的音量值
     *
     * @return
     */
    public int getCurrentVolumeValue() {
        return mCurrentVolume;
    }


    public boolean isViewValid() {
        return mHostActivity == null || !mHostActivity.isFinishing();
    }

    @Override
    public void show() {
        if (!isViewValid()) {
            return;
        }
        try {
            super.show();
        } catch (Throwable e) {

        }
    }

    @Override
    public void dismiss() {
        try {
            super.dismiss();
        } catch (Throwable e) {

        }
    }

    /**
     * 屏幕宽度
     *
     * @param context
     * @return
     */
    public int getScreenWidth(Context context) {
        if (context == null) {
            return 0;
        } else {
            DisplayMetrics dm = context.getResources().getDisplayMetrics();
            return dm == null ? 0 : dm.widthPixels;
        }
    }

    /**
     * 屏幕高度
     *
     * @param context
     * @return
     */
    public int getScreenHeight(Context context) {
        if (context == null) {
            return 0;
        } else {
            DisplayMetrics dm = context.getResources().getDisplayMetrics();
            return dm == null ? 0 : dm.heightPixels;
        }
    }

    /**
     * dp 转 px
     *
     * @param context
     * @param dipValue
     * @return
     */
    public float dip2Px(Context context, float dipValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return dipValue * scale + 0.5F;
    }
}
