package com.cab404.iconic.numbers;

/**
 * Well, sorry for no comments here!
 * Still you can send me your question to me@cab404.ru!
 * <p/>
 * Created at 12:36 on 12/06/15
 *
 * @author cab404
 */
public interface ShapeCast<From extends ShapeProcessor, To extends ShapeProcessor> {
    ShapeBundle<From> castConditions(ShapeBundle<From> data);

    ShapeBundle<To> cast(ShapeBundle<From> data);
}
