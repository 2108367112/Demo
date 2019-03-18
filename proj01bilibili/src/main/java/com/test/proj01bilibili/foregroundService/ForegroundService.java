package com.test.proj01bilibili.foregroundService;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.test.proj01bilibili.R;
import com.test.proj01bilibili.activity.ForegroundActivity;

/**
 * 前台服务
 */
public class ForegroundService extends Service {
    private static final String TAG = "ForegroundService";
    private static final int SERVICE_ID = 1;

    static Notification notification;

    public ForegroundService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Intent intent = new Intent(this, ForegroundActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 2, intent, 0);
        //通知的创建  >>>>>>一切皆对象
        notification = new NotificationCompat.Builder(this)
                .setContentTitle("前台服务")
                .setContentText("1111")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent)
                .setWhen(System.currentTimeMillis())
                .build();
        if (Build.VERSION.SDK_INT < 18) {
            //启动前台服务
            startForeground(SERVICE_ID, notification);
        } else if (Build.VERSION.SDK_INT < 26) {
            startForeground(SERVICE_ID, notification);
            startService(new Intent(this, InnerService.class));
        } else if (Build.VERSION.SDK_INT >= 26) {

        }

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: 我被销毁了");
    }

    /***********  内部类服务    *************/
    public static class InnerService extends Service {

        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }

        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            startForeground(SERVICE_ID, notification);
            stopForeground(true);
            stopSelf();
            return super.onStartCommand(intent, flags, startId);
        }
    }
}
