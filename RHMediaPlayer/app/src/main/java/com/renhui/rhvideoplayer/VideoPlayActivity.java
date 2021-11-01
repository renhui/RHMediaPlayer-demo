package com.renhui.rhvideoplayer;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.renhui.component.VodVideoPlayView;

public class VideoPlayActivity extends AppCompatActivity {

    VodVideoPlayView liveVideoView;    // 播放器控件

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);// 隐藏标题栏
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);// 隐藏状态栏
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play_activity);

        liveVideoView = findViewById(R.id.video_play_view);
        // 对播放器添加生命周期监听
        getLifecycle().addObserver(liveVideoView);

        //liveVideoView.setVideoResourceAndPlay("http://ivi.bupt.edu.cn/hls/cctv6hd.m3u8");
        liveVideoView.setVideoResourceAndPlay("http://vfx.mtime.cn/Video/2019/03/18/mp4/190318214226685784.mp4");
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        liveVideoView.release();
        getLifecycle().removeObserver(liveVideoView);
    }

}
