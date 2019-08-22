package com.wl.workutils.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wl.workutils.R;
import com.wl.workutils.adapters.TabAdapter;

import java.util.ArrayList;

/**
 * create by wyh on 2019/6/25
 */

public class Test2Fragment extends Fragment{

    private TabAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = View.inflate(getActivity(), R.layout.fragment_test , null);
        RecyclerView recy = inflate.findViewById(R.id.recyclerView);
        setAdapter(recy);
        return inflate;
    }

    private void setAdapter(RecyclerView recyclerView) {
        ArrayList<String> mData = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            mData.add(" 第" + i + "个item");
        }
        //设置RecycleView
        mAdapter = new TabAdapter();
        recyclerView.setAdapter(mAdapter);
        mAdapter.setNewData(mData);
        mAdapter.setEnableLoadMore(true);
        mAdapter.loadMoreEnd(true);
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {

            }
        });
    }
}
