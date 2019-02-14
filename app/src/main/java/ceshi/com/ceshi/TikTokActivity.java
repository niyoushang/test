package ceshi.com.ceshi;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import android.view.WindowInsets;
import android.widget.FrameLayout;

import com.antuan.utils.atplayer.VideoPlayerIJK;
import com.antuan.utils.atplayer.VideoPlayerListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import ceshi.com.ceshi.adapter.TikTokAdapter;
import ceshi.com.ceshi.bean.VideoBean;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;
import tv.danmaku.ijk.media.player.IjkTimedText;

/**
 * 模仿抖音短视频
 * Created by xinyu on 2018/1/6.
 */

public class TikTokActivity extends FragmentActivity {

    private static final String TAG = "TikTokActivity";
    private VideoPlayerIJK mIjkVideoView;
    private int mCurrentPosition;
    private RecyclerView mRecyclerView;
    private List<VideoBean> mVideoList = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
        setContentView(R.layout.activity_tiktok);

        setStatusBarTransparent();

        mIjkVideoView = new VideoPlayerIJK(this);


        mRecyclerView = findViewById(R.id.rv);

        VideoBean videoBean = new VideoBean("",
                "http://cdn.file6.goodid.com/20088/2019/01/27/48e42d089b0bba3465ae923f6bdb2521.jpg",
                "http://cdn.file6.goodid.com/20088/2019/01/27/a257a6f8b89cfbea35c7d563a0e0ee2c.mp4"
        );

        mVideoList.add(videoBean);

        videoBean = new VideoBean("",
                "http://cdn.file6.goodid.com/20088/2019/01/28/642ede2a95726a928025e152fd43a21d.png",
                "http://cdn.file6.goodid.com/20088/2019/01/28/28ab3b018a8436c7c28b138bb5a74333.mp4"
        );

        mVideoList.add(videoBean);

        videoBean = new VideoBean("",
                "http://cdn.file6.goodid.com/20088/2019/01/28/31c9c64ec8ce3f492db15174f834e2b9.png",
                "http://cdn.file3.goodid.com/20088/2019/01/11/74b35184603e9c239ae09fa1da9120a9.mp4"
        );

        mVideoList.add(videoBean);



        TikTokAdapter tikTokAdapter = new TikTokAdapter(mVideoList, this);
        ViewPagerLayoutManager layoutManager = new ViewPagerLayoutManager(this, OrientationHelper.VERTICAL);

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(tikTokAdapter);
        layoutManager.setOnViewPagerListener(new OnViewPagerListener() {
            @Override
            public void onInitComplete() {
                //自动播放第一条
                startPlay(0);
            }

            @Override
            public void onPageRelease(boolean isNext, int position) {
                if (mCurrentPosition == position) {
                    mIjkVideoView.release();
                }
            }

            @Override
            public void onPageSelected(int position, boolean isBottom) {
                if (mCurrentPosition == position) return;
                startPlay(position);
                mCurrentPosition = position;
            }
        });
    }

    /**
     * 得到json文件中的内容
     *
     * @param context
     * @param fileName
     * @return
     */
    public  String getFileStr(Context context, String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        //获得assets资源管理器
        AssetManager assetManager = context.getAssets();
        //使用IO流读取json文件内容
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName), "utf-8"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public void init() {
        try {
            IjkMediaPlayer.loadLibrariesOnce(null);
            IjkMediaPlayer.native_profileBegin("libijkplayer.so");
        } catch (Exception e) {
            this.finish();
        }
    }

    private void startPlay(int position) {
        View itemView = mRecyclerView.getChildAt(0);
        FrameLayout frameLayout = itemView.findViewById(R.id.container);
//        Glide.with(this)
//                .load(mVideoList.get(position).getThumb())
//                .placeholder(android.R.color.white)
//                .into(mTikTokController.getThumb());
        ViewParent parent = mIjkVideoView.getParent();
        if (parent != null && parent instanceof FrameLayout) {
            ((FrameLayout) parent).removeView(mIjkVideoView);
        }
        frameLayout.addView(mIjkVideoView);
        mIjkVideoView.setVideoPath(mVideoList.get(position).getUrl());

        mIjkVideoView.setListener(new VideoPlayerListener() {
            @Override
            public void onTimedText(IMediaPlayer mp, IjkTimedText text) {
                Log.e("ijkPlayer","onTimedText"+text);
            }

            @Override
            public void onBufferingUpdate(IMediaPlayer mp, int percent) {
                Log.e("ijkPlayer","onBufferingUpdate"+percent);
            }

            @Override
            public void onCompletion(final IMediaPlayer mp) {
                Log.e("ijkPlayer","onCompletion");
//                ijkPlayer.setVideoPath(url);
            }

            @Override
            public boolean onError(IMediaPlayer mp, int what, int extra) {
                Log.e("ijkPlayer","onError");
                return false;
            }

            @Override
            public boolean onInfo(IMediaPlayer mp, int what, int extra) {
                Log.e("ijkPlayer","onInfo:"+what+" "+extra);
                return false;
            }

            @Override
            public void onPrepared(IMediaPlayer mp) {
                // 视频准备好播放了，但是他不会自动播放，需要手动让他开始。
                Log.e("ijkPlayer","onPrepared");
                mp.start();
            }

            @Override
            public void onSeekComplete(IMediaPlayer mp) {
                Log.e("ijkPlayer","onSeekComplete");
            }

            @Override
            public void onVideoSizeChanged(IMediaPlayer mp, int width, int height, int sar_num, int sar_den) {
                //获取到视频的宽和高
                Log.e("ijkPlayer","onVideoSizeChanged:"+width+" "+height);
            }
        });


    }

    /**
     * 把状态栏设成透明
     */
    private void setStatusBarTransparent() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View decorView = TikTokActivity.this.getWindow().getDecorView();
            decorView.setOnApplyWindowInsetsListener((v, insets) -> {
                WindowInsets defaultInsets = v.onApplyWindowInsets(insets);
                return defaultInsets.replaceSystemWindowInsets(
                        defaultInsets.getSystemWindowInsetLeft(),
                        0,
                        defaultInsets.getSystemWindowInsetRight(),
                        defaultInsets.getSystemWindowInsetBottom());
            });
            ViewCompat.requestApplyInsets(decorView);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, android.R.color.transparent));
        }

//        preloader = new M3uPreloader();
//        preloader.setCachePath(getExternalCacheDir().getPath());
//        preloader.setCacheCount(1);
//        preloader.setReloadCount(5);
//        preloader.start("http://cdn.file0.antuan.com/2018/kjs/index.m3u8");
    }

    @Override
    protected void onPause() {
        super.onPause();
        mIjkVideoView.pause();
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        mIjkVideoView.resume();
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mIjkVideoView.release();
    }
}
