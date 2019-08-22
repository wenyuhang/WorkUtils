package com.wl.workutils.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.wl.workutils.R;
import com.wl.workutils.activitys.AdapterActivity;
import com.wl.workutils.activitys.Behavior2Activity;
import com.wl.workutils.activitys.BottomActivity;
import com.wl.workutils.activitys.CusTextViewActivity;
import com.wl.workutils.activitys.CustomViewActivity;
import com.wl.workutils.activitys.FFmpegActivity;
import com.wl.workutils.activitys.GalleryActivity;
import com.wl.workutils.activitys.ImmersionBarActivity;
import com.wl.workutils.activitys.ListImageActivity;
import com.wl.workutils.activitys.LookImgActivity;
import com.wl.workutils.activitys.MapActivity;
import com.wl.workutils.activitys.MathActivity;
import com.wl.workutils.activitys.PermiActivity;
import com.wl.workutils.activitys.ScrollListActivity;
import com.wl.workutils.activitys.Tab2Activity;
import com.wl.workutils.activitys.TabActivity;
import com.wl.workutils.activitys.TestsActivity;
import com.wl.workutils.activitys.ThreadPoolActivity;
import com.wl.workutils.activitys.VRActivity;
import com.wl.workutils.app.App;

public class MainActivity extends AppCompatActivity {

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        App.context = this;
    }

    /**
     * 自定义状态栏
     * @param view
     */
    public void jumpTileBar(View view){
        intent = new Intent(MainActivity.this, ImmersionBarActivity.class);
        startActivity(intent);
    }

    /**
     * 模仿微信查看图片资源
     * @param view
     */
    public void  jumpImgLook(View view){
        intent = new Intent(MainActivity.this, LookImgActivity.class);
        startActivity(intent);
    }

    /**
     * Gallery
     * @param view
     */
    public void jumpGallery(View view){
        intent = new Intent(MainActivity.this, GalleryActivity.class);
        startActivity(intent);
    }

    /**
     * 权限管理
     * @param view
     */
    public void jumpPermi(View view){
        intent = new Intent(MainActivity.this, PermiActivity.class);
        startActivity(intent);
    }

    /**
     * VR
     * @param view
     */
    public void jumpVR(View view){
        intent = new Intent(MainActivity.this, VRActivity.class);
        startActivity(intent);
    }

    /**
     * 文字描边
     * @param view
     */
    public void jumpCusTextView(View view){
        intent = new Intent(MainActivity.this, CusTextViewActivity.class);
        startActivity(intent);
    }

    /**
     * 拉动头部放大ui
     * @param view
     */
    public void jumpBehavior(View view){
        intent = new Intent(MainActivity.this, Behavior2Activity.class);
        startActivity(intent);
    }

    /**
     * 腾讯地图
     * @param view
     */
    public void jumpMap(View view){
        intent = new Intent(MainActivity.this, MapActivity.class);
        startActivity(intent);
    }

    /**
     * 列表多条目
     * @param view
     */
    public void jumpChat(View view){
        intent = new Intent(MainActivity.this, AdapterActivity.class);
        startActivity(intent);
    }

    /**
     * 视频压缩
     * @param view
     */
    public void jumpCompress(View view){
        intent = new Intent(MainActivity.this, FFmpegActivity.class);
        startActivity(intent);
    }

    /**
     * 视频压缩
     * @param view
     */
    public void jumpCustomView(View view){
        intent = new Intent(MainActivity.this, CustomViewActivity.class);
        startActivity(intent);
    }

    /**
     * 测试
     * @param view
     */
    public void jumpTest(View view){
        intent = new Intent(MainActivity.this, TestsActivity.class);
        startActivity(intent);
    }
    /**
     * scrollview嵌套listview
     * @param view
     */
    public void jumpScrollList(View view){
        intent = new Intent(MainActivity.this, ScrollListActivity.class);
        startActivity(intent);
    }

    /**
     * 列表查看图片
     * @param view
     */
    public void jumpListImage(View view){
        intent = new Intent(MainActivity.this, ListImageActivity.class);
        startActivity(intent);
    }

    /**
     * 抖音评论列表页
     * @param view
     */
    public void jumpBottom(View view){
        intent = new Intent(MainActivity.this, BottomActivity.class);
        startActivity(intent);
    }

    /**
     * TAb页
     * @param view
     */
    public void jumpTab(View view){
        intent = new Intent(MainActivity.this, TabActivity.class);
        startActivity(intent);
    }

    /**
     * TAb2页
     * @param view
     */
    public void jumpTab2(View view){
        intent = new Intent(MainActivity.this, Tab2Activity.class);
        startActivity(intent);
    }

    /**
     * math
     * @param view
     */
    public void jumpMath(View view){
        intent = new Intent(MainActivity.this, MathActivity.class);
        startActivity(intent);
    }

    /**
     * 线程池
     * @param view
     */
    public void jumpThreadPool(View view){
        intent = new Intent(MainActivity.this, ThreadPoolActivity.class);
        startActivity(intent);
    }

}
