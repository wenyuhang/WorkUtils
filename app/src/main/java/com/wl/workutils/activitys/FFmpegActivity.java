package com.wl.workutils.activitys;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.mabeijianxi.smallvideorecord2.DeviceUtils;
import com.mabeijianxi.smallvideorecord2.JianXiCamera;
import com.mabeijianxi.smallvideorecord2.LocalMediaCompress;
import com.mabeijianxi.smallvideorecord2.model.AutoVBRMode;
import com.mabeijianxi.smallvideorecord2.model.LocalMediaConfig;
import com.mabeijianxi.smallvideorecord2.model.OnlyCompressOverBean;
import com.wl.workutils.R;
import com.wl.workutils.app.App;
import com.wl.workutils.utils.CacheUtils;
import com.wl.workutils.utils.T;
import com.wl.workutils.utils.UpLoadUtils;
import com.wl.workutils.utils.UriUtils;

import java.io.File;


public class FFmpegActivity extends AppCompatActivity {

    private String videoPath;
    private TextView tvPath;
    private RelativeLayout layoutPro;
    private String newpath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ffmpeg);
        tvPath = findViewById(R.id.tv_path);
        layoutPro = findViewById(R.id.layout_progress);
        newpath = CacheUtils.getExternalCacheDir(App.context) +
                System.currentTimeMillis() + "/.yoawo";
        initSmallVideo(this);
    }

    public  void initSmallVideo(Context context) {
        // Set the cache path for video
        File dcim = Environment
                .getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        if (DeviceUtils.isZte()) {
            if (dcim.exists()) {

//                VCamera.setVideoCachePath(dcim + "/mabeijianxi/");
                JianXiCamera.setVideoCachePath(newpath);
            } else {
//                VCamera.setVideoCachePath(dcim.getPath().replace("/sdcard/",
//                        "/sdcard-ext/")
//                        + "/mabeijianxi/");
                JianXiCamera.setVideoCachePath(newpath);
            }
        } else {
//            VCamera.setVideoCachePath(dcim + "/mabeijianxi/");
            JianXiCamera.setVideoCachePath(newpath);
        }
        JianXiCamera.initialize(false,null);
    }

    /**
     * 选择相册
     *
     * @param view
     */
    public void btnOpt(View view) {
        Intent intent = new Intent();
        intent.setType("video/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent, 101);
    }

    /**
     * 开始压缩
     *
     * @param view
     */
    public void btnRun(View view) {
        LocalMediaConfig.Buidler buidler = new LocalMediaConfig.Buidler();
        final LocalMediaConfig config = buidler
                .setVideoPath(videoPath)
                .captureThumbnailsTime(1)
                .doH264Compress(new AutoVBRMode())
                .setFramerate(15)
                .setScale(1.0f)
                .build();
        LocalMediaCompress localMediaCompress = new LocalMediaCompress(config);
        OnlyCompressOverBean onlyCompressOverBean = localMediaCompress.startCompress();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        switch (requestCode) {
//            case 101:
//                if (resultCode == RESULT_OK) {
//                    videoPath = UriUtils.getPath(this, data.getData());
//                }
//                break;
//
//        }

        if (requestCode == 101) {
            if (resultCode == RESULT_OK && data != null && data.getData() != null) {
                Uri uri = data.getData();

                String path = UriUtils.getPath(this, uri);//  修改后获取本地视频地址

                LocalMediaConfig.Buidler buidler = new LocalMediaConfig.Buidler();
                final LocalMediaConfig config = buidler
                        .setVideoPath(path)
                        .captureThumbnailsTime(1)
                        .doH264Compress(new AutoVBRMode())
                        .setFramerate(15)
                        .setScale(1.0f)
                        .build();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                layoutPro.setVisibility(View.VISIBLE);
                                T.showToast("压缩中...");
//                                showProgress("", "压缩中...", -1);
                            }
                        });
                        OnlyCompressOverBean onlyCompressOverBean = new LocalMediaCompress(config).startCompress();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                layoutPro.setVisibility(View.GONE);
                                T.showToast("压缩完成"+onlyCompressOverBean.getVideoPath());
                                tvPath.setText("地址位："+onlyCompressOverBean.getVideoPath());
                                upLoadImg(onlyCompressOverBean.getVideoPath());
//                                hideProgress();
                            }
                        });
//                        Intent intent = new Intent(FFmpegActivity.this, SendSmallVideoActivity.class);
//                        intent.putExtra(MediaRecorderActivity.VIDEO_URI, onlyCompressOverBean.getVideoPath());
//                        intent.putExtra(MediaRecorderActivity.VIDEO_SCREENSHOT, onlyCompressOverBean.getPicPath());
//                        startActivity(intent);
                    }
                }).start();
            }
        }
    }


    /**
     * 上传图片
     * @param path
     */
    public void upLoadImg(final String path) {
        final String img_name = path.split("/")[path.split("/").length - 1];
        new Thread(){
            @Override
            public void run() {
                super.run();

                // 构造上传请求
                PutObjectRequest put = new PutObjectRequest("oss-yoawo-com",img_name , path);
                try {
                    PutObjectResult putResult = UpLoadUtils.getInstance().initOSS(FFmpegActivity.this).putObject(put);
                    //上传成功
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            T.showToast("上传成功");
                            Log.d("TAG_WL","地址："+img_name);
                        }
                    });
                } catch (ClientException e) {
                    // 本地异常如网络异常等
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                           T.showToast("网络异常");
                        }
                    });
                } catch (ServiceException e) {
                    // 服务异常
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            T.showToast("上传失败");
                        }
                    });
                }
            }
        }.start();
    }

}
