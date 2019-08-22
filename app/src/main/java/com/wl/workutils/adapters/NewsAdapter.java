package com.wl.workutils.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wl.workutils.R;
import com.wl.workutils.model.entity.News;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * create by wyh on 2019/4/1
 */

public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


    private final int DEFULT_VIEW = 1;
    private final int IMAGE_VIEW = 2;

    private Context context;
    private List<News> list;

    public NewsAdapter(Context context, List<News> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view;
        if(viewType == IMAGE_VIEW){
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.image_news_item, viewGroup, false);
            return new ImageViewHolder(view);
        } else {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.news_item, viewGroup, false);
            return new NewsViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof NewsViewHolder){
            NewsViewHolder viewHolder = (NewsViewHolder)holder;
            viewHolder.title.setText(list.get(position).getTitle());
        } else if(holder instanceof ImageViewHolder){
            ImageViewHolder viewHolder = (ImageViewHolder)holder;
            viewHolder.title.setText(list.get(position).getTitle());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
       if(Integer.parseInt(list.get(position).getType())==IMAGE_VIEW){
            return IMAGE_VIEW;
        } else {
            return super.getItemViewType(position);
        }
    }


    class NewsViewHolder extends RecyclerView.ViewHolder{

        @Bind(R.id.news_title) TextView title;
        @Bind(R.id.news_digest)TextView digest;
        @Bind(R.id.news_time)TextView time;
        @Bind(R.id.news_src)ImageView image;

        public NewsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class ImageViewHolder extends RecyclerView.ViewHolder{

        @Bind(R.id.news_title) TextView title;
        @Bind(R.id.image_left) ImageView imageLeft;
        @Bind(R.id.image_right) ImageView imageRight;
        @Bind(R.id.image_middle) ImageView imageMiddle;
        @Bind(R.id.news_time) TextView time;

        public ImageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
