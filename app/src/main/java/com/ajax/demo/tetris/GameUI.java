package com.ajax.demo.tetris;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by Administrator on 2015/8/26.
 */
public class GameUI extends View {
    public Game m_Game = null;
    private byte[][] m_Groundback = null;

    private Paint m_Paint = new Paint();
    private final int PADDING = 2;
    private int m_TetrisWidth = 0;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setBackgroundColor(Color.BLACK);
        drawGoundback(canvas);

        int nX = m_Game.m_nX;
        int nY = m_Game.m_nY;

        if (m_Game.m_CurrBlock == null)
            return;

        byte[][] data = m_Game.m_CurrBlock;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (data[i][j] == Game.ISWALL) {
                    int nLeft = nX * m_TetrisWidth + j * m_TetrisWidth;
                    int nTop = nY * m_TetrisWidth + j * m_TetrisWidth;
                    m_Paint.setColor(Color.WHITE);
                    canvas.drawRect(nLeft, nTop, nLeft + m_TetrisWidth,
                            nTop + m_TetrisWidth, m_Paint);

                    //内框
                    m_Paint.setColor(Color.GREEN);
                    canvas.drawRect(nLeft + PADDING, nTop + PADDING,
                            nLeft + m_TetrisWidth - PADDING, nTop + m_TetrisWidth - PADDING, m_Paint);

                }
            }
        }

    }

    private void drawGoundback(Canvas canvas) {
        m_Groundback = m_Game.getGroundback();
        for (int i = 0; i < Game.NROWS; i++) {
            for (int j = 0; j < Game.NCOLS; j++) {
                if (m_Groundback[i][j] == Game.ISWALL) {
                    m_Paint.setColor(Color.WHITE);
                    canvas.drawRect(j * m_TetrisWidth, i * m_TetrisWidth,
                            (j + 1) * m_TetrisWidth, (i + 1) * m_TetrisWidth, m_Paint);
                    m_Paint.setColor(Color.GREEN);
                    canvas.drawRect(j * m_TetrisWidth + PADDING, i * m_TetrisWidth + PADDING,
                            (j + 1) * m_TetrisWidth + PADDING, (i + 1) * m_TetrisWidth + PADDING, m_Paint);
                }
            }
        }
    }


    public GameUI(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public GameUI(Context context, AttributeSet attrs) {
        super(context, attrs);
        m_Game = new Game();

        WindowManager wmgr = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wmgr.getDefaultDisplay().getMetrics(outMetrics);
        int nTetrisWidth = outMetrics.widthPixels;
        m_TetrisWidth = nTetrisWidth / Game.NCOLS * 3 / 4;
    }

    public GameUI(Context context) {
        super(context);
    }
}
