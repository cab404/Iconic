package com.cab404.iconic.shapes;

import android.graphics.PointF;

/**
 * Line attributes
 * <p/>
 * Created at 20:21 on 03/06/15
 *
 * @author cab404
 */
public class LineData {
    public float thickness;
    public int color;
    public PointF a = new PointF(.5f, .5f), b = new PointF(.5f, .5f);

    @Override
    public String toString() {
        return "LineData{" +
                "thickness=" + thickness +
                ", color=" + color +
                ", a=" + a +
                ", b=" + b +
                '}';
    }
}
