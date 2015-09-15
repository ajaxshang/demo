package com.ajax.demo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2015/8/29.
 */
public class LayerView extends View {

    private Paint mPaint;
    private int mViewWidth, mViewHeight;

    public LayerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
    }

    public LayerView(Context context) {
        super(context);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mViewWidth = w;
        mViewHeight = h;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setColor(Color.RED);
        canvas.drawRect(mViewWidth / 2f - 200, mViewHeight / 2f - 200,
                mViewWidth / 2f + 200, mViewHeight / 2f + 200, mPaint);
        canvas.save();
        mPaint.setColor(Color.BLUE);
        canvas.drawRect(mViewWidth / 2F - 100, mViewHeight / 2F - 100,
                mViewWidth / 2F + 100, mViewHeight / 2F + 100, mPaint);
        canvas.restore();
    }
}
