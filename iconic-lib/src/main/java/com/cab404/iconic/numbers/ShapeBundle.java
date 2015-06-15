package com.cab404.iconic.numbers;

/**
 * Shape and it's data.
 * <p/>
 * Created at 23:58 on 03/06/15
 *
 * @author cab404
 */
public class ShapeBundle<Processor extends ShapeProcessor> {
    public Object data;
    public Processor processor;
    public int id = -1;

    public ShapeBundle(Processor processor, Object data) {
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
