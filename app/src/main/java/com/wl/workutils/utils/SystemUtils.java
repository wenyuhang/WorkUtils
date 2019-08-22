package com.wl.workutils.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.wl.workutils.app.App;
import com.wl.workutils.config.Action;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION;

/**
 * Created by ${wyh} on 2018/5/9.
 * 开打系统工具
 */

public class SystemUtils {

    private Uri cameraUri;
    private static SystemUtils photoUtils;
    private ArrayList<Integer> dates;
    private String[] split;
    private SimpleDateFormat df;
    private String time;
    private static int StatusBarHeight;

    /**
     * 实例化单例对象
     *
     * @return
     */
    public static SystemUtils getInstance() {
        if (photoUtils == null) {
            synchronized (SystemUtils.class) {
                if (photoUtils == null) {
                    photoUtils = new SystemUtils();
                }
            }
        }
        return photoUtils;
    }

    /**
     * 获取系统状态栏高度
     * @param context
     * @return
     */
    public int getStatusBarHeight(Context context) {
        if (StatusBarHeight != 0) {
            return StatusBarHeight;
        } else {
            Class<?> c = null;
            Object obj = null;
            Field field = null;
            int sbar = 0;

            try {
                c = Class.forName("com.android.internal.R$dimen");
                obj = c.newInstance();
                field = c.getField("status_bar_height");
                int x = Integer.parseInt(field.get(obj).toString());
                sbar = context.getResources().getDimensionPixelSize(x);
            } catch (Exception var7) {
                var7.printStackTrace();
            }

            StatusBarHeight = sbar;
            return StatusBarHeight;
        }
    }

    /**
     * 打开相机
     * @param activity
     * @param t
     * @return
     */
    public Uri openCamera(Activity activity, int t) {
        try {
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            File file = new File(Environment.getExternalStorageDirectory(), "temp.jpg");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                //注意需要跟provider中的authorities一行
                cameraUri = FileProvider.getUriForFile(activity, "com.yoawo.duiduiandroid.fileprovider", file);
            } else {
                cameraUri = Uri.fromFile(file);
            }
            intent.putExtra(MediaStore.EXTRA_OUTPUT, cameraUri);
            activity.startActivityForResult(intent, t);
        }catch (Exception e){
            T.showToast("请设置应用权限");
        }

        return cameraUri;
    }

//    /**
//     * 打开相机
//     * @param activity
//     * @param t
//     * @return
//     */
//    public Uri openCamera(BaseFragment activity, int t) {
//        try {
//            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
//            File file = new File(Environment.getExternalStorageDirectory(), "temp.jpg");
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                //注意需要跟provider中的authorities一行
//                cameraUri = FileProvider.getUriForFile(activity.getActivity(), "com.yoawo.duiduiandroid.fileprovider", file);
//            } else {
//                cameraUri = Uri.fromFile(file);
//            }
//            intent.putExtra(MediaStore.EXTRA_OUTPUT, cameraUri);
//            activity.startActivityForResult(intent, t);
//        }catch (Exception e){
//            T.showToast("请设置应用权限");
//        }
//        return cameraUri;
//    }

    /**
     * 打开系统相册
     *
     * @param activity
     */
    public void openAlbum(Activity activity) {
        Intent intent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        activity.startActivityForResult(intent, Action.TYPE_ALBUM);
    }

//    /**
//     * 打开系统相册
//     *
//     * @param activity
//     */
//    public void openAlbum(BaseFragment activity) {
//        Intent intent = new Intent(Intent.ACTION_PICK,
//                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        activity.startActivityForResult(intent, Action.TYPE_ALBUM);
//    }

    //打开系统媒体库
    public void openVideo(Activity activity){
        Intent intent = new Intent();
        intent.setType("video/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        activity.startActivityForResult(intent, Action.TYPE_VIDEO);
    }

//    //打开系统媒体库
//    public void openVideo(BaseFragment activity){
//        Intent intent = new Intent();
//        intent.setType("video/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        intent.addCategory(Intent.CATEGORY_OPENABLE);
//        activity.startActivityForResult(intent, Action.TYPE_VIDEO);
//    }


    /**
     * 剪切图片
     * @param uri
     * @param activity
     * @param crop
     * @param aspectX
     * @param aspectY
     * @return
     */
    public Uri cropPhoto(Uri uri, Activity activity, boolean crop, int aspectX, int aspectY) {
        File file = new File(Environment.getExternalStorageDirectory(), "cropImage.jpg");
        if (file.exists())
            file.delete();
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent("com.android.camera.action.CROP");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(FLAG_GRANT_READ_URI_PERMISSION);
        }
        Uri cropUri = Uri.fromFile(file);
        intent.setDataAndType(uri, "image/*");
        //裁剪图片的宽高比例
        if (crop) {
            intent.putExtra("aspectX", aspectX);
            intent.putExtra("aspectY", aspectY);
        }

        intent.putExtra("crop", "true");//可裁剪
        // 裁剪后输出图片的尺寸大小
//        intent.putExtra("outputX", 400);
//        intent.putExtra("outputY", 200);
        intent.putExtra("scale", true);//支持缩放
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, cropUri);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());//输出图片格式
        intent.putExtra("noFaceDetection", true);//取消人脸识别
        activity.startActivityForResult(intent, Action.CODE_CROP);
        return cropUri;
    }

    /**
     * 剪切图片
     * @param uri
     * @param activity
     * @param crop
     * @param aspectX
     * @param aspectY
     * @return
     */
