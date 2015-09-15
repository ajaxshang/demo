package com.ajax.demo.tetris;

import java.util.Random;

/**
 * Created by Administrator on 2015/8/26.
 */
public class Game {
    public static final byte ISWALL = 1;
    public static final int NROWS = 20;
    public static final int NCOLS = 12;
    public byte[][] m_Groundback = null;

    public int m_nX = 0;
    public int m_nY = 0;
    private Random m_Random = new Random();

    private byte[][][] m_Tetris = null;
    public byte[][] m_CurrBlock = new byte[4][4];
    private byte[][] m_NextBlock = new byte[4][4];

    public Game() {
        m_Groundback = new byte[NROWS][NCOLS];
        initGroundback();
        initTetris();
    }

    public void initGroundback() {
        for (int i = 0; i < NROWS; i++) {
            for (int j = 0; j < NCOLS; j++) {
                if (i == NROWS - 1 || j == 0 || j == NCOLS - 1) {
                    m_Groundback[i][j] = ISWALL;
                }
            }
        }

    }

    public void initTetris() {
        m_Tetris = new byte[][][]{
                //1
                {
                        {1, 1, 1, 1},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0}
                },
                //2
                {
                        {1, 0, 0, 0},
                        {1, 0, 0, 0},
                        {1, 0, 0, 0},
                        {1, 0, 0, 0}
                },
                //3
                {
                        {1, 0, 0, 0},
                        {1, 1, 0, 0},
                        {0, 1, 0, 0},
                        {0, 0, 0, 0}
                },
                //4
                {
                        {0, 1, 0, 0},
                        {1, 1, 0, 0},
                        {1, 0, 0, 0},
                        {0, 0, 0, 0}
                },
                //5
                {
                        {1, 1, 0, 0},
                        {0, 1, 1, 0},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0}
                },
                //6
                {
                        {0, 1, 1, 0},
                        {1, 1, 0, 0},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0}
                },
                //7
                {
                        {0, 1, 0, 0},
                        {1, 1, 1, 0},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0}
                },
                //8
                {
                        {1, 1, 1, 0},
                        {0, 1, 0, 0},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0}
                },
                //9
                {
                        {1, 0, 0, 0},
                        {1, 1, 0, 0},
                        {1, 0, 0, 0},
                        {0, 0, 0, 0}
                },
                //10
                {
                        {0, 1, 0, 0},
                        {1, 1, 0, 0},
                        {0, 1, 0, 0},
                        {0, 0, 0, 0}
                },
                //11
                {
                        {1, 1, 0, 0},
                        {0, 1, 0, 0},
                        {0, 1, 0, 0},
                        {0, 0, 0, 0}
                },
                //12
                {
                        {1, 1, 0, 0},
                        {1, 0, 0, 0},
                        {1, 0, 0, 0},
                        {0, 0, 0, 0}
                },
                //13
                {
                        {1, 1, 0, 0},
                        {1, 1, 1, 0},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0}
                },
                //14
                {
                        {1, 1, 1, 0},
                        {0, 0, 1, 0},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0}
                },
                //15
                {
                        {1, 1, 0, 0},
                        {1, 1, 0, 0},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0}
                },
        };
    }

    public byte[][] getGroundback() {
        return m_Groundback;
    }

    public void startGame() {
        m_nX = NCOLS / 2 - 2;
        m_nY = 0;

        m_CurrBlock = RandomBlock();
        m_NextBlock = RandomBlock();

    }

    public void stopGame() {

    }

    public void moveLeft() {
        if (IsCanMove(m_nX - 1, m_nY))
            m_nX--;
    }

    public void moveRight() {
        if (IsCanMove(m_nX + 1, m_nY))
            m_nX++;
    }

    public byte[][] getNextBlock() {
        int nLen = m_Tetris.length;
        int nIndex = (m_Random.nextInt() >>> 1) % nLen;
        return m_Tetris[(nIndex + 2) % 15];
    }

    public void moveChange() {
        byte[][] oldBlock = m_CurrBlock;
        m_CurrBlock = getNextBlock();
        if (!IsCanMove(m_nX, m_nY))
            m_CurrBlock = oldBlock;
    }

    public void moveDown() {
        if (IsCanMove(m_nX, m_nY + 1))
            m_nY++;
        else
            Fixbircks();

    }

    public byte[][] RandomBlock() {
        int nLen = m_Tetris.length;
        int nIndex = (m_Random.nextInt() >>> 1) % nLen;
        return m_Tetris[nIndex];
    }

    private void CreateBircks() {
        m_nX = NCOLS / 2 - 2;
        m_nY = 0;

        m_CurrBlock = m_NextBlock;
        m_NextBlock = RandomBlock();

    }

    public void MoveFix() {
        while (IsCanMove(m_nX, m_nY + 1)) {
            m_nY++;
        }
        Fixbircks();

    }

    private boolean IsCanMove(int x, int y) {
        byte[][] data = m_CurrBlock;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (data[i][j] == ISWALL
                        && m_Groundback[y + i][x + j] == ISWALL)
                    return false;
            }
        }
        return true;
    }

    private void Fixbircks() {
        byte[][] data = m_CurrBlock;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (data[i][j] == ISWALL)
                    m_Groundback[m_nY + i][m_nX + j] = ISWALL;
            }
        }
        int nRows = ReleaseRows();
        CreateBircks();

    }

    private int ReleaseRows() {
        int nReleaseRows = 0;
        for (int nRow = NROWS - 2; nRow > 0; nRow--) {
            if (IsCanRelease(nRow)) {
                nReleaseRows++;
                MoveRows(nRow);
                nRow++;
            }
        }
        return nReleaseRows;
    }

    private boolean IsCanRelease(int nRow) {
        for (int nCol = 1; nCol < NCOLS - 1; nCol++) {
            if (m_Groundback[nRow][nCol] == 0)
                return false;
        }
        return true;
    }

    private void MoveRows(int nRow) {
        for (int i = nRow; i > 0; i--) {
            for (int j = 1; j < NCOLS; j++) {
                m_Groundback[i][j] = m_Groundback[i - 1][j];
            }
        }
    }
}
