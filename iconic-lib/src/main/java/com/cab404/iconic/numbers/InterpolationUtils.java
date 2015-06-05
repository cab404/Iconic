package com.cab404.iconic.numbers;

import android.graphics.Color;
import android.graphics.PointF;
import android.view.animation.Interpolator;

/**
 * Methods for interpolating objects
 *
 * <p/>
 * Created at 10:54 on 03/06/15
 *
 * @author cab404
 */
public class InterpolationUtils {

    /* x */
    public static final Interpolator IP_LINEAR = new Interpolator() {
        @Override
        public float getInterpolation(float input) {
            return input;
        }
    };

    /**
     * x^2
     */
    public static final Interpolator IP_QUADRATIC = new Interpolator() {
        @Override
        public float getInterpolation(float input) {
            return input * input;
        }
    };


    /**
     * log₁₀(1+x*9)
     */
    public static final Interpolator IP_LOGARITHMIC = new Interpolator() {
        @Override
        public float getInterpolation(float input) {
            return (float) Math.log10(input * 9f + 1);
        }
    };

    /**
     * Interpolates between two floats
     */
    public static float interpolateFloat(float start, float end, float progress, Interpolator how) {
        return start + (end - start) * how.getInterpolation(progress);
    }

    /**
     * Interpolates between two argb colors using a, r, g and b channels for float interpolation
     */
    public static int interpolateColor(int start, int end, float progress, Interpolator how) {
        progress = how.getInterpolation(progress);
        int a = Color.alpha(start) + (int) ((Color.alpha(end) - Color.alpha(start)) * progress);
        int r = Color.red(start) + (int) ((Color.red(end) - Color.red(start)) * progress);
        int g = Color.green(start) + (int) ((Color.green(end) - Color.green(start)) * progress);
        int b = Color.blue(start) + (int) ((Color.blue(end) - Color.blue(start)) * progress);
        return Color.argb(a, r, g, b);
    }

    /**
     * Interpolates between two points and stores result in source point
     *
     * @return start
     */
    public static void interpolatePointF(PointF start, PointF end, PointF container, float progress, Interpolator how) {
        container.x = interpolateFloat(start.x, end.x, progress, how);
        container.y = interpolateFloat(start.y, end.y, progress, how);
    }

}
