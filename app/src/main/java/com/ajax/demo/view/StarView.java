package com.ajax.demo.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.view.View;

import com.ajax.demo.R;
import com.ajax.demo.Utils.MeasureUtil;

/**
 * Created by Administrator on 2015/9/6.
 */
public class StarView extends View {
    private Paint mPaint;
    private Context mContext;
    private Bitmap bitmap;

    private int x, y;
    private boolean isClick;

    public StarView(Context context) {
        super(context);
        mContext = context;
        initPaint();
        initRes(mContext);

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isClick) {
                    mPaint.setColorFilter(null);
                    isClick = false;
                } else {
                    mPaint.setColorFilter(new LightingColorFilter(0xFFFFFFFF, 0X00FFFF00));
                    isClick = true;
                }
                invalidate();
            }
        });
    }

//    public StarView(Context context, AttributeSet attrs) {
//        super(context, attrs);
//
//
//    }


    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    private void initRes(Context context) {
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.a2);

        x = MeasureUtil.getScreenSize((Activity) mContext)[0] / 2 - bitmap.getWidth() / 2;
        y = MeasureUtil.getScreenSize((Activity) mContext)[1] / 2 - bitmap.getHeight() / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap, x, y, mPaint);
    }
}
