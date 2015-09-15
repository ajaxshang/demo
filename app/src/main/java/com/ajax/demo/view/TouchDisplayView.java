package com.ajax.demo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2015/8/25.
 */
public class TouchDisplayView extends View {

    private SparseArray<TouchHistory> mTouches;

    private boolean mHasTouch = false;

    static final class TouchHistory {

        public static final int HISTORY_COUNT = 20;

        public float x;
        public float y;
        public float pressure = 0f;
        public String label = null;

        public int historyIndex = 0;
        public int historyCount = 0;

        public PointF[] history = new PointF[HISTORY_COUNT];

        private static final int MAX_POOL_SIZE = 10;
        private static final Pools.SimplePool<TouchHistory> sPool =
                new Pools.SimplePool<>(MAX_POOL_SIZE);

        public static TouchHistory obtain(float x, float y, float pressure) {
            TouchHistory data = sPool.acquire();
            if (data == null) {
                data = new TouchHistory();
            }
            data.setTouch(x, y, pressure);
            return data;
        }

        public TouchHistory() {
            for (int i = 0; i < HISTORY_COUNT; i++) {
                history[i] = new PointF();
            }
        }

        public void setTouch(float x, float y, float pressure) {
            this.x = x;
            this.y = y;
            this.pressure = pressure;
        }

        public void recycle() {
            this.historyIndex = 0;
            this.historyCount = 0;
            sPool.release(this);
        }

        public void addHistory(float x, float y) {
            PointF p = history[historyIndex];
            p.x = x;
            p.y = y;

            historyIndex = (historyIndex + 1) % history.length;
            if (historyCount < HISTORY_COUNT) {
                historyCount++;
            }
        }
    }


    public TouchDisplayView(Context context) {
        super(context);
    }

    public TouchDisplayView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mTouches = new SparseArray<TouchHistory>(10);
        initialisePaint();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        final int action = event.getAction();
        switch (action & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN: {
                int id = event.getPointerId(0);
                TouchHistory data = TouchHistory.obtain(event.getX(0), event.getY(0),
                        event.getPressure(0));
                data.label = "id: " + 0;
                mTouches.put(id, data);
                mHasTouch = true;
                break;
            }
            case MotionEvent.ACTION_POINTER_DOWN: {
                int index = event.getActionIndex();
                int id = event.getPointerId(index);

                TouchHistory data = TouchHistory.obtain(event.getX(index), event.getY(index),
                        event.getPressure(index));
                data.label = "id: " + id;
                mTouches.put(id, data);
                break;
            }
            case MotionEvent.ACTION_UP: {
                int id = event.getPointerId(0);
                TouchHistory data = mTouches.get(id);
                mTouches.remove(id);
                data.recycle();
                mHasTouch = false;
                break;
            }
            case MotionEvent.ACTION_POINTER_UP: {
                int index = event.getActionIndex();
                int id = event.getPointerId(index);

                TouchHistory data = mTouches.get(id);
                mTouches.remove(id);
                data.recycle();
                break;
            }

            case MotionEvent.ACTION_MOVE: {
                for (int index = 0; index < event.getPointerCount(); index++) {
                    int id = event.getPointerId(index);
                    TouchHistory data = mTouches.get(id);
                    data.addHistory(data.x, data.y);
                    data.setTouch(event.getX(index), event.getY(index), event.getPressure());
                }
                break;
            }
        }
        this.postInvalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mHasTouch) {
            canvas.drawColor(BACKGROUND_ACTIVE);
        } else {
            canvas.drawRect(mBorderWidth, mBorderWidth, getWidth() - mBorderWidth, getHeight() - mBorderWidth, mBorderPaint);
        }

        for (int i = 0; i < mTouches.size(); i++) {
            int id = mTouches.keyAt(i);
            TouchHistory data = mTouches.valueAt(i);
            drawCircle(canvas, id, data);
        }
    }


    private static final float CIRCLE_RADIUS_DP = 75f;
    private static final float CIRCLE_HISTORICAL_RADIUS_DP = 7F;

    private float mCircleRadius;
    private float mCircleHistoricalRadius;

    private Paint mCirclePaint = new Paint();
    private Paint mTextPaint = new Paint();

    private static final int BACKGROUND_ACTIVE = Color.WHITE;

    private static final float INACTIVE_BORDER_DP = 15F;

    private static final int INACTIVE_BORDER_COLOR = 0xFFffd060;

    private Paint mBorderPaint = new Paint();
    private float mBorderWidth;

    public final int[] COLORS = {
            0xFF33B5E5, 0xFFAA66CC, 0xFF99CC00, 0xFFFFBB33, 0xFFFF4444,
            0xFF0099CC, 0xFF9933CC, 0xFF669900, 0xFFFF8800, 0xFFCC0000
    };

    private void initialisePaint() {
        float density = getResources().getDisplayMetrics().density;
        mCircleRadius = CIRCLE_RADIUS_DP * density;
        mCircleHistoricalRadius = CIRCLE_HISTORICAL_RADIUS_DP * density;

        mTextPaint.setTextSize(27f);
        mTextPaint.setColor(Color.BLACK);

        mBorderWidth = INACTIVE_BORDER_DP * density;
        mBorderPaint.setStrokeWidth(mBorderWidth);
        mBorderPaint.setColor(INACTIVE_BORDER_COLOR);
        mBorderPaint.setStyle(Paint.Style.STROKE);
    }

    protected void drawCircle(Canvas canvas, int id, TouchHistory data) {
        int color = COLORS[id % COLORS.length];
        float pressure = Math.min(data.pressure, 1f);
        float radius = pressure * mCircleRadius;

        canvas.drawCircle(data.x, (data.y) - (radius / 2f), radius, mCirclePaint);
        mCirclePaint.setAlpha(125);
        for (int i = 0; i < data.history.length && i < data.historyCount; i++) {
            PointF p = data.history[i];
            canvas.drawCircle(p.x, p.y, mCircleHistoricalRadius, mCirclePaint);
        }
        canvas.drawText(data.label, data.x + radius, data.y - radius, mTextPaint);
    }
}
