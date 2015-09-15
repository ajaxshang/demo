package com.ajax.demo.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.ajax.demo.Utils.MeasureUtil;

/**
 * Created by Administrator on 2015/8/29.
 */
public class CustomView extends View implements Runnable {


    private Paint mPaint;
    private Context mContext;

    private int radiu;

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initPaint();
    }

    private void initPaint() {
        // 实例化画笔并打开抗锯齿
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        /**
         * 设置画笔样式为描边，圆环嘛……当然不能填充不然就么意思了
         *
         * 画笔样式分三种：
         * 1.Paint.Style.STROKE：描边
         * 2.Paint.Style.FILL_AND_STROKE：描边并填充
         * 3.Paint.Style.FILL：填充
         */
        mPaint.setStyle(Paint.Style.STROKE);
        // 设置画笔颜色为浅灰色
        mPaint.setColor(Color.LTGRAY);
        /**
         * 设置描边的粗细，单位：像素px
         * 注意：当setStrokeWidth(0)的时候描边宽度并不为0而是只占一个像素
         */
        mPaint.setStrokeWidth(10);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(MeasureUtil.getScreenSize((Activity) mContext)[0] / 2,
                MeasureUtil.getScreenSize((Activity) mContext)[1] / 2, radiu, mPaint);
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (radiu <= 200) {
                    radiu += 10;
                    postInvalidate();
                } else radiu = 0;
                Thread.sleep(40);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
