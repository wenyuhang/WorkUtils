package com.wl.workutils.activitys;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wl.workutils.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GalleryActivity extends AppCompatActivity {

    @Bind(R.id.recy_view)
    RecyclerView recyView;
    private ArrayList<DataBean> lists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        ButterKnife.bind(this);
        initData();
        HorLayoutManager mLayoutManager = new HorLayoutManager(this ,OrientationHelper.HORIZONTAL);
        RecyclerViewadapter mAdapter = new RecyclerViewadapter(lists,this);
        recyView.setLayoutManager(mLayoutManager);
        recyView.setAdapter(mAdapter);
    }

    private void initData() {
        lists = new ArrayList<>();
        lists.add(new DataBean("Smart","青青原上草，一岁一枯荣"));
        lists.add(new DataBean("Smart","青青原上草，一岁一枯荣"));
        lists.add(new DataBean("Smart","青青原上草，一岁一枯荣"));
        lists.add(new DataBean("Smart","青青原上草，一岁一枯荣"));
        lists.add(new DataBean("Smart","青青原上草，一岁一枯荣"));
        lists.add(new DataBean("Smart","青青原上草，一岁一枯荣"));

    }
}

class HorLayoutManager extends LinearLayoutManager implements RecyclerView.OnChildAttachStateChangeListener{

    private RecyclerView mRecyclerView;
    private OnViewPagerListener mOnViewPagerListener;
    private PagerSnapHelper mPagerSnapHelper;
    private int mDrift;//位移，用来判断移动方向

    public HorLayoutManager(Context context,int orientation) {
        super(context, orientation, false);
        mPagerSnapHelper = new PagerSnapHelper();
    }

    public HorLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
        mPagerSnapHelper = new PagerSnapHelper();
    }

    public void setmOnViewPagerListener(OnViewPagerListener mOnViewPagerListener) {
        this.mOnViewPagerListener = mOnViewPagerListener;
    }




    @Override
    public void onAttachedToWindow(RecyclerView view) {
        super.onAttachedToWindow(view);
        mPagerSnapHelper.attachToRecyclerView(view);//设置RecyclerView每次滚动一页
        mRecyclerView = view;
        mRecyclerView.addOnChildAttachStateChangeListener(this);
    }

    /**
     * 滑动状态的改变
     * 缓慢拖拽-> SCROLL_STATE_DRAGGING
     * 快速滚动-> SCROLL_STATE_SETTLING
     * 空闲状态-> SCROLL_STATE_IDLE
     * @param state
     */
    @Override
    public void onScrollStateChanged(int state) {
        super.onScrollStateChanged(state);
        if (state == RecyclerView.SCROLL_STATE_IDLE){
            View viewIdle = mPagerSnapHelper.findSnapView(this);
            int positionIdle = getPosition(viewIdle);
            if (mOnViewPagerListener != null && getChildCount() == 1) {
                mOnViewPagerListener.onPageSelected(positionIdle, positionIdle == getItemCount() - 1);
            }
        }
    }

    /**
     * 布局完成后调用
     * @param state
     */
    @Override
    public void onLayoutCompleted(RecyclerView.State state) {
        super.onLayoutCompleted(state);
        if (mOnViewPagerListener != null) {
            mOnViewPagerListener.onLayoutComplete();
        }
    }

    /**
     * 监听竖直方向的相对偏移量
     */
    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        this.mDrift = dy;
        return super.scrollVerticallyBy(dy, recycler, state);
    }

    /**
     * 监听水平方向的相对偏移量
     */
    @Override
    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
        this.mDrift = dx;
        return super.scrollHorizontallyBy(dx, recycler, state);
    }

    @Override
    public void onChildViewAttachedToWindow(@NonNull View view) {

    }

    @Override
    public void onChildViewDetachedFromWindow(@NonNull View view) {
        if (mDrift >= 0) {
            if (mOnViewPagerListener != null){
                mOnViewPagerListener.onPageRelease(true, getPosition(view));
            }
        } else {
            if (mOnViewPagerListener != null){
                mOnViewPagerListener.onPageRelease(false, getPosition(view));
            }
        }
    }

    public interface OnViewPagerListener{
        /*释放的监听*/
        void onPageRelease(boolean isNext,int position);
        /*选中的监听以及判断是否滑动到底部*/
        void onPageSelected(int position,boolean isBottom);
        /*布局完成的监听*/
        void onLayoutComplete();
    }

}

class DataBean{
    private String Autor;

    public DataBean(String autor, String content) {
        Autor = autor;
        Content = content;
    }

    public String getAutor() {
        return Autor;
    }

    public String getContent() {
        return Content;
    }

    private String Content;
}

class RecyclerViewadapter extends RecyclerView.Adapter {
    private List<DataBean> lists;
    private Context context;

    public RecyclerViewadapter(List<DataBean> lists, Context context) {
        this.lists = lists;
        this.context = context;
    }

    class myholder extends RecyclerView.ViewHolder{

        private TextView tv1,tv2;
        public myholder(View itemView) {
            super(itemView);
            tv1= (TextView) itemView.findViewById(R.id.tv1);
            tv2= (TextView) itemView.findViewById(R.id.tv2);


        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        myholder holder =new myholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recy_item,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.d("TAG", "onBindViewHolder: "+lists.get(position).getAutor());
        ((myholder)holder).tv1.setText(lists.get(position).getAutor());
        ((myholder)holder).tv2.setText(lists.get(position).getContent());

    }

    @Override
    public int getItemCount() {
        return lists.size();
    }
}
