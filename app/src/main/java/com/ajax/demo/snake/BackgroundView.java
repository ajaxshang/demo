package com.ajax.demo.snake;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.ajax.demo.R;

import java.util.Arrays;

/**
 * Created by Administrator on 2015/8/28.
 */
public class BackgroundView extends View {

    private int[] mColors = new int[4];
    private final short[] mIndices = {0, 1, 2, 0, 3, 4, 0, 1, 4};
    private float[] mVertexPoints = null;

    public BackgroundView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFocusable(true);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.BackgroundView);
        mColors[0] = a.getColor(R.styleable.BackgroundView_colorSegmentOne, Color.RED);
        mColors[1] = a.getColor(R.styleable.BackgroundView_colorSegmentTwo, Color.YELLOW);
        mColors[2] = a.getColor(R.styleable.BackgroundView_colorSegmentThree, Color.BLUE);
        mColors[3] = a.getColor(R.styleable.BackgroundView_colorSegmentFour, Color.GREEN);
        a.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        assert (mVertexPoints != null);
        int[] mFillColors = new int[mVertexPoints.length];
        for (int triangle = 0; triangle < mColors.length; triangle++) {
            Log.d("background", "building background");
            Arrays.fill(mFillColors, mColors[triangle]);
            canvas.drawVertices(Canvas.VertexMode.TRIANGLES,
                    mVertexPoints.length, mVertexPoints,
                    0, null, 0, // No Textures
                    mFillColors, 0, mIndices,
                    triangle * 2, 3, // Use 3 vertices via Index Array with offset 2
                    new Paint());
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mVertexPoints = new float[]{
                w / 2, h / 2,
                0, 0,
                w, 0,
                w, h,
                0, h
        };
    }
}
