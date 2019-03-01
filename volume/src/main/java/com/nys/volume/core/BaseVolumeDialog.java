package com.nys.volume.core;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class BaseVolumeDialog extends Dialog {

    private Activity mHostActivity;

    public BaseVolumeDialog(@NonNull Activity context) {
        super(context);
        this.mHostActivity = context;
    }

    public BaseVolumeDialog(@NonNull Activity context, int themeResId) {
        super(context, themeResId);
        this.mHostActivity = context;
    }

    protected BaseVolumeDialog(@NonNull Activity context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.mHostActivity = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LRVolumeHelper.registerLRVolumeAdjust(getWindow(), mHostActivity);
    }
}
