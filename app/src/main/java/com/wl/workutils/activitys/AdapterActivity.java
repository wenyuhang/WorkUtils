package com.wl.workutils.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.wl.workutils.R;
import com.wl.workutils.adapters.NewsAdapter;
import com.wl.workutils.model.entity.News;

import java.util.ArrayList;

public class AdapterActivity extends AppCompatActivity {

    private RecyclerView recyView;
    private ArrayList<News> dataList;
    private NewsAdapter adapter;
    private News news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter);
        initView();
    }

    private void initView() {
        recyView = findViewById(R.id.recy_view);
        dataList = new ArrayList<>();
        initData();
        adapter = new NewsAdapter(this,dataList);
        recyView.setLayoutManager(new LinearLayoutManager(this));
        recyView.setAdapter(adapter);
    }

    private void initData() {
        for(int i = 0; i < 10; i++) {
            news = new News();
            news.setTitle("Title==="+i);
            if(i%2==0) {
                news.setType(String.valueOf(2));
            }else {
                news.setType(String.valueOf(1));
            }
            dataList.add(news);
        }
    }
}
