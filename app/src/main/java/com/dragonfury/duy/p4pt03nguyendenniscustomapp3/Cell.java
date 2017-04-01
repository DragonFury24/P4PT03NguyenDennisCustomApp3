package com.dragonfury.duy.p4pt03nguyendenniscustomapp3;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * Created by Duy on 3/28/2017.
 */
public class Cell extends RectF {
    public static final Creator<RectF> CREATOR = null;

    int state = 100;
    public Cell(float left, float top, float right, float bottom) {
        super(left, top, right, bottom);
    }

    public void draw(Canvas c, Paint p){
        //Draw Blank Circles in each box
        p.setColor(Color.WHITE);
        p.setStyle(Paint.Style.FILL);
        c.drawCircle(centerX(), centerY(), width()/2, p);
        //Draw Rectangle Outline of each box
        p.setColor(Color.BLACK);
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(10 * c.getWidth() / 1440);
        c.drawRect(this, p);

        //Draw Black Circle or Red Circle depending on player turn
        if (state==0) {
            p.setStyle(Paint.Style.FILL);
            p.setColor(Color.RED);
            c.drawCircle(centerX(), centerY(), width()/2, p);
        }
        if (state==1) {
            p.setStyle(Paint.Style.FILL);
            p.setColor(Color.BLACK);
            c.drawCircle(centerX(), centerY(), width()/2, p);
        }
    }
    public boolean isOpen() {

        return state==100;
    }

    public void assignState(int turnCount) {
        state = turnCount%2 ;
    }

    public int getState(){
        return state;
    }

}




