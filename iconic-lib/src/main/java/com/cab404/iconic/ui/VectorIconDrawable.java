package com.cab404.iconic.ui;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.view.animation.Interpolator;

import com.cab404.iconic.numbers.InterpolationUtils;
import com.cab404.iconic.numbers.VectorIcon;
import com.cab404.iconic.numbers.VectorIconInterpolator;

import java.util.ArrayList;
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

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private List<VectorIcon> iconStack = new ArrayList<>();
    private VectorIconInterpolator vectorIconInterpolator = new VectorIconInterpolator();
    private Interpolator interpolationAlgorithm = InterpolationUtils.IP_LINEAR;
    private long transitionDuration = 200;

    public void setIcon(VectorIcon icon) {
        iconStack.add(icon);
        invalidateSelf();
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public Paint getPaint() {
        return paint;
    }

    public VectorIconInterpolator getVectorIconInterpolator() {
        return vectorIconInterpolator;
    }

    public void setVectorIconInterpolator(VectorIconInterpolator vectorIconInterpolator) {
        this.vectorIconInterpolator = vectorIconInterpolator;
    }

    public long getTransitionDuration() {
        return transitionDuration;
    }

    public void setTransitionDuration(long transitionDuration) {
        this.transitionDuration = transitionDuration;
    }

    public Interpolator getInterpolationAlgorithm() {
        return interpolationAlgorithm;
    }

    public void setInterpolationAlgorithm(Interpolator interpolationAlgorithm) {
        this.interpolationAlgorithm = interpolationAlgorithm;
    }

    long started_transition = -1;

    @Override
    public void draw(Canvas canvas) {
        if (iconStack.isEmpty()) return;

        if (iconStack.size() == 1) {
            iconStack.get(0).draw(canvas, paint);
        } else {
            VectorIcon a = iconStack.get(0);
            VectorIcon b = iconStack.get(1);

            if (started_transition == -1) {
                started_transition = System.currentTimeMillis();
                vectorIconInterpolator.newInterpolation(a, b, interpolationAlgorithm);
            }

            float progress = (System.currentTimeMillis() - started_transition) / (float) transitionDuration;

            if (progress >= 1) {
                progress = 1;
                started_transition = -1;
                iconStack.remove(0);
            }

            vectorIconInterpolator.interpolate(progress).draw(canvas, paint);
            invalidateSelf();
        }


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
