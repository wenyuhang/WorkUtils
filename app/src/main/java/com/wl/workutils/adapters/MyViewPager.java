package com.wl.workutils.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * create by wyh on 2019/6/25
 */

public class MyViewPager extends FragmentPagerAdapter {

    private List<Fragment> fragments;
    public  String[]titles = {"视频","赞过"};
    public MyViewPager(FragmentManager fm, List<Fragment> fragments, String[]titles) {
        super(fm);
        this.fragments=fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
