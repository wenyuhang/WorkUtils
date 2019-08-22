package com.wl.workutils.activitys;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wl.diooto.Diooto;
import com.wl.diooto.config.DiootoConfig;
import com.wl.workutils.R;

import me.panpf.sketch.SketchImageView;

public class ListImageActivity extends AppCompatActivity {
    String[] normalImageUlr = new String[]{
            "http://spub-dev.yoawo.com/store/10001.jpg",
            "http://spub-dev.yoawo.com/store/10002_.jpg",
            "http://spub-dev.yoawo.com/store/10003.jpg",
            "http://spub-dev.yoawo.com/store/10004.jpg",
            "http://spub-dev.yoawo.com/store/10010.jpg",
            "http://spub-dev.yoawo.com/store/10006.jpg",
            "http://spub-dev.yoawo.com/store/10007.jpg",
            "http://spub-dev.yoawo.com/store/10008.jpg",
            "http://spub-dev.yoawo.com/store/10009.jpg"
    };
    private RecyclerView recyclerView;
    private RecyclerView myRecyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_image);

        //列表
        recyclerView = new RecyclerView(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MainAdapter());

        myRecyclerview = findViewById(R.id.my_recyclerview);
        myRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        myRecyclerview.setAdapter(new MainAdapter());
    }

    class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder> {

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(
                    ListImageActivity.this).inflate(R.layout.item_grid, parent,
                    false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final MainAdapter.MyViewHolder holder, final int position) {
            holder.srcImageView.displayImage(normalImageUlr[position]);
//            holder.srcImageView.setShowGifFlagEnabled(R.drawable.ic_gif);
            holder.srcImageView.setOnClickListener(srcView -> {
                    //加载单张图片
                    Diooto diooto = new Diooto(ListImageActivity.this)
                            .urls(normalImageUlr[position])
                            .type(DiootoConfig.PHOTO)
                            .immersive(true)
                            .position(0)
                            .views(holder.srcImageView)
                            .loadPhotoBeforeShowBigImage((sketchImageView, position1) -> {
                                sketchImageView.displayImage(normalImageUlr[position]);
                            })
                            .start();
            });
        }

        @Override
        public int getItemCount() {
            return normalImageUlr.length;
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            SketchImageView srcImageView;

            public MyViewHolder(View view) {
                super(view);
                srcImageView = view.findViewById(R.id.srcImageView);
            }
        }

    }
}
