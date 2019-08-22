package com.wl.workutils.desgin;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * create by wyh on 2019/4/17
 */

public class RopeView extends View{
    private Paint mPaint = new Paint();
    public RopeView(Context context) {
        super(context);
    }

    public RopeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RopeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        mPaint.setColor(Color.parseColor("#ff0000"));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(0,0,getWidth(),1,mPaint);
    }
}
