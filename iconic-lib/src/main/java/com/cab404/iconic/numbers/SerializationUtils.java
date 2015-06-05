package com.cab404.iconic.numbers;

import android.graphics.PointF;
import android.util.Log;

/**
 * Simple methods to help storing and retrieving data from xml parameters.
 * <p/>
 * Created at 21:00 on 03/06/15
 *
 * @author cab404
 */
public class SerializationUtils {
    public static String pointFToString(PointF o){
        return o.x + ":" + o.y;
    }

    public static void parsePointF(PointF where, String o){
        int delimiter = o.indexOf(':');
        where.x = Float.parseFloat(o.substring(0, delimiter));
        where.y = Float.parseFloat(o.substring(delimiter + 1));
    }

}
