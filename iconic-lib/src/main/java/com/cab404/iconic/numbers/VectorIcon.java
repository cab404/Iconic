package com.cab404.iconic.numbers;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.graphics.Canvas;
import android.graphics.Paint;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Vector icon.
 * <p/>
 * Created at 12:35 on 03/06/15
 *
 * @author cab404
 */
public class VectorIcon {

    /**
     * Icon processors
     */
    static final ConstructingPool<ShapeProcessor> PROCESSORS = new ReflectivePool<>();

    /**
     * Shapes of this object
     */
    public List<ShapeBundle> iconData = new ArrayList<>();


    /**
     * Creates new icon from file
     */
    public VectorIcon(Context ctx, int xml_id) {
        try {
            XmlResourceParser xml = ctx.getResources().getXml(xml_id);
            while (xml.next() != XmlPullParser.END_DOCUMENT) {
                String class_name = xml.getName();

                if (xml.getEventType() == XmlPullParser.START_TAG) {
                    Class converterClass;
                    if (class_name.contains("."))
                        // Trying to get class by name
                        converterClass = Class.forName(class_name);
                    else
                        // If there's no dots in class name, then presume we are dealing with default package.
                        converterClass = Class.forName("com.cab404.iconic.shapes." + class_name);

                    // Simple type check
                    if (!ShapeProcessor.class.isAssignableFrom(converterClass))
                        throw new RuntimeException("Class " + converterClass + " cannot be casted to ShapeProcessor!");

                    // We're now pretty sure that is a ShapeProcessor
                    //noinspection unchecked
                    ShapeProcessor processor = PROCESSORS.getInstance(converterClass);

                    ShapeBundle shape = new ShapeBundle(processor, processor.makeDataStorage());

                    // ID's, to make beeeeauuutiful transitions between shapes.
                    shape.id = xml.getIdAttributeResourceValue(-1);

                    // Once again, we are sure that given object is compatible with method in it's creator.
                    //noinspection unchecked
                    processor.fillDataStorage(ctx, shape.data, xml);

                    iconData.add(shape);

                }

                xml.next();

                System.out.println(iconData);
            }
        } catch (Exception e) {
            throw new RuntimeException("Cannot read icon!", e);
        }
    }

    /**
     * Creates an empty icon
     */
    public VectorIcon() {
    }

    public void draw(Canvas cvs, Paint paint) {
        for (ShapeBundle shape : iconData) {
            //noinspection unchecked
            shape.processor.draw(cvs, paint, shape.data);
        }
    }

}
