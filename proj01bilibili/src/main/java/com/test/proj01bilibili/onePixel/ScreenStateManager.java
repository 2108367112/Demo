package com.test.proj01bilibili.onePixel;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

public class ScreenStateManager {
    private static final String TAG = "ScreenStateManager";

    private static ScreenStateManager manager;
    private ScreenStateReceiver screenStateReceiver;


    private ScreenStateManager() {
    }

    public static ScreenStateManager getInstance() {
        if (null == manager) {
            synchronized (ScreenStateManager.class) {
                if (null == manager) {
                    manager = new ScreenStateManager();
                }
            }
        }
        return manager;
    }

    /**
     * 屏幕状态 监听
     *
     * @param context
     */
    public void registerScreenStateReceiver(Context context) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_SCREEN_ON);
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
        screenStateReceiver = new ScreenStateReceiver();
        context.registerReceiver(screenStateReceiver, intentFilter);
    }

    /**
     * 广播解注册
     *
     * @param context
     */
    public void unRegisterScreenStateReceiver(Context context) {
        if (null != screenStateReceiver) {
            context.unregisterReceiver(screenStateReceiver);
        }
    }

    /**
     * 启动 1像素 的Activity
     *
     * @param context
     */
    public void startOnePixActivity(Context context) {
        Intent intent = new Intent(context, OnePixelActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 关闭1 像素Activity
     */
    public void finishOnePixelActivity(Context context) {
        //方案一：ScreenStateManager 持有 OnePixelActivity 的引用，这个必须依靠 OnePixelActivity onCreate的时候传进来。

        //方案二：通过发广播的方式。OnePixelActivity 接收到广播后，自己结束。
        Intent intent = new Intent("finish");
        context.sendBroadcast(intent);
    }

    /**
     * 当 1 像素Activity 启动的时候，向 ScreenStateManager 传入它的引用。以便后期停止应用。
     */
    public void setOnePixelActivityReference() {

    }
}
