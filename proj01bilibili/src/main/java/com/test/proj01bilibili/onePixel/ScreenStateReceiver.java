package com.test.proj01bilibili.onePixel;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

public class ScreenStateReceiver extends BroadcastReceiver {
    private static final String TAG = "ScreenStateReceiver";

    /**
     * 1、采用动态注册广播
     *
     * @param context
     * @param intent
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        String action = intent.getAction();
        if (TextUtils.equals(action, Intent.ACTION_SCREEN_OFF)) {
            //启动 1 像素Activity
            ScreenStateManager.getInstance().startOnePixActivity(context);
        } else if (TextUtils.equals(action, Intent.ACTION_SCREEN_ON)) {
            //关闭 1 像素Activity
            ScreenStateManager.getInstance().finishOnePixelActivity(context);
        }
    }
}
