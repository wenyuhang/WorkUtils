package com.wl.workutils.activitys;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.wl.diooto.Diooto;
import com.wl.diooto.config.DiootoConfig;
import com.wl.diooto.interfaces.DefaultCircleProgress;
import com.wl.workutils.R;
import com.wl.workutils.widget.SimpleControlPanel;

import org.salient.artplayer.MediaPlayerManager;
import org.salient.artplayer.ScaleType;
import org.salient.artplayer.VideoView;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.panpf.sketch.SketchImageView;

public class LookImgActivity extends AppCompatActivity {

    @Bind(R.id.srcImageView)
    SketchImageView srcImageView;
    @Bind(R.id.srcImageView_video)
    SketchImageView srcImageViewVideo;
    @Bind(R.id.my_recyclerview)
    RecyclerView myRecyclerview;
    @Bind(R.id.srcImageView_page)
    SketchImageView srcImageViewPage;
    private boolean isImmersive = true;
    private String path;
    private String video_path;
    private Bitmap bitmap;

    String[] normalImageUlr = new String[]{
            "http://img1.juimg.com/140908/330608-140ZP1531651.jpg",
            "https://github.com/moyokoo/Media/blob/master/diooto2.jpg?raw=true",
            "https://github.com/moyokoo/Media/blob/master/diooto3.jpg?raw=true",
            "https://github.com/moyokoo/Media/blob/master/diooto4.jpg?raw=true",
            "https://github.com/moyokoo/Media/blob/master/diooto5.jpg?raw=true",
            "https://github.com/moyokoo/Media/blob/master/diooto6.jpg?raw=true",
            "https://github.com/moyokoo/Media/blob/master/diooto7.png?raw=true",
            "https://github.com/moyokoo/Media/blob/master/diooto8.png?raw=true",
            "https://github.com/moyokoo/Media/blob/master/diooto9.jpg?raw=true"
    };
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_look_img);
        ButterKnife.bind(this);

        path = "http://img1.juimg.com/140908/330608-140ZP1531651.jpg";
        video_path = "http://bmob-cdn-982.b0.upaiyun.com/2017/02/23/266454624066f2b680707492a0664a97.mp4";
        Glide.with(this).load(path).into(srcImageView);
        bitmap = getNetBitmap(video_path, 100, 100);
        if (bitmap != null) {
            srcImageViewVideo.setImageBitmap(bitmap);
        }
        //列表
        recyclerView = new RecyclerView(this);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(new MainAdapter());

        myRecyclerview.setLayoutManager(new GridLayoutManager(this, 3));
        myRecyclerview.setAdapter(new MainAdapter());
    }

    @OnClick({R.id.srcImageView, R.id.srcImageView_video, R.id.srcImageView_page})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.srcImageView://查看单张图片
                lookPhoto();
                break;
            case R.id.srcImageView_video://查看视频
                lookVideo();
                break;
            case R.id.srcImageView_page:
                lookPage();
                break;
        }
    }


    //加载单张图片
    private void lookPhoto() {
        //加载单张图片
        Diooto diooto = new Diooto(this)
                .urls(path)
                .type(DiootoConfig.PHOTO)
                .immersive(true)
                .position(0)
                .views(srcImageView)
                .loadPhotoBeforeShowBigImage((sketchImageView, position1) -> {
                    sketchImageView.displayImage(path);
                })
                .start();
    }

    //查看视频
    private void lookVideo() {
        //加载视频
        Diooto diooto = new Diooto(this)
                .urls(path)
                .position(0)
                .views(srcImageViewVideo)
                .type(DiootoConfig.VIDEO)
                .immersive(isImmersive)
                .setProgress(new DefaultCircleProgress())
                //提供视频View
                .onProvideVideoView(() -> new VideoView(this))
                //显示视频加载之前的缩略图
                .loadPhotoBeforeShowBigImage((sketchImageView, position13) -> sketchImageView.displayImage(path))
                //动画到最大化时的接口
                .onVideoLoadEnd((dragDiootoView, sketchImageView, progressView) -> {
                    VideoView videoView = (VideoView) dragDiootoView.getContentView();
                    SimpleControlPanel simpleControlPanel = new SimpleControlPanel(this);
                    simpleControlPanel.setOnClickListener(v -> dragDiootoView.backToMin());
                    simpleControlPanel.setOnVideoPreparedListener(() -> {
                        sketchImageView.setVisibility(View.GONE);
                        progressView.setVisibility(View.GONE);
                    });
                    videoView.setControlPanel(simpleControlPanel);
                    videoView.setUp(video_path);
                    videoView.start();
                    dragDiootoView.notifySize(1920, 1080);
                    MediaPlayerManager.instance().setScreenScale(ScaleType.SCALE_CENTER_CROP);
                })
                //到最小状态的接口
                .onFinish(dragDiootoView -> MediaPlayerManager.instance().releasePlayerAndView(this))
                .start();
    }

    //查看列表
    private void lookPage() {
//        String[] strings = isLong ? longImageUrl : normalImageUlr;
        Diooto diooto = new Diooto(this)
                .urls(normalImageUlr)
                .type(DiootoConfig.PHOTO)
                .immersive(true)
                .position(0, 0)
                .views(recyclerView, R.id.srcImageView)
                .loadPhotoBeforeShowBigImage((sketchImageView, position12) -> {
                    Log.e("TAG", "loadPhotoBeforeShowBigImage:" + position12);
//                                Glide.with(context).load(normalImageUlr[position]).into(sketchImageView);
//                                sketchImageView.displayImage(normalImageUlr[position]);
                    sketchImageView.setOnLongClickListener(v -> {
                        Toast.makeText(this, "Long click", Toast.LENGTH_SHORT).show();
                        return false;
                    });
                })
                .start();
    }

    class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder> {

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(
                    LookImgActivity.this).inflate(R.layout.item_grid, parent,
                    false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {
            holder.srcImageView.displayImage(normalImageUlr[position]);
//            holder.srcImageView.setShowGifFlagEnabled(R.drawable.ic_gif);
            holder.srcImageView.setOnClickListener(srcView -> {
                Diooto diooto = new Diooto(LookImgActivity.this)
                        .urls(normalImageUlr)
                        .type(DiootoConfig.PHOTO)
                        .immersive(isImmersive)
                        .position(holder.getAdapterPosition(), 0)
                        .views(myRecyclerview, R.id.srcImageView)
                        .loadPhotoBeforeShowBigImage((sketchImageView, position12) -> {
                            Log.e("TAG", "loadPhotoBeforeShowBigImage:" + position12);
//                                Glide.with(context).load(normalImageUlr[position]).into(sketchImageView);
//                                sketchImageView.displayImage(normalImageUlr[position]);
                            sketchImageView.setOnLongClickListener(v -> {
                                Toast.makeText(LookImgActivity.this, "Long click", Toast.LENGTH_SHORT).show();
                                return false;
                            });
                        })
                        .start();
            });
        }

        @Override
        public int getItemCount() {
            return normalImageUlr.length;
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            SketchImageView srcImageView;

            public MyViewHolder(View view) {
                super(view);
                srcImageView = view.findViewById(R.id.srcImageView);
            }
        }

    }


    @Override
    protected void onPause() {
        super.onPause();
        MediaPlayerManager.instance().pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MediaPlayerManager.instance().releasePlayerAndView(this);
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public static Bitmap getNetBitmap(String url, int width, int height) {
        Bitmap bitmap = null;
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        int kind = MediaStore.Video.Thumbnails.MINI_KIND;
        try {
            if (Build.VERSION.SDK_INT >= 14) {
                retriever.setDataSource(url, new HashMap<String, String>());
            } else {
                retriever.setDataSource(url);
            }
            bitmap = retriever.getFrameAtTime();
        } catch (IllegalArgumentException ex) {
            // Assume this is a corrupt video file
        } catch (RuntimeException ex) {
            // Assume this is a corrupt video file.
        } finally {
            try {
                retriever.release();
            } catch (RuntimeException ex) {
                // Ignore failures while cleaning up.
            }
        }
        if (kind == MediaStore.Images.Thumbnails.MICRO_KIND && bitmap != null) {
            bitmap = ThumbnailUtils.extractThumbnail(bitmap, width, height, ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
        }
        return bitmap;
    }
}
