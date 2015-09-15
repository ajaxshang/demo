package com.ajax.demo.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;

import com.ajax.demo.R;
import com.ajax.demo.Utils.MeasureUtil;

/**
 * Created by Administrator on 2015/8/31.
 */
public class DisInView extends View {

    private Paint mPaint;
    private Bitmap bitmapDis, bitmapSrc;
    private PorterDuffXfermode porterDuffXfermode;//图形混合模式

    private int x, y;
    private int screenW, screenH;


    public DisInView(Context context) {
        super(context);
    }

    public DisInView(Context context, AttributeSet attrs) {
        super(context, attrs);

        porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
        initPaint();
        initRes(context);
    }

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    private void initRes(Context context) {
        bitmapDis = BitmapFactory.decodeResource(context.getResources(), R.drawable.a3);
        bitmapSrc = BitmapFactory.decodeResource(context.getResources(), R.drawable.a3_mask);

        int[] screenSize = MeasureUtil.getScreenSize((Activity) context);

        screenW = screenSize[0];
        screenH = screenSize[1];

        x = screenW / 2 - bitmapDis.getWidth() / 2;
        y = screenH / 2 - bitmapDis.getHeight() / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);

        int sc = canvas.saveLayer(0, 0, screenW, screenH, null, Canvas.ALL_SAVE_FLAG);

        canvas.drawBitmap(bitmapDis, x, y, mPaint);

        mPaint.setXfermode(porterDuffXfermode);

        canvas.drawBitmap(bitmapSrc, x, y, mPaint);

        mPaint.setXfermode(null);
        canvas.restoreToCount(sc);
    }
}
