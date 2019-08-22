package com.wl.workutils.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * create by wyh on 2019/6/1
 */

public class MyView extends ScrollView{
    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
//        Log.d("TAG_tou","\n"+super.onTouchEvent(ev)+"<-------onTouchEvent------->ScrollView");
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.d("TAG_tou","ScrollView------->onTouchEvent------->"+super.onTouchEvent(ev)+"--------->ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("TAG_tou","ScrollView------->onTouchEvent------->"+super.onTouchEvent(ev)+"--------->ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.d("TAG_tou","ScrollView------->onTouchEvent------->"+super.onTouchEvent(ev)+"--------->ACTION_UP");
                break;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("TAG_tou", "ScrollView------->onInterceptTouchEvent------->" + super.onInterceptTouchEvent(ev) + "--------->ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("TAG_tou", "ScrollView------->onInterceptTouchEvent------->" + super.onInterceptTouchEvent(ev) + "--------->ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.d("TAG_tou", "ScrollView------->onInterceptTouchEvent------->" + super.onInterceptTouchEvent(ev) + "--------->ACTION_UP");
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
//        Log.d("TAG_tou","\n"+super.dispatchTouchEvent(ev)+"<-------dispatchTouchEvent------->ScrollView");
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("TAG_tou", "ScrollView------->dispatchTouchEvent------->" + super.dispatchTouchEvent(ev) + "--------->ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("TAG_tou", "ScrollView------->dispatchTouchEvent------->" + super.dispatchTouchEvent(ev) + "--------->ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.d("TAG_tou", "ScrollView------->dispatchTouchEvent------->" + super.dispatchTouchEvent(ev) + "--------->ACTION_UP");
                break;
        }
        return super.dispatchTouchEvent(ev);
    }
}
