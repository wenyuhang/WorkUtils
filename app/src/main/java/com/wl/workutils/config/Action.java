package com.wl.workutils.config;

/**
 * Created by wyh on 2017/11/7.
 */

public class Action {


    //    返回code值
    public static final int CD_SUCCESS_CODE = 0;              //返回成功





    //    OnActivityResult    requestcode值
    public static final int CLOSE = 1000;                    //取消
    public static final int SCAN = 1001;                     //扫一扫
    public static final int TYPE_CAMERA = 1201;              //拉起相机
    public static final int TYPE_ALBUM = 1202;               //相册
    public static final int TYPE_MORE_ALBUM = 1203;          //相册多选
    public static final int TYPE_VIDEO = 1204;               //打开系统媒体库
    public static final int CODE_CROP = 1205;                //图片裁剪
    public static final int CAMERA_ERROR = 1206;             //自定义相机   拍照错误
    public static final int CAMERA_CAPTURE = 1207;           //自定义相机    拍照
    public static final int CAMERA_RECORD = 1208;            //自定义相机    拍摄
    public static final int CAMERA_CUSTOM = 1209;            //拉起自定义相机





    //   EventBus    code


}
