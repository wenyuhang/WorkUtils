package com.wl.workutils.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.wl.workutils.R;

public class ScrollListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ScrollView scrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_list);
        scrollView = (ScrollView) findViewById(R.id.sv);
        recyclerView = (RecyclerView) findViewById(R.id.rv);
        GridLayoutManager gridLayoutManager = new GridLayoutManager (this,4);
        recyclerView.setLayoutManager(gridLayoutManager);
        MyRecyclerAdapter adapter = new MyRecyclerAdapter();

        recyclerView.setAdapter(adapter);
        scrollView.smoothScrollTo(0, 0);
    }

     class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyRecyclerViewHolder> {
         int[] normalImageUlr = new int[]{
                 R.mipmap.s10003,R.mipmap.s10008,R.mipmap.s10010,R.mipmap.s10102,R.mipmap.bg8,
                 R.mipmap.s10003,R.mipmap.s10008,R.mipmap.s10010,R.mipmap.s10102,R.mipmap.bg8
         };
        @Override
        public MyRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int position) {
            ImageView tv = new ImageView(parent.getContext());
            tv.setPadding(10, 0, 10, 0);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            tv.setLayoutParams(lp);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(parent.getContext(), "" + position, Toast.LENGTH_SHORT).show();
                }
            });
            return new MyRecyclerViewHolder(tv);
        }

        @Override
        public void onBindViewHolder(MyRecyclerViewHolder holder, int position) {
//        holder.tv.setText("RECYCLER " + position);
            holder.tv.setImageResource(normalImageUlr[position%10]);
//            Glide.with(ScrollListActivity.this).load(normalImageUlr[position%10]).into( holder.tv);
            holder.tv.setTag(position);
        }

        @Override
        public int getItemCount() {
            return 100;
        }

        class MyRecyclerViewHolder extends RecyclerView.ViewHolder {

            public ImageView tv;

            public MyRecyclerViewHolder(ImageView itemView) {
                super(itemView);
                tv = itemView;
            }
        }
    }
}
