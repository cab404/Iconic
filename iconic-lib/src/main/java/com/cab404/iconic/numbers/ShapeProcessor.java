package com.cab404.iconic.numbers;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.animation.Interpolator;

import org.xmlpull.v1.XmlPullParser;

/**
 * Renders shape on canvas and interpolates it between two states
 * <p/>
 * Created at 10:45 on 03/06/15
 *
 * @author cab404
 */
public interface ShapeProcessor<ShapeState> {

    void draw(Canvas cvs, Paint paint, ShapeState shape);

    void interpolate(float progress, Interpolator interpolator, ShapeState source, ShapeState destination, ShapeState container);

    ShapeState makeDataStorage();

    void fillDataStorage(Context ctx, ShapeState state, XmlResourceParser xml);



}
