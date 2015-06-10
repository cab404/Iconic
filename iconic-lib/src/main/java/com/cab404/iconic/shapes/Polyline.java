package com.cab404.iconic.shapes;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.animation.Interpolator;

import com.cab404.iconic.numbers.ShapeProcessor;
import com.cab404.iconic.shapes.data.PolylineData;

/**
 * Well, sorry for no comments here!
 * Still you can send me your question to me@cab404.ru!
 * <p/>
 * Created at 12:31 on 06/06/15
 *
 * @author cab404
 */
public class Polyline implements ShapeProcessor<PolylineData> {

    Path path = new Path();
    @Override
    public void draw(Canvas cvs, Paint paint, PolylineData shape) {
        path.reset();
    }

    @Override
    public void interpolate(float progress, Interpolator interpolator, PolylineData source, PolylineData destination, PolylineData container) {

    }

    @Override
    public PolylineData makeDataStorage() {
        return new PolylineData();
    }

    @Override
    public void fillDataStorage(Context ctx, PolylineData polylineData, XmlResourceParser xml) {

    }
}
