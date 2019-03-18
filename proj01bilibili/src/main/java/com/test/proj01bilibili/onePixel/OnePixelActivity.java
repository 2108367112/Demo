package com.test.proj01bilibili.onePixel;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

public class OnePixelActivity extends AppCompatActivity {
    private static final String TAG = "OnePixelActivity";

    private FinishReceiver finishReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置窗口
        initWindow();
        //注册结束广播
        finishReceiver = new FinishReceiver();
        IntentFilter filter = new IntentFilter("finish");
        registerReceiver(finishReceiver, filter);
        //由于广播发送有一定时间差，此时用户可能再次打开屏幕。
        checkScreen();
    }

    private void initWindow() {
        Window window = getWindow();
        window.setGravity(Gravity.START | Gravity.TOP);
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = 1;
        params.height = 1;
        params.x = 0;
        params.y = 0;
        window.setAttributes(params);
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkScreen();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解注册
        unregisterReceiver(finishReceiver);
    }

    /**
     * 检查屏幕状态 isScreenOn为true  屏幕“亮”结束该Activity
     */
    private void checkScreen() {
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        if (pm.isScreenOn()) {
            finish();
        }
    }

    /**
     * 结束 OnePixelActivity 广播
     */
    private class FinishReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            //结束 OnePixelActivity
            OnePixelActivity.this.finish();
        }
    }
}
