package com.wl.workutils.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.gyf.barlibrary.ImmersionBar;
import com.wl.workutils.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 自定义状态栏
 */
public class ImmersionBarActivity extends AppCompatActivity {

    @Bind(R.id.btn_one)
    Button btnOne;
    @Bind(R.id.btn_two)
    Button btnTwo;
    private ImmersionBar immersionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_immersion_bar);
        ButterKnife.bind(this);
        immersionBar = ImmersionBar.with(this);
        immersionBar.init();
        btnTwo.setWidth(200);
//        ImmersionBar.with(this)
//                .transparentStatusBar()  //透明状态栏，不写默认透明色
//                .transparentNavigationBar()  //透明导航栏，不写默认黑色(设置此方法，fullScreen()方法自动为 true)
//                .transparentBar()             //透明状态栏和导航栏，不写默认状态栏为透明色，导航栏为黑色（设置此方法，fullScreen()方法自动为 true）
//                .statusBarColor(R.color.colorPrimary)     //状态栏颜色，不写默认透明色
//                .navigationBarColor(R.color.colorPrimary) //导航栏颜色，不写默认黑色
//                .barColor(R.color.colorPrimary)  //同时自定义状态栏和导航栏颜色，不写默认状态栏为透明色，导航栏为黑色
//                .statusBarAlpha(0.3f)  //状态栏透明度，不写默认 0.0f
//                .navigationBarAlpha(0.4f)  //导航栏透明度，不写默认 0.0F
//                .barAlpha(0.3f)  //状态栏和导航栏透明度，不写默认 0.0f
//                .statusBarDarkFont(true)   //状态栏字体是深色，不写默认为亮色
//                .flymeOSStatusBarFontColor(R.color.btn3)  //修改 flyme OS 状态栏字体颜色
//                .fullScreen(true)      //有导航栏的情况下，activity 全屏显示，也就是 activity 最下面被导航栏覆盖，不写默认非全屏
//                .hideBar(BarHide.FLAG_HIDE_BAR)  //隐藏状态栏或导航栏或两者，不写默认不隐藏
//                .addViewSupportTransformColor(toolbar)  //设置支持 view 变色，可以添加多个 view，不指定颜色，默认和状态栏同色，还有两个重载方法
//                .titleBar(view)    //解决状态栏和布局重叠问题，任选其一
//                .titleBarMarginTop(view)     //解决状态栏和布局重叠问题，任选其一
//                .statusBarView(view)  //解决状态栏和布局重叠问题，任选其一
//                .fitsSystemWindows(true)    //解决状态栏和布局重叠问题，任选其一，默认为 false，当为 true 时一定要指定 statusBarColor()，不然状态栏为透明色，还有一些重载方法
//                .supportActionBar(true) //支持 ActionBar 使用
//                .statusBarColorTransform(R.color.orange)  //状态栏变色后的颜色
//                .navigationBarColorTransform(R.color.orange) //导航栏变色后的颜色
//                .barColorTransform(R.color.orange)  //状态栏和导航栏变色后的颜色
//                .removeSupportView(toolbar)  //移除指定 view 支持
//                .removeSupportAllView() //移除全部 view 支持
//                .navigationBarEnable(true)   //是否可以修改导航栏颜色，默认为 true
//                .navigationBarWithKitkatEnable(true)  //是否可以修改安卓 4.4 和 emui3.1 手机导航栏颜色，默认为 true
//                .fixMarginAtBottom(true)   //已过时，当 xml 里使用 android:fitsSystemWindows="true"属性时,解决 4.4 和 emui3.1 手机底部有时会出现多余空白的问题，默认为 false，非必须
//                .addTag("tag")  //给以上设置的参数打标记
//                .getTag("tag")  //根据 tag 获得沉浸式参数
//                .reset()  //重置所以沉浸式参数
//                .keyboardEnable(true)  //解决软键盘与底部输入框冲突问题，默认为 false，还有一个重载方法，可以指定软键盘 mode
//                .keyboardMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)  //单独指定软键盘模式
//                .setOnKeyboardListener(new OnKeyboardListener() {    //软键盘监听回调
//                    @Override
//                    public void onKeyboardChange(boolean isPopup, int keyboardHeight) {
//                        L.e(isPopup);  //isPopup 为 true，软键盘弹出，为 false，软键盘关闭
//                    }
//                })
//                .init();  //必须调用方可沉浸式
    }

    @OnClick({R.id.btn_one, R.id.btn_two})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_one:
                btnTwo.setHeight(200);
                break;
            case R.id.btn_two:
                immersionBar.reset().init();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (immersionBar != null)
            immersionBar.destroy();  //必须调用该方法，防止内存泄漏，不调用该方法，如果界面 bar 发生改变，在不关闭 app 的情况下，退出此界面再进入将记忆最后一次 bar 改变的状态
    }
}
