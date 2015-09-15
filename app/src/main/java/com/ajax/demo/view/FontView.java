package com.ajax.demo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Administrator on 2015/9/6.
 */
public class FontView extends View implements Runnable {
    private String TAG = "FONT";
    private static final String TEXT = "吾身为剑所天成。血若钢铁铮铮，心似琉璃易碎。横行沙场无数，未尝败绩。然虽不曾落败。却也不被理解。其常独立于剑丘之上，沉醉于胜利之中。故其之一生，没有意义。其身，定为无限之剑所天成";
    private TextPaint mPaint;
    private Paint.FontMetrics mFont;
    private StaticLayout mStaticLayout;
    private String[] strings = new String[TEXT.length()];
    private String str = "";

    public FontView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
        for (int i = 0; i < TEXT.length(); i++) {
            strings[i] = new String().valueOf(TEXT.charAt(i));
            Log.d(TAG, strings[i]);
        }
    }

    private void initPaint() {
        mPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setTextSize(50);
        mPaint.setColor(Color.BLACK);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mStaticLayout = new StaticLayout(str, mPaint, canvas.getWidth(), Layout.Alignment.ALIGN_NORMAL, 1.0F, 0.0F, false);
        mStaticLayout.draw(canvas);
//        canvas.drawText(TEXT, 0, Math.abs(mFont.top), mPaint);
        canvas.restore();
    }


    @Override
    public void run() {
        for (int i = 0; i < strings.length; i++) {
            try {
                if (strings[i].equals("，")
                        | strings[i].equals("。")
                        | strings[i].equals(",")
                        | strings[i].equals(".")) {
                    str = str + strings[i] + "\n";
                    postInvalidate();
                } else {
                    str = str + strings[i];
                    postInvalidate();
                }
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
//            }
            }
        }
    }
}
