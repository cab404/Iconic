package com.cab404.iconic.numbers;

import android.graphics.PointF;
import android.util.Log;

/**
 * Well, sorry for no comments here!
 * Still you can send me your question to me@cab404.ru!
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
