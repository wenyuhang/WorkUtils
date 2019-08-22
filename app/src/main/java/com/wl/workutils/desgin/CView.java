package com.wl.workutils.desgin;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by WYH
 * on2019/7/18
 */
public class CView extends View{
    private Paint mPaint;

    /**
     * 圆的宽度
     */
    private int mCircleWidth = 3;
    public CView(Context context) {
        super(context);
    }

    public CView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);//取消锯齿
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(mCircleWidth);
        mPaint.setColor(Color.CYAN);

        /**
         * 这是一个居中的圆
         */
        float x = (getWidth() - getHeight() / 2) / 2;
        float y = getHeight() / 4;

        RectF oval = new RectF( x, y,
                getWidth() - x, getHeight() - y);

        Log.d("TAG-ss","width："+getWidth()+"height："+getHeight());
        Log.d("TAG-ss","x："+x+"==>y："+y+"==>l_x："+(getWidth() - x)+"==>l_y："+(getHeight()-y));

        canvas.drawArc(oval,360,100,true,mPaint);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(oval, mPaint);//画矩形
    }
}
