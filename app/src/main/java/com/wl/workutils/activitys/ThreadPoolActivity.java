package com.wl.workutils.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.wl.workutils.R;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadPoolActivity extends AppCompatActivity {

    private String TAG = "ThreadPoolActivity";

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            for(int i = 0; i <1000 ; i++) {
                if(1000%100==0) {
                    Log.d(TAG,"=====>"+i);
                }

            }
        }
    };
    private ScheduledExecutorService threadpool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_pool);

    }

    public void btn_touch(View view){
        threadpool = Executors.newScheduledThreadPool(4);
        threadpool.schedule(runnable,2000, TimeUnit.MILLISECONDS);
        threadpool.scheduleAtFixedRate(runnable,0,300,TimeUnit.MILLISECONDS);
    }
}
