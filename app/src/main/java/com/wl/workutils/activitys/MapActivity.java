package com.wl.workutils.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tencent.tencentmap.mapsdk.maps.MapView;
import com.wl.workutils.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MapActivity extends AppCompatActivity {

    @Bind(R.id.map_view)
    MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
    }

    @Override
    protected void onDestroy() {
        ButterKnife.unbind(this);
        mapView.onDestroy();
        super.onDestroy();
    }
    @Override
    protected void onPause() {
        mapView.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    protected void onStop() {
        mapView.onStop();
        super.onStop();
    }
}
