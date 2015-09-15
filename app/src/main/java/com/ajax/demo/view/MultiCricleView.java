package com.ajax.demo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2015/8/31.
 */
public class MultiCricleView extends View {

    private static final float
            STROKE_WIDTH = 1F / 256F,
            SPACE = 1F / 64F,
            LINE_LENGTH = 3F / 32F,
            CRICLE_LARGER_RADIU = 3F / 32F,
            CRICLE_SAMLL_RADIU = 5F / 64F,
            ARC_RADIU = 1F / 8F,
            ARC_TEXT_RADIU = 5F / 32F;

    private Paint strokePaint, textPaint, arcPaint;

    private int size;

    private float strokeWidth;
    private float ccX, ccY;
    private float largeCricleRadiu, smallCricleRadiu;
    private float lineLength;
    private float space;
    private float textOffsetY;


    private enum Type {
        LARGER, SMALL
    }

    public MultiCricleView(Context context) {
        super(context);

    }

    public MultiCricleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint(context);
    }

    private void initPaint(Context context) {

        strokePaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        strokePaint.setStyle(Paint.Style.STROKE);
        strokePaint.setColor(Color.WHITE);
        strokePaint.setStrokeCap(Paint.Cap.ROUND);

        textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(30);
        textPaint.setTextAlign(Paint.Align.CENTER);

        arcPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        arcPaint.setStyle(Paint.Style.STROKE);
        arcPaint.setColor(Color.WHITE);
        arcPaint.setStrokeCap(Paint.Cap.ROUND);

        textOffsetY = (textPaint.descent() + textPaint.ascent()) / 2;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        size = w;
        calculation();
    }

    private void calculation() {
        strokeWidth = STROKE_WIDTH * size;
        largeCricleRadiu = size * CRICLE_LARGER_RADIU;
        smallCricleRadiu = size * CRICLE_SAMLL_RADIU;
        lineLength = size * LINE_LENGTH;
        space = size * SPACE;
        ccX = size / 2;
        ccY = size / 2 + size * CRICLE_LARGER_RADIU;
        setPara();
    }

    private void setPara() {
        strokePaint.setStrokeWidth(strokeWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(0xfff29b76);
        canvas.drawCircle(ccX, ccY, largeCricleRadiu, strokePaint);

        canvas.drawText("AigeStudio", ccX, ccY - textOffsetY, textPaint);
        drawTopLeft(canvas);
        drawTopRight(canvas);
        drawBottom(canvas);
        drawBottomLeft(canvas);
        drawBottomRigth(canvas);
    }

    private void drawTopLeft(Canvas canvas) {
        canvas.save();

        canvas.translate(ccX, ccY);
        canvas.rotate(-30);
        canvas.drawLine(0, -largeCricleRadiu, 0, -largeCricleRadiu * 2, strokePaint);
        canvas.drawCircle(0, -lineLength * 3, largeCricleRadiu, strokePaint);
//        canvas.drawText("Apple", 0, -lineLength * 3 - textOffsetY, textPaint);


        canvas.drawLine(0, -largeCricleRadiu * 4, 0, -largeCricleRadiu * 5, strokePaint);
        canvas.drawCircle(0, -lineLength * 6, largeCricleRadiu, strokePaint);
//        canvas.drawText("Orange", 0, -lineLength * 6 - textOffsetY, textPaint);

        canvas.save();
        canvas.translate(0, -lineLength * 3 - textOffsetY);
        canvas.rotate(30);
        canvas.drawText("Apple", 0, 0, textPaint);
        canvas.restore();

        canvas.save();
        canvas.translate(0, -lineLength * 6 - textOffsetY);
        canvas.rotate(30);
        canvas.drawText("Orange", 0, 0, textPaint);


        canvas.restore();
        canvas.restore();
    }

    private void drawTopRight(Canvas canvas) {
        float cricleY = -lineLength * 3;
        canvas.save();
        canvas.translate(ccX, ccY);
        canvas.rotate(30);

        canvas.drawLine(0, -largeCricleRadiu, 0, -lineLength * 2, strokePaint);
        canvas.drawCircle(0, cricleY, largeCricleRadiu, strokePaint);

        canvas.save();
        canvas.translate(0, cricleY - textOffsetY);
        canvas.rotate(-30);
//        canvas.drawText("Toppical", 0, cricleY - textOffsetY, textPaint);
        canvas.drawText("Toppical", 0, 0, textPaint);
        canvas.restore();

        drawTopRightArc(canvas, cricleY);

        canvas.restore();
    }

    private void drawTopRightArc(Canvas canvas, float cricleY) {
        canvas.save();

        canvas.translate(0, cricleY);
        canvas.rotate(-30);

        float arcRadiu = size * ARC_RADIU;
        RectF oval = new RectF(-arcRadiu, -arcRadiu, arcRadiu, arcRadiu);
        arcPaint.setStyle(Paint.Style.FILL);
        arcPaint.setColor(0x55ec6941);
        canvas.drawArc(oval, -22.5f, -135, true, arcPaint);

        arcPaint.setStyle(Paint.Style.STROKE);
        arcPaint.setColor(Color.WHITE);
        canvas.drawArc(oval, -22.5f, -135, false, arcPaint);

        float arcTextRadiu = size * ARC_TEXT_RADIU;

        canvas.save();
        canvas.rotate(-135F / 2F);
        for (float i = 0; i < 5 * 33.75F; i += 33.75F) {
            canvas.save();
            canvas.rotate(i);
            canvas.drawText("Aige", 0, -arcTextRadiu, textPaint);
            canvas.restore();
        }


        canvas.restore();
        canvas.restore();
    }

    private void drawBottomLeft(Canvas canvas) {
        float lineYS = -largeCricleRadiu - space,
                lineYE = -largeCricleRadiu * 2 - space,
                cricleY = -lineLength * 2 - smallCricleRadiu - space * 2;
        canvas.save();
        canvas.translate(ccX, ccY);
        canvas.rotate(-100);

        canvas.drawLine(0, lineYS, 0, lineYE, strokePaint);
        canvas.drawCircle(0, cricleY, smallCricleRadiu, strokePaint);

        canvas.save();
        canvas.translate(0, cricleY - textOffsetY - space);
        canvas.rotate(100);
        canvas.drawText("Banana", 0, 0, textPaint);
        canvas.restore();

        canvas.restore();

    }

    private void drawBottom(Canvas canvas) {
        float lineYS = -largeCricleRadiu - space,
                lineYE = -lineLength * 2 - space,
                cricleY = -lineLength * 2 - smallCricleRadiu - space * 2;
        canvas.save();
        canvas.translate(ccX, ccY);
        canvas.rotate(180);
        canvas.drawLine(0, lineYS, 0, lineYE, strokePaint);
        canvas.drawCircle(0, cricleY, smallCricleRadiu, strokePaint);

        canvas.save();
        canvas.translate(0, cricleY - textOffsetY - space);
        canvas.rotate(-180);
        canvas.drawText("Cucumber", 0, 0, textPaint);
        canvas.restore();

        canvas.restore();
    }

    private void drawBottomRigth(Canvas canvas) {
        float lineYS = -largeCricleRadiu - space,
                lineYE = -largeCricleRadiu * 2 - space,
                cricleY = -lineLength * 2 - smallCricleRadiu - space * 2;

        canvas.save();

        canvas.translate(ccX, ccY);
        canvas.rotate(100);

        canvas.drawLine(0, lineYS, 0, lineYE, strokePaint);
        canvas.drawCircle(0, cricleY, smallCricleRadiu, strokePaint);

        canvas.save();
        canvas.translate(0, cricleY - textOffsetY - space);
        canvas.rotate(-100);
        canvas.drawText("Vibrators", 0, 0, textPaint);
        canvas.restore();

        canvas.restore();

    }


}