//    public Uri cropPhoto(Uri uri, BaseFragment activity, boolean crop, int aspectX, int aspectY) {
//        File file = new File(Environment.getExternalStorageDirectory(), "cropImage.jpg");
//        if (file.exists())
//            file.delete();
//        try {
//            file.createNewFile();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Intent intent = new Intent("com.android.camera.action.CROP");
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            intent.addFlags(FLAG_GRANT_READ_URI_PERMISSION);
//        }
//        Uri cropUri = Uri.fromFile(file);
//        Log.e("TAG", cropUri.toString());
//        intent.setDataAndType(uri, "image/*");
//        //裁剪图片的宽高比例
//        if (crop) {
//            intent.putExtra("aspectX", aspectX);
//            intent.putExtra("aspectY", aspectY);
//        }
//
//        intent.putExtra("crop", "true");//可裁剪
//        // 裁剪后输出图片的尺寸大小
////        intent.putExtra("outputX", 400);
////        intent.putExtra("outputY", 200);
//        intent.putExtra("scale", true);//支持缩放
//        intent.putExtra("return-data", false);
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, cropUri);
//        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());//输出图片格式
//        intent.putExtra("noFaceDetection", true);//取消人脸识别
//        activity.startActivityForResult(intent, CODE_CROP);
//        return cropUri;
//    }

    /**
     * 拨打电话（跳转到拨号界面，用户手动点击拨打）
     *
     * @param phoneNum 电话号码
     */
    public void callPhone(String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        App.context.startActivity(intent);
    }


    /**
     * 获取系统日期
     * @return
     */
    public ArrayList<Integer> getSystemDate(){
        df = new SimpleDateFormat("yyyy-MM-dd");
        split = df.format(System.currentTimeMillis()).split("-");
        dates = new ArrayList<>();
        dates.add(Integer.parseInt(split[0]));
        dates.add(Integer.parseInt(split[1]));
        dates.add(Integer.parseInt(split[2]));
        return dates;
    }
    /**
     * 获取系统日期
     * @return
     */
    public ArrayList<Integer> getSystemDate(String date){
        df = new SimpleDateFormat("yyyy-MM-dd");
        split = date.split("-");
        dates = new ArrayList<>();
        dates.add(Integer.parseInt(split[0]));
        dates.add(Integer.parseInt(split[1]));
        dates.add(Integer.parseInt(split[2]));
        return dates;
    }

    /**
     * 获取系统日期
     * @return
     */
    public String getSystemDate(long date){
        df = new SimpleDateFormat("yyyy-MM-dd");
        time = df.format(date);
        return time;
    }

    /**
     * 判断软键盘是否弹出
     */
    public boolean isSHowKeyboard(Context context, View v) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(context.INPUT_METHOD_SERVICE);
        if (imm.hideSoftInputFromWindow(v.getWindowToken(), 0)) {
            imm.showSoftInput(v, 0);
            return true;
            //软键盘已弹出
        } else {
            return false;
            //软键盘未弹出
        }
    }

    public String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }


    /**
     * 获取当前版本
      * @param context
     * @return
     */
    public int getAppVersionCode(Context context) {
        String versionName = "";
        int versionCode = 0;
        try {
            // ---get the package info---
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
            //versioncode = pi.versionCode;
            versionCode = pi.versionCode;
            if (versionName == null || versionName.length() <= 0) {
                return versionCode;
            }
        } catch (Exception e) {
        }
        return versionCode;

    }


    /**
     * 安装apk
     */
    public void installApk(Activity activity, File file) {
        if (Build.VERSION.SDK_INT >= 24) {//判读版本是否在7.0以上
            Uri apkUri = FileProvider.getUriForFile(activity, "com.yoawo.duiduiandroid.fileprovider", file);//在AndroidManifest中的android:authorities值
            Intent install = new Intent(Intent.ACTION_VIEW);
            install.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            install.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);//添加这一句表示对目标应用临时授权该Uri所代表的文件
            install.setDataAndType(apkUri, "application/vnd.android.package-archive");
            activity.startActivity(install);
        } else {
            Intent install = new Intent(Intent.ACTION_VIEW);
            install.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            install.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            activity.startActivity(install);
        }
    }
}
