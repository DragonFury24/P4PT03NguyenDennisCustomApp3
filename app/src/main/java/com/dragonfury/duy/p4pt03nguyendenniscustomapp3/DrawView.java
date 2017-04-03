package com.dragonfury.duy.p4pt03nguyendenniscustomapp3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Duy on 3/28/2017.
 */
public class DrawView extends View {
    public DrawView(Context context) {
        super(context);

    }

    private Paint nam = new Paint();
    private final int ROW = 6, COL = 7;
    private Cell[][] squares = new Cell[ROW][COL];
    private RectF board;
    private int turnCount;
    private int playerWin; //1 = Red wins, 2 = Black wins
    private boolean gameOver = false;
    private int chip0;
    private int chip1;
    private int chip2;
    private int chip3;
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        //Draw Big Board
        nam.setColor(Color.YELLOW);
        nam.setStyle(Paint.Style.FILL);
        canvas.drawRect(board, nam);

        //Draw Outline of Big board
        nam.setColor(Color.BLACK);
        nam.setStrokeWidth(10 * getWidth() / 1440);
        canvas.drawLine(board.left, board.top, board.right, board.top, nam);
        canvas.drawLine(board.left, board.top, board.left, board.bottom, nam);
        canvas.drawLine(board.right, board.top, board.right, board.bottom, nam);
        canvas.drawLine(board.left, board.bottom, board.right, board.bottom, nam);

        //Draw Individual Boxes
        for (int j = 0; j < ROW; j++) {
            for (Cell square : squares[j]) {
                square.draw(canvas, nam);
            }
        }

        //Draw turn indicator
        nam.setColor(Color.BLACK);
        nam.setTextSize(100 * getWidth() / 1440);
        nam.setStyle(Paint.Style.FILL);
        canvas.drawText("Current Turn:", 25 * getWidth() / 1440, 150 * getHeight() / 2560, nam);
        if (turnCount % 2 == 0){
            nam.setColor(Color.RED);
            canvas.drawCircle(685 * getWidth() / 1440, 125 * getHeight() / 2560, 75 * getWidth() / 1440, nam);
        }else {
            nam.setColor(Color.BLACK);
            canvas.drawCircle(685 * getWidth() / 1440, 125 * getHeight() / 2560, 75 * getWidth() / 1440, nam);
        }

        //Draw Game text
        nam.setColor(Color.RED);
        nam.setTextSize(220 * getWidth() / 1440);
        canvas.drawText("WELCOME TO", 25 * getWidth() / 1440, 575 * getHeight() / 2560, nam);
        nam.setTextSize(245 * getWidth() / 1440);
        nam.setColor(Color.BLUE);
        canvas.drawText("CONNECT 4!", 25 * getWidth() / 1440, 1000 * getHeight() / 2560, nam);

        //Check vertical 4 in a rows
        if (!gameOver) {
            for (int col = 0; col < COL; col++) {
                for (int row = 0; row <= ROW-4; row++) {
                    chip0 = squares[row][col].getState();
                    chip1 = squares[row+1][col].getState();
                    chip2 = squares[row+2][col].getState();
                    chip3 = squares[row+3][col].getState();
                    if (chip0 + chip1 + chip2 + chip3 == 4) {
                        gameOver = true;
                        playerWin = 1;
                        break;
                    } else if (chip0 + chip1 + chip2 + chip3 == 8) {
                        gameOver = true;
                        playerWin = 2;
                        break;
                    }
                }
            }
        }

        //Check horizontal 4 in a rows
        if (!gameOver) {
            for (int row = 0; row < ROW; row ++) {
                for (int col = 0; col <= COL-4; col++) {
                    chip0 = squares[row][col].getState();
                    chip1 = squares[row][col+1].getState();
                    chip2 = squares[row][col+2].getState();
                    chip3 = squares[row][col+3].getState();
                    if (chip0 + chip1 + chip2 + chip3 == 4) {
                        gameOver = true;
                        playerWin = 1;
                        break;
                    } else if (chip0 + chip1 + chip2 + chip3 == 8) {
                        gameOver = true;
                        playerWin = 2;
                        break;
                    }
                }
            }
        }
        //Check diagonal 4 in a rows (LEFT TO RIGHT)
        if (!gameOver) {
            for (int col = 0; col <= COL-4; col++) {
                for (int row = 0; row <= ROW-4; row++) {
                    chip0 = squares[row][col].getState();
                    chip1 = squares[row+1][col+1].getState();
                    chip2 = squares[row+2][col+2].getState();
                    chip3 = squares[row+3][col+3].getState();
                    if (chip0 + chip1 + chip2 + chip3 == 4) {
                        gameOver = true;
                        playerWin = 1;
                        break;
                    } else if (chip0 + chip1 + chip2 + chip3 == 8) {
                        gameOver = true;
                        playerWin = 2;
                        break;
                    }
                }
            }
        }

        //Check diagonal 4 in a rows (RIGHT TO LEFT)
        if (!gameOver) {
            for (int col = COL-1; col >= 3; col--) {
                for (int row = 0; row <= ROW-4; row++) {
                    chip0 = squares[row][col].getState();
                    chip1 = squares[row+1][col-1].getState();
                    chip2 = squares[row+2][col-2].getState();
                    chip3 = squares[row+3][col-3].getState();
                    if (chip0 + chip1 + chip2 + chip3 == 4) {
                        gameOver = true;
                        playerWin = 1;
                        break;
                    } else if (chip0 + chip1 + chip2 + chip3 == 8) {
                        gameOver = true;
                        playerWin = 2;
                        break;
                    }
                }
            }
        }

        //Game over action
        if (gameOver) {
            nam.setTextSize(245 * getWidth() / 1440);
            nam.setColor(Color.RED);
            canvas.drawRect(0, 0, getWidth(), getHeight(), nam);
            nam.setColor(Color.WHITE);
            canvas.drawText("GAME OVER!", 0, getHeight() / 2, nam);
            if (playerWin == 1) {
                canvas.drawText("RED WINS!", 0, 2 * getHeight() / 3, nam);
            }else if (playerWin == 2) {
                canvas.drawText("BLACK WINS!", 0, 2 * getHeight() / 3, nam);
            }
        }
        invalidate();

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        board = new RectF(0, 7 * getHeight() / 16, getWidth(), getHeight());
        for (int j = 0; j < ROW; j++) {
            for (int i = 0; i < COL; i++) {
                squares[j][i] = new Cell((board.width() / COL) * i, (board.height() / ROW) * j + board.top, (board.width() / COL) * (i + 1), (board.height() / ROW) * (j + 1) + board.top);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int col=0;
        if(event.getX()<board.width()/COL){col=0;}
        else if(event.getX()<2*board.width()/COL){col=1;}
        else if(event.getX()<3*board.width()/COL){col=2;}
        else if(event.getX()<4*board.width()/COL){col=3;}
        else if(event.getX()<5*board.width()/COL){col=4;}
        else if(event.getX()<6*board.width()/COL){col=5;}
        else if(event.getX()<7*board.width()/COL){col=6;}

        for (int i = ROW-1; i >= 0; i--) {
            if (squares[i][col].isOpen()) {
                squares[i][col].assignState(turnCount++);
                break;
            }
        }
        return super.onTouchEvent(event);
    }
}

