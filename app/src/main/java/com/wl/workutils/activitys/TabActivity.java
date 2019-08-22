package com.wl.workutils.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.wl.workutils.R;
import com.wl.workutils.adapters.GoodLuckAdapter;
import com.wl.workutils.desgin.LoadMoreRecyclerView;
import com.wl.workutils.utils.T;

import java.util.ArrayList;
import java.util.List;

public class TabActivity extends AppCompatActivity implements LoadMoreRecyclerView.LoadMoreListener{
    private LoadMoreRecyclerView mRecyclerView;
    private GoodLuckAdapter mComplexAdapter;

    private List<String> mContentList = new ArrayList<>();

    private View mFakeTabView;
    private LinearLayoutManager mLayoutManager;
    private TextView mTvTabName;
    private RecyclerView tab_view;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        mRecyclerView = (LoadMoreRecyclerView) findViewById(R.id.recycler_view);
        tab_view = (RecyclerView) findViewById(R.id.tab_view);
        setAdapter();
        initData();
        initView();
    }

    private void setAdapter() {
        ArrayList<String> strings = new ArrayList<>();
        for(int i = 0; i <12 ; i++) {
            strings.add("TAG_TITLE"+"="+i);
        }

        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        tab_view.setLayoutManager(layoutManager);
    }

    private void initData() {
        for (int i = 0; i < 40; i++) {
            mContentList.add("Item " + i);
        }
    }

    private void initView() {

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mComplexAdapter = new GoodLuckAdapter(this,R.layout.item_content,mContentList);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mComplexAdapter);
        mRecyclerView.setAutoLoadMoreEnable(true);
        mRecyclerView.setLoadMoreListener(this);
    }

    @Override
    public void onLoadMore() {
        T.showToast("加载更多");
    }
}
