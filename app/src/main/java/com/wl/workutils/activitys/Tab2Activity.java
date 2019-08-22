package com.wl.workutils.activitys;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.gyf.barlibrary.ImmersionBar;
import com.wl.workutils.R;
import com.wl.workutils.adapters.MyViewPager;
import com.wl.workutils.fragments.Test2Fragment;
import com.wl.workutils.fragments.TestFragment;

import java.util.ArrayList;
import java.util.List;

public class Tab2Activity extends AppCompatActivity {
    private List<String> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab2);
        initData(1);
        initView();
        ImmersionBar.with(this)
                .statusBarView(R.id.view)
                .navigationBarColor(android.R.color.white)
                .autoDarkModeEnable(true, 0.2f)
                .init();
//        Glide.with(this).asBitmap().load(Utils.getPic())
//                .apply(new RequestOptions().placeholder(R.mipmap.test))
//                .into((ImageView) findViewById(R.id.mIv));
    }


    private void initData(int pager) {
        mData = new ArrayList<>();
        for (int i = 1; i < 50; i++) {
            mData.add("pager" + pager + " 第" + i + "个item");
        }
    }

    private void initView() {
        //设置ToolBar
        ImmersionBar.setTitleBar(this, findViewById(R.id.toolbar));

        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.app_bar);

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        collapsingToolbarLayout.post(() -> {
            int offHeight = collapsingToolbarLayout.getHeight() - ImmersionBar.getStatusBarHeight(this);
            appBarLayout.addOnOffsetChangedListener((appBarLayout1, i) -> {
                ImmersionBar.with(this).statusBarDarkFont(Math.abs(i) >= offHeight, 0.2f).init();
            });
        });




        //设置TabLayout
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        ViewPager viewPager = findViewById(R.id.viewpag);
        ArrayList<Fragment> fragments = new ArrayList<Fragment>();
        fragments.add(new TestFragment());
        fragments.add(new Test2Fragment());
        MyViewPager viewpagerAdapter = new MyViewPager(getSupportFragmentManager(), fragments, null);
        viewPager.setAdapter(viewpagerAdapter);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setupWithViewPager(viewPager);

//        //TabLayout的切换监听
//        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                //切换的时候更新RecyclerView
//                initData(tab.getPosition() + 1);
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });

    }
}
