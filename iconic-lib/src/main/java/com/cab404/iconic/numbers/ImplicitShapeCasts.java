package com.cab404.iconic.numbers;

import java.util.HashMap;
import java.util.Map;

/**
 * Stores casted shapes.
 * <p/>
 * Created at 12:36 on 12/06/15
 *
 * @author cab404
 */
public class ImplicitShapeCasts {

    private static Map<Generic2Tuple, ShapeCast> casts = new HashMap<>();

    @SuppressWarnings("unchecked")
    public static <From extends ShapeProcessor, To extends ShapeProcessor>
    void addCast(Class<From> from, Class<To> to, ShapeCast<From, To> cast) {
        casts.put(new Generic2Tuple(from, to), cast);
    }

    private static final Generic2Tuple RETRIEVAL_KEY = new Generic2Tuple(null, null);

    @SuppressWarnings("unchecked")
    public static <From extends ShapeProcessor, To extends ShapeProcessor>
    ShapeCast<From, To> get(Class from, Class to) {
        RETRIEVAL_KEY.first = from;
        RETRIEVAL_KEY.second = to;
        return casts.get(RETRIEVAL_KEY);
    }

}
