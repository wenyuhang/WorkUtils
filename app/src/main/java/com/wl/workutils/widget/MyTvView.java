package com.wl.workutils.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * create by wyh on 2019/6/1
 */

public class MyTvView extends android.support.v7.widget.AppCompatTextView{
    public MyTvView(Context context) {
        super(context);
    }

    public MyTvView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTvView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
//        Log.d("TAG_tou","\n"+super.onTouchEvent(ev)+"<-------onTouchEvent------->TextView");
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.d("TAG_tou","TextView------->"+super.onTouchEvent(ev)+"--------->ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("TAG_tou","TextView------->"+super.onTouchEvent(ev)+"--------->ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.d("TAG_tou","TextView------->"+super.onTouchEvent(ev)+"--------->ACTION_UP");
                break;
        }
        return super.onTouchEvent(ev);
    }

}
