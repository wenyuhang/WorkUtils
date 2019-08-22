package com.wl.workutils.desgin;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.wl.workutils.R;


/**
 * Created by WYH
 * on2019/7/17
 */
public class CustomView extends View {

    private Paint paint;      //圆画笔、路径画笔
    private Paint cirPaint;   //圆点画笔
    private Paint arrowPaint; //箭头画笔
    private int width;       //控件宽
    private int height;      //控件高
    private int radiu;       //物体  圆 半径
    private int dotRadio;    //圆点半径
    private float radiu_x;   //圆点x坐标
    private float radiu_y;   //圆点y坐标

    public final int SIN = 1;
    public final int COS = 2;
    public final int TAN = 3;
    private float value;           //勾股 得到边长
    private float angle;           //相对角度



    public CustomView(Context context) {
        super(context);
        init();
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private int[] colors = {Color.RED, Color.BLUE, Color.YELLOW};

    private void init() {
        //初始化变量
        dotRadio = 5;


        //初始化画笔
        initPaint();

    }

    /**
     * 初始化画笔
     */
    private void initPaint() {
        paint = new Paint();
        paint.setColor(colors[0]);
        paint.setStyle(Paint.Style.STROKE);
        paint.setTextSize(5);
//        paint.setColor(new Random().nextInt(2));
        paint.setAntiAlias(true);


        //初始化圆点画笔
        cirPaint = new Paint();
        cirPaint.setColor(colors[1]);
        cirPaint.setStyle(Paint.Style.FILL);
        cirPaint.setTextSize(5);
//        paint.setColor(new Random().nextInt(2));
        cirPaint.setAntiAlias(true);

        //初始化圆点画笔
        arrowPaint = new Paint();
        arrowPaint.setColor(colors[0]);
        arrowPaint.setStyle(Paint.Style.FILL);
        arrowPaint.setTextSize(5);
//        paint.setColor(new Random().nextInt(2));
        arrowPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        initData();

        //绘制目标人物
//        drawObj(canvas,"NO.1",radiu_x,radiu_y,radiu,angle);
        drawObj(canvas,"NO.2",100,100,radiu,angle);
        drawObj(canvas,"NO.3",150,100,radiu,12);

        //绘画物体圆
        drawCircle(canvas);

        //绘画人物
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        canvas.drawBitmap(bitmap,width/2-(bitmap.getWidth()/2),height-bitmap.getHeight(),paint);
        //绘画广角
        canvas.drawPath(setPath(width/2, height-(bitmap.getWidth()/2), 0, height-getRriangleHeight(TAN,42.5,width/2)), paint);
        canvas.drawPath(setPath(width/2, height-(bitmap.getWidth()/2), width, height-getRriangleHeight(TAN,42.5,width/2)), paint);
    }

    private void drawCircle(Canvas canvas) {
        canvas.drawCircle(width/2,height, (float) (height*0.2), paint);
        canvas.drawCircle(width/2,height, (float) (height*0.4), paint);
        canvas.drawCircle(width/2,height, (float) (height*0.6), paint);
        canvas.drawCircle(width/2,height, (float) (height*0.8), paint);
        canvas.drawCircle(width/2,height, height, paint);
    }


    /**
     * 绘画物体
     * @param canvas
     * @param type     文字
     * @param r_x      圆点  X坐标
     * @param r_y      圆点  Y坐标
     * @param r        半径
     * @param angle    角度
     */
    private void drawObj(Canvas canvas,String type,float r_x,float r_y,float r,float angle) {
        //绘制文字
        paint.setTextSize(12);
        canvas.drawText(type,r_x-10,r_y+15,paint);

        //绘画箭头
        paint.setTextSize(5);
        canvas.drawPath(setPath(r_x, r_y, r_x+getRriangleHeight(COS,angle,r)+r, r_x+getRriangleHeight(SIN,angle,r)+r), paint);
        canvas.drawPath(setArrowPath(r_x+getRriangleHeight(COS,angle,r)+r, r_y+getRriangleHeight(SIN,angle,r)+r, r_x, r_y), arrowPaint);

        canvas.drawPath(setPath(r_x, r_y, r_x+r, r_y), paint);
        canvas.drawPath(setArrowPath( r_x+r, r_y,r_x, r_y), arrowPaint);

        //绘画物体圆点
        canvas.drawCircle(r_x, r_y, dotRadio, cirPaint);
        //绘画物体圆
        canvas.drawCircle(r_x, r_y, r, paint);
        canvas.drawCircle(width/2,height, height, paint);
    }

    /**
     * 勾股定理
     * @param angle
     */
    private float getRriangleHeight(int type,double angle,float length) {
        //根据角度、边长获取边长
        switch (type){
            case SIN:
                value = (float) (length * Math.sin(angle * Math.PI / 180));
                break;
            case COS:
                value = (float) (length * Math.cos(angle * Math.PI / 180));
                break;
            case TAN:
                value = (float) (length * Math.tan(angle * Math.PI / 180));
                break;
        }

        return value;
    }

    /**
     * 初始化数据
     */
    private void initData() {
        width  = getWidth();
        height = getHeight();
        radiu = 30;
        radiu_x =20;
        radiu_y = 100;
//        line_one_x = width / 4;
//        line_one_y = height / 2;
        angle = 60;
    }



    /**
     * 绘制path路径
     *
     * @param x
     * @param y
     * @param lx
     * @param ly
     * @return
     */
    private Path setPath(float x, float y, float lx, float ly) {
        Path path = new Path();
        path.moveTo(x, y);
        path.lineTo(lx, ly);
        return path;
    }

    /**
     * 画箭头
     */
    public Path setArrowPath(float endX, float endY, float startX, float startY) {
        double H = 9; // 箭头高度
        double L = 6.25; // 底边的一半

        double angle = Math.atan(L / H); // 箭头角度
        double arrowLength = Math.sqrt(L * L + H * H); // 箭头的长度
        //箭头就是个三角形，我们已经有一个点了，根据箭头的角度和长度，确定另外2个点的位置
        double[] point1 = rotateVec(endX - startX, endY - startY, angle, arrowLength);
        double[] point2 = rotateVec(endX - startX, endY - startY, -angle, arrowLength);
        double point1_x = endX - point1[0];
        double point1_y = endY - point1[1];
        double point2_x = endX - point2[0];
        double point2_y = endY - point2[1];
        int x3 = (int) point1_x;
        int y3 = (int) point1_y;
        int x4 = (int) point2_x;
        int y4 = (int) point2_y;

        Path arrowPath = new Path();
        // 画线
        arrowPath.moveTo(endX, endY);
        arrowPath.lineTo(x3, y3);
        arrowPath.lineTo(x4, y4);
        arrowPath.close();

        return arrowPath;
    }

    // 计算

    /**
     * @param diffX       X的差值
     * @param diffY       Y的差值
     * @param angle       箭头的角度（箭头三角形的线与直线的角度）
     * @param arrowLength 箭头的长度
     */
    public double[] rotateVec(float diffX, float diffY, double angle, double arrowLength) {
        double arr[] = new double[2];
        // 下面的是公式，得出的是以滑动出的线段末点为中心点旋转angle角度后,线段起点的坐标，这个旋转后的线段也就是“变长了的箭头的三角形的一条边”
        //推导见注释1
        double x = diffX * Math.cos(angle) - diffY * Math.sin(angle);
        double y = diffX * Math.sin(angle) + diffY * Math.cos(angle);
        double d = Math.sqrt(x * x + y * y);
        //根据相似三角形，得出真正的箭头三角形顶点坐标，这里见注释2
        x = x / d * arrowLength;
        y = y / d * arrowLength;
        arr[0] = x;
        arr[1] = y;
        return arr;
    }
}
