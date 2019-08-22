package com.wl.workutils.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.wl.workutils.R;

public class MathActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math);
    }

    public void btn_touch(View view){
        double tan = Math.tan(45*Math.PI/180);
        Toast.makeText(this,"=====>"+tan,Toast.LENGTH_SHORT).show();
        Toast.makeText(this,"=====>"+Math.atan(1)/Math.PI*180,Toast.LENGTH_SHORT).show();
    }
}
