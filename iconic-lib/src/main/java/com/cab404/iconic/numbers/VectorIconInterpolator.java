package com.cab404.iconic.numbers;

import android.util.Log;
import android.view.animation.Interpolator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Well, sorry for no comments here!
 * Still you can send me your question to me@cab404.ru!
 * <p/>
 * Created at 07:37 on 04/06/15
 *
 * @author cab404
 */
public class VectorIconInterpolator {

    private Interpolator interpolator;
    private VectorIcon res;

    private List<ShapeLink> connections;

    private class ShapeCounter {
        List<ShapeBundle> shapes = new ArrayList<>();
        int counter = 0;

        ShapeBundle get() {
            try {
                return shapes.get(counter);
            } finally {
                counter++;
                counter %= shapes.size();
            }
        }
    }

    public void newInterpolation(VectorIcon a, VectorIcon b, Interpolator interpolator) {
        this.connections = new ArrayList<>();
        this.interpolator = interpolator;
        this.res = new VectorIcon();

        HashMap<Class, ShapeCounter> shapes = indexIcon(a);
        List<ShapeBundle> things = new ArrayList<>(a.iconData);

        for (ShapeBundle dest : b.iconData) {
            ShapeBundle c = new ShapeBundle(dest.processor, dest.processor.makeDataStorage());

            ShapeLink link;
            if (shapes.containsKey(dest.processor.getClass()))
                link = new ShapeLink(shapes.get(dest.processor.getClass()).get(), dest, c);
            else
                link = new ShapeLink(new ShapeBundle(dest.processor, null), dest, c);

            things.remove(link.a);
            Log.e("Poof", "connection " + link.a + " → " + link.b + " :: " + link.c);
            connections.add(link);
            res.iconData.add(link.c);
        }

        if (!things.isEmpty()) {
            Log.e("Poof", "Not finished yet");
            shapes = indexIcon(b);

            for (ShapeBundle source : things) {
                ShapeBundle c = new ShapeBundle(source.processor, source.processor.makeDataStorage());
                ShapeLink link;
                if (shapes.containsKey(source.processor.getClass()))
                    link = new ShapeLink(source, shapes.get(source.processor.getClass()).get(), c);
                else
                    link = new ShapeLink(source, new ShapeBundle(source.processor, null), c);

                Log.e("Poof", "connection " + link.a + " → " + link.b + " :: " + link.c);
                connections.add(link);
                res.iconData.add(link.c);

            }
        }
        Log.e("Poof", "ended up with " + res.iconData.size() + " figures");

    }

    protected HashMap<Class, ShapeCounter> indexIcon(VectorIcon a) {
        HashMap<Class, ShapeCounter> shapes = new HashMap<>();
        for (ShapeBundle source : a.iconData) {
            ShapeCounter counter;
            if (!shapes.containsKey(source.processor.getClass()))
                shapes.put(source.processor.getClass(), counter = new ShapeCounter());
            else
                counter = shapes.get(source.processor.getClass());
            counter.shapes.add(source);
        }
        return shapes;
    }

    public VectorIcon interpolate(float progress) {
        for (ShapeLink l : connections) {
            //noinspection unchecked
            l.a.processor.interpolate(progress, interpolator, l.a.data, l.b.data, l.c.data);
        }
        return res;
    }

}
