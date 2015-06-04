package com.cab404.iconic.ui;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.DrawFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.cab404.iconic.numbers.VectorIcon;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

/**
 * Well, sorry for no comments here!
 * Still you can send me your question to me@cab404.ru!
 * <p/>
 * Created at 21:48 on 01/06/15
 *
 * @author cab404
 */
public class VectorIconDrawable extends Drawable {

    protected Paint paint = new Paint();
    private Deque<VectorIcon> iconStack = new ArrayDeque<>();

    public void setIcon(VectorIcon icon) {
        iconStack.add(icon);
        invalidateSelf();
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }


    long lastUpdate = -1;


    @Override
    public void draw(Canvas canvas) {
        if (lastUpdate == -1)
            lastUpdate = System.currentTimeMillis();

        iconStack.getFirst().draw(canvas);
        invalidateSelf();
    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(ColorFilter cf) {
    }

    @Override
    public int getOpacity() {
        return PixelFormat.OPAQUE;
    }

}
