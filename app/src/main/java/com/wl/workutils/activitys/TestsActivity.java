package com.wl.workutils.activitys;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.wl.workutils.R;

public class TestsActivity extends AppCompatActivity {

    private TextView tv_test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tests);
        tv_test = findViewById(R.id.tv_test);
    }

    public void but(View view){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();//Looper初始化
                //Handler初始化 需要注意, Handler初始化传入Looper对象是子线程中缓存的Looper对象
                new Handler(Looper.myLooper());
                Looper.loop();//死循环
                //注意: Looper.loop()之后的位置代码在Looper退出之前不会执行,(并非永远不执行)
            }
        }).start();
    }

    public void tv_green(View view){
        Toast.makeText(TestsActivity.this,"点击了我",Toast.LENGTH_SHORT).show();
    }
}
