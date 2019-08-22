package com.wl.workutils.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.wl.workutils.R;
import com.wl.workutils.desgin.vr.GyroscopeManager;
import com.wl.workutils.desgin.vr.XImageView;
import com.zph.glpanorama.GLPanorama;

import butterknife.Bind;
import butterknife.ButterKnife;

public class VRActivity extends AppCompatActivity {

    @Bind(R.id.glp_view)
    GLPanorama glpView;
    @Bind(R.id.my_image)
    XImageView myImage;
    private GyroscopeManager gyroscopeManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vr);
        ButterKnife.bind(this);

        init();//        手动VR

        initData();//        手动VR
    }

    private void initData() {
        gyroscopeManager = new GyroscopeManager();
        myImage.setGyroscopeManager(gyroscopeManager);
    }

    @Override
    protected void onPause() {
        gyroscopeManager.unregister();
        super.onPause();
    }

    @Override
    protected void onResume() {
        gyroscopeManager.register(this);
        super.onResume();
    }

    private void init() {
        glpView.setGLPanorama(R.drawable.imggugong);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
