package com.ajax.demo.snake;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.ajax.demo.R;

/**
 * Created by Administrator on 2015/8/28.
 */
public class TileView extends View {

    public static int mTileSize;

    public static int mXTileCount;
    public static int mYTileCount;

    private static int mXOffset;
    private static int mYOffset;

    private final Paint mPaint = new Paint();

    private Bitmap[] mTileArray;
    private int[][] mTileGrid;

    public TileView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TileView);
        mTileSize = a.getDimensionPixelSize(R.styleable.TileView_tileSize, 12);
        a.recycle();
    }

    public TileView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TileView);
        mTileSize = a.getDimensionPixelSize(R.styleable.TileView_tileSize, 12);
        a.recycle();
    }

    public void clearTiles() {
        for (int x = 0; x < mXTileCount; x++) {
            for (int y = 0; y < mYTileCount; y++) {
                setTile(0, x, y);
            }
        }
    }

    public void loadTile(int key, Drawable tile) {
        Bitmap bitmap = Bitmap.createBitmap(mTileSize, mTileSize,
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        tile.setBounds(0, 0, mTileSize, mTileSize);
        tile.draw(canvas);

        mTileArray[key] = bitmap;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int x = 0; x < mXTileCount; x += 1) {
            for (int y = 0; y < mYTileCount; y += 1) {
                if (mTileGrid[x][y] > 0) {
                    canvas.drawBitmap(mTileArray[mTileGrid[x][y]],
                            mXOffset + x * mTileSize,
                            mYOffset + y * mTileSize, mPaint);
                }
            }
        }
    }

    public void resetTiles(int tilecount) {
        mTileArray = new Bitmap[tilecount];
    }

    public void setTile(int tileindex, int x, int y) {
        Log.d("test", "do setTile ing ");
        mTileGrid[x][y] = tileindex;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mXTileCount = (int) Math.floor(w / mTileSize);
        mYTileCount = (int) Math.floor(h / mTileSize);

        mXOffset = ((w - (mTileSize * mXTileCount)) / 2);
        mYOffset = ((h - (mTileSize * mYTileCount)) / 2);

        mTileGrid = new int[mXTileCount][mYTileCount];
        clearTiles();

    }

    public TileView(Context context) {
        super(context);
    }
}
