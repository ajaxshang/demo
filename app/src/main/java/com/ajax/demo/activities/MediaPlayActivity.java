package com.ajax.demo.activities;

import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.ajax.demo.R;

import java.io.IOException;

/**
 * Created by Administrator on 2015/8/24.
 */
public class MediaPlayActivity extends BaseActivity implements SurfaceHolder.Callback {
    private MediaPlayer mediaPlayer;
    static final String TAG = "MediaPlayActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //完全隐藏虚拟按键
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        window.setAttributes(params);

        setContentView(R.layout.activity_media);


        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        mediaPlayer = new MediaPlayer();

        final SurfaceView surfaceView = (SurfaceView) findViewById(R.id.media_sur);
        //配置SurfaceView
        surfaceView.setKeepScreenOn(true);
        //配置SurfaceHolder并注册回调
        SurfaceHolder holder = surfaceView.getHolder();
        holder.addCallback(this);
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        holder.setFixedSize(400, 300);

        System.out.println(Environment.getExternalStorageDirectory());

        //连接播放按钮
        Button play_btn = (Button) findViewById(R.id.play_btn);
        play_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
            }
        });
        Button pause_btn = (Button) findViewById(R.id.pause_btn);
        pause_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.pause();
            }
        });
        Button skip_btn = (Button) findViewById(R.id.skip_btn);
        skip_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.seekTo(mediaPlayer.getDuration() / 2);
            }
        });


    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        try {
            mediaPlayer.setDisplay(holder);
            mediaPlayer.setDataSource("/storage/emulated/0/DCIM/Camera/11.mp4");//准备数据源
            mediaPlayer.prepare();

        } catch (IllegalArgumentException e) {
            Log.e(TAG, "IllegalArgumentException", e);
        } catch (IllegalStateException e) {
            Log.e(TAG, "IllegalStateException", e);
        } catch (SecurityException e) {
            Log.e(TAG, "SecurityException", e);
        } catch (IOException e) {
            Log.e(TAG, "IOException", e);
        }


    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mediaPlayer.release();
    }
}
