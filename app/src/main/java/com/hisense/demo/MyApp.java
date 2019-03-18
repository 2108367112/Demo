package com.hisense.demo;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.test.LoaderTestCase;
import android.util.Log;

/**
 * Created by Administrator on 2018/12/28.
 */

public class MyApp extends Application {

    private static final String TAG = "MyApp";
    private ActivityLifeCycle activityLifeCycle = new ActivityLifeCycle();

    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(activityLifeCycle);
    }


    private class ActivityLifeCycle implements ActivityLifecycleCallbacks {

        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            Log.d(TAG, "onActivityCreated: " + activity.getClass().getName());
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
    }
}
