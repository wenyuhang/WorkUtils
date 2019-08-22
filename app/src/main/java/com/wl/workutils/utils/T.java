package com.wl.workutils.utils;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wl.workutils.R;
import com.wl.workutils.app.App;


/**
 * Created by wyh on 2017/9/21.
 * Toast管理
 * 一个自定义的吐司工具类，可以修改任意布局
*/

public class T {

    //系统默认吐司
    public static void showToast(String msg){
        Toast toast = Toast.makeText(App.context, msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM,0,300);
        toast.show();
    }

    //时间长点的吐司
    public static void showLongToast(String msg){
        Toast.makeText(App.context,msg, Toast.LENGTH_LONG).show();
    }


    /**
     *  带图片的吐司提示
     * @param text
     */
    public static void showCustomImgToast(String text) {
        LayoutInflater inflater = LayoutInflater.from(App.context);
        View view = inflater.inflate(R.layout.toast_view, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.toast_image);
        imageView.setBackgroundResource(R.mipmap.icon_mark);
        TextView t = (TextView) view.findViewById(R.id.toast_text);
        t.setText(text);
        Toast toast = null;
        if (toast != null) {
            toast.cancel();
        }
        toast = new Toast(App.context);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
        toast.show();
    }

    /**
     *  带图片的吐司提示
     *  通过参数传递，可是设置吐司的图片和文字内容
     * @param text
     */
    public static void showCustomImgToast(String text,int imgResId) {
        LayoutInflater inflater = LayoutInflater.from(App.context);
        View view = inflater.inflate(R.layout.toast_view, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.toast_image);
        imageView.setBackgroundResource(R.mipmap.icon_mark);
        TextView t = (TextView) view.findViewById(R.id.toast_text);
        t.setText(text);
        Toast toast = null;
        if (toast != null) {
            toast.cancel();
        }
        toast = new Toast(App.context);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
        toast.show();
    }

    /**
     *  不带图片的吐司提示
     * @param text
     */
    public static void showCustomToast(String text) {
        LayoutInflater inflater = LayoutInflater.from(App.context);
        View view = inflater.inflate(R.layout.toast_view, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.toast_image);
        imageView.setVisibility(View.GONE);
        TextView t = (TextView) view.findViewById(R.id.toast_text);
        t.setText(text);
        Toast toast = null;
        if (toast != null) {
            toast.cancel();
        }
        toast = new Toast(App.context);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
        toast.show();
    }

    /**
     * 带图片的吐司，设置吐司弹出的位置为屏幕中心
     * @param text
     */
    public static void showCustomToastCenter(String text) {
        showCustomToastCenter(text, R.mipmap.icon_mark);
    }

    /**
     * 带图片的吐司，设置吐司弹出的位置为屏幕中心
     * 通过参数传递，可是设置吐司的图片和文字内容
     * @param text
     */
    public static void showCustomToastCenter(String text, int imgResId) {
        LayoutInflater inflater = LayoutInflater.from(App.context);
        View view = inflater.inflate(R.layout.toast_view, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.toast_image);
        imageView.setBackgroundResource(imgResId);
        TextView t = (TextView) view.findViewById(R.id.toast_text);
        t.setText(text);
        Toast toast = null;
        if (toast != null) {
            toast.cancel();
        }
        toast = new Toast(App.context);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
