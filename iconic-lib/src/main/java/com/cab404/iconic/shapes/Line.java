package com.cab404.iconic.shapes;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.animation.Interpolator;

import com.cab404.iconic.numbers.InterpolationUtils;
import com.cab404.iconic.numbers.SerializationUtils;
import com.cab404.iconic.numbers.ShapeProcessor;

/**
 * Line. Yup, line, that's all.
 * <p/>
 * Created at 20:20 on 03/06/15
 *
 * @author cab404
 */
public class Line implements ShapeProcessor<LineData> {

    float[] buffer = new float[4];

    @Override
    public void draw(Canvas cvs, Paint paint, LineData shape) {
        buffer[0] = cvs.getWidth() * shape.a.x;
        buffer[1] = cvs.getHeight() * shape.a.y;
        buffer[2] = cvs.getWidth() * shape.b.x;
        buffer[3] = cvs.getHeight() * shape.b.y;

        paint.setColor(shape.color);
        paint.setStrokeWidth(shape.thickness * cvs.getWidth());
        paint.setStyle(Paint.Style.FILL_AND_STROKE);

        cvs.drawLines(buffer, paint);

        paint.setStyle(Paint.Style.FILL);

        for (int i = 0; i < buffer.length / 2; i++)
            cvs.drawCircle(buffer[i * 2], buffer[i * 2 + 1], paint.getStrokeWidth() / 2, paint);

    }

    @Override
    public void interpolate(float p, Interpolator i, LineData a, LineData b, LineData c) {
        InterpolationUtils.interpolatePointF(a.a, b.a, c.a, p, i);
        InterpolationUtils.interpolatePointF(a.b, b.b, c.b, p, i);
        c.color = InterpolationUtils.interpolateColor(a.color, b.color, p, i);
        c.thickness = InterpolationUtils.interpolateFloat(a.thickness, b.thickness, p, i);
    }

    @Override
    public LineData makeDataStorage() {
        return new LineData();
    }

    @Override
    public void fillDataStorage(Context ctx, LineData lineData, XmlResourceParser xml) {
        if (xml.getAttributeValue(null, "a") == null)
            throw new RuntimeException("Can't make line without start point");
        if (xml.getAttributeValue(null, "b") == null)
            throw new RuntimeException("Can't make line without start point");
        SerializationUtils.parsePointF(lineData.a, xml.getAttributeValue(null, "a"));
        SerializationUtils.parsePointF(lineData.b, xml.getAttributeValue(null, "b"));
        lineData.thickness = xml.getAttributeFloatValue(null, "thickness", 0.1f);
        lineData.color = xml.getAttributeIntValue(null, "color", 0xffffffff);
    }
}
