package com.wl.workutils.app;

import android.app.Activity;
import android.app.Application;

/**
 * Created by ${WYH} on 2018/4/18.
 */

public class App extends Application {
    public static Activity context = null;
    @Override
    public void onCreate() {
        super.onCreate();
    }
}
