package com.cab404.iconic.numbers;

import android.view.animation.Interpolator;

/**
 * Well, sorry for no comments here!
 * Still you can send me your question to me@cab404.ru!
 * <p/>
 * Created at 07:37 on 04/06/15
 *
 * @author cab404
 */
public class VectorIconInterpolator {

    private Interpolator interpolator;
    private VectorIcon res;
    private VectorIcon a;
    private VectorIcon b;

    public void newInterpolation(VectorIcon a, VectorIcon b, Interpolator interpolator){
        this.interpolator = interpolator;
        this.res = new VectorIcon();
        this.a = a;
        this.b = b;

        if (a.typeStamp == b.typeStamp) {
            for (int i = 0; i < a.iconData.size(); i++) {
                ShapeBundle as = a.iconData.get(i);
                Object container = as.processor.makeDataStorage();
                //noinspection unchecked
                res.iconData.add(new ShapeBundle(as.processor, container));
            }
        }

    }

    public VectorIcon interpolate(float progress) {

        if (a.typeStamp == b.typeStamp) {
            for (int i = 0; i < a.iconData.size(); i++) {
                ShapeBundle as = a.iconData.get(i);
                ShapeBundle bs = b.iconData.get(i);
                Object container = res.iconData.get(i).data;
                //noinspection unchecked
                as.processor.interpolate(progress, interpolator, as.data, bs.data, container);
            }
        }

        return res;
    }

}
