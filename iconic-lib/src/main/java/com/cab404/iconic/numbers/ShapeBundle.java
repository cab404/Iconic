package com.cab404.iconic.numbers;

/**
 * Shape and it's data.
 * <p/>
 * Created at 23:58 on 03/06/15
 *
 * @author cab404
 */
public class ShapeBundle {
    public Object data;
    public ShapeProcessor processor;
    public int id = -1;

    public ShapeBundle(ShapeProcessor processor, Object data) {
        this.data = data;
        this.processor = processor;
    }

    @Override
    public String toString() {
        return "ShapeBundle{" +
                "data=" + data +
                ", processor=" + processor +
                '}';
    }
}
