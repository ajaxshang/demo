package com.ajax.demo.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;

import com.ajax.demo.Utils.MeasureUtil;

/**
 * Created by Administrator on 2015/8/31.
 */
public class ClockView extends View {
    private Paint mPaint, paint;
    private PorterDuffXfermode porterDuffXfermode;
    private int x, y;
    private int screenW, screenH;

    public ClockView(Context context) {
        super(context);
    }

    public ClockView(Context context, AttributeSet attrs) {
        super(context, attrs);
        porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
        initPaint();
        initRec(context);
    }

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(20);

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);
        paint.setColor(Color.RED);
    }

    private void initRec(Context context) {

        int[] screenSize = MeasureUtil.getScreenSize((Activity) context);
        screenW = screenSize[0];
        screenH = screenSize[1];

        x = screenW / 2;
        y = screenH / 2;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();

        canvas.drawCircle(x, y, 260, mPaint);
        canvas.translate(x, y);
        canvas.rotate(60);

        canvas.drawRect(-10, -200, 10, 0, paint);
        canvas.drawRect(0, -10, 130, 10, paint);

        canvas.drawCircle(0, 0, 10, paint);

        canvas.restore();
//        canvas.restoreToCount(sc);
    }
}
