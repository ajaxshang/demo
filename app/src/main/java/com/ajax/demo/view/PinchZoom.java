package com.ajax.demo.view;

import android.annotation.SuppressLint;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Administrator on 2015/8/31.
 */
@SuppressLint({"NewApi", "FloatMath"})
public class PinchZoom implements View.OnTouchListener {

    static final int NONE = 0;
    int mode = NONE;
    static final int DRAG = 1;
    static final int ZOOM = 2;
    Matrix matrix = new Matrix();
    Matrix saveMatrix = new Matrix();
    PointF start = new PointF();
    PointF mid = new PointF();
    float oldDist = 1f;
    int r = 0;
    private float y = 0;


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        ImageView view = (ImageView) v;

        return false;
    }

    private void dempEvent(MotionEvent event) {
    }

//    private float spacing(MotionEvent event) {
//        float x = event.getX(0) - event.getX(1);
//        float y = event.getY(0) - event.getY(1);
//        return FloatMat;
//    }


}
