package com.ajax.demo.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.ajax.demo.R;
import com.ajax.demo.snake.SnakeView;

/**
 * Created by Administrator on 2015/8/28.
 */
public class SnakeActivity extends BaseActivity {

    public static int MOVE_LEFT = 0;
    public static int MOVE_UP = 1;
    public static int MOVE_DOWN = 2;
    public static int MOVE_RIGHT = 3;

    private static String ICICLE_KEY = "snake-view";
    private SnakeView mSankeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snake);
        mSankeView = (SnakeView) findViewById(R.id.snake);
        mSankeView.setDependentViews((TextView) findViewById(R.id.text)
                , findViewById(R.id.arrowContainer), findViewById(R.id.background));
        if (savedInstanceState == null) {
            mSankeView.setMode(SnakeView.READY);
        } else {
            Bundle map = savedInstanceState.getBundle(ICICLE_KEY);
            if (map != null) {
                mSankeView.restoreState(map);
            } else {
                mSankeView.setMode(SnakeView.PAUSE);
            }
        }

        mSankeView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d("", "onTouch start");
                if (mSankeView.getGameState() == SnakeView.RUNNING) {
                    float x = event.getX() / v.getWidth();
                    float y = event.getY() / v.getHeight();
                    Log.d("", "onTouch x :" + x + "," + "y:" + y);
                    int direcction = 0;
                    direcction = (x > y) ? 1 : 0;
                    direcction |= (x > 1 - y) ? 2 : 0;
                    mSankeView.moveSnake(direcction);
                } else {
                    mSankeView.moveSnake(MOVE_UP);
                }
                return false;
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSankeView.setMode(SnakeView.PAUSE);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBundle(ICICLE_KEY, mSankeView.saveState());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_UP:
                mSankeView.moveSnake(MOVE_UP);
                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                mSankeView.moveSnake(MOVE_RIGHT);
                break;
            case KeyEvent.KEYCODE_DPAD_DOWN:
                mSankeView.moveSnake(MOVE_DOWN);
                break;
            case KeyEvent.KEYCODE_DPAD_LEFT:
                mSankeView.moveSnake(MOVE_LEFT);
                break;
        }
        return super.onKeyDown(keyCode, event);
    }
}
