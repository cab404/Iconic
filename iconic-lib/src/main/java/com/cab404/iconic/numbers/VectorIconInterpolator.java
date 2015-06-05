package com.cab404.iconic.numbers;

import android.view.animation.Interpolator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Creates interpolations between two icons.
 * Created at 07:37 on 04/06/15
 *
 * @author cab404
 */
public class VectorIconInterpolator {

    private Interpolator interpolator;
    private VectorIcon bufferIcon;
    private List<ShapeLink> connections;

    /**
     * Goes round and round, giving all shapes evenly
     */
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

    /**
     * Clears interpolator for new interpolation
     */
    public void newInterpolation(VectorIcon a, VectorIcon b, Interpolator interpolator) {
        this.bufferIcon = new VectorIcon();
        this.connections = new ArrayList<>();
        this.interpolator = interpolator;

        // Now we are going to make connections
        // In this case by connections I mean links between shapes in start object and end object,
        // using which we are going to interpolate whole icon.

        // Creating a handy little map, which will track shape usage for us.
        HashMap<Class, ShapeCounter> shapes = indexIcon(a);

        // Copying list, so we can keep track of unused objects
        List<ShapeBundle> things = new ArrayList<>(a.iconData);

        // Searching source shape for each destination shape
        for (ShapeBundle dest : b.iconData) {
            ShapeBundle c = new ShapeBundle(dest.processor, dest.processor.makeDataStorage());

            ShapeLink link;
            if (shapes.containsKey(dest.processor.getClass()))
                // If there is a class in shape map, there should be at least one shape.
                link = new ShapeLink(shapes.get(dest.processor.getClass()).get(), dest, c);
            else
                // Or we'll just stick to good old 'appear from nothing'.
                link = new ShapeLink(new ShapeBundle(dest.processor, null), dest, c);

            // Removing shape from unused.
            things.remove(link.a);
//            Log.e("Poof", "connection " + link.a + " → " + link.b + " :: " + link.c);

            // Adding discovered link to list and to buffer icon.
            connections.add(link);
            bufferIcon.iconData.add(link.c);
        }

        // If not all of parts of first shape are used while
        // creating relations, then trying to find them backwards -
        // for each unused source shape we are searching for destination shape.
        // Basically, we are repeating the process above.
        if (!things.isEmpty()) {
            shapes = indexIcon(b);
//            Log.e("Poof", "not finished yet");

            for (ShapeBundle source : things) {
                ShapeBundle c = new ShapeBundle(source.processor, source.processor.makeDataStorage());
                ShapeLink link;

                if (shapes.containsKey(source.processor.getClass()))
                    link = new ShapeLink(source, shapes.get(source.processor.getClass()).get(), c);
                else
                    link = new ShapeLink(source, new ShapeBundle(source.processor, null), c);

                connections.add(link);
                bufferIcon.iconData.add(link.c);

//            Log.e("Poof", "connection " + link.a + " → " + link.b + " :: " + link.c);
            }
        }
//        Log.e("Poof", "ended up with " + bufferIcon.iconData.size() + " figures");

    }

    /**
     * Helps distributing shapes evenly and removes pain of searching class you need.
     */
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

    /**
     * Interpolates icon to given progress.
     * Note that icon will remain the same object until next
     * call to {@link #newInterpolation(VectorIcon, VectorIcon, Interpolator) newInterpolation}
     */
    public VectorIcon interpolate(float progress) {
        for (ShapeLink l : connections) {
            //noinspection unchecked
            l.a.processor.interpolate(progress, interpolator, l.a.data, l.b.data, l.c.data);
        }
        return bufferIcon;
    }

}
