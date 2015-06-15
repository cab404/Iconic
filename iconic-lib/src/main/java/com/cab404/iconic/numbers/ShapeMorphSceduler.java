package com.cab404.iconic.numbers;

import android.util.Log;
import android.view.animation.Interpolator;

import java.util.ArrayList;
import java.util.List;

/**
 * Interpolates shape through multiple states
 *
 * @author cab404
 */
public class ShapeMorphSceduler {
    List<ShapeBundle> stages = new ArrayList<>();

    @SuppressWarnings("unchecked")
    public void add(ShapeBundle stage) {
        if (stages.size() == 0) {
            stages.add(stage);
            return;
        }
        ShapeBundle last = stages.get(stages.size() - 1);

        if (last.processor.getClass().equals(stage.processor.getClass()))
            stages.add(stage);
        else {
            ShapeCast cast =
                    ImplicitShapeCasts.get(
                            last.processor.getClass(),
                            stage.processor.getClass()
                    );

            if (cast == null)
                throw new RuntimeException(
                        String.format(
                                "No implicit cast from class %s to class %s",
                                last.processor.getClass(),
                                stage.processor.getClass()
                        ));

            stages.add(cast.castConditions(last));
            stages.add(stage);

        }
    }

    ShapeBundle currentBuffer = new ShapeBundle(null, null);

    public ShapeBundle interpolate(float progress, Interpolator interpolator) {
        if (stages.size() == 0) return null;

        float interpolated_progress = interpolator.getInterpolation(progress);
        int index = (int) Math.ceil(stages.size() * interpolated_progress);
        float subprogress = interpolated_progress - (1f / index);

        if (index == stages.size()) return stages.get(stages.size() - 1);
        if (index == 0) return stages.get(0);

        ShapeBundle from = stages.get(index);
        ShapeBundle to = stages.get(index);

        if (from.processor.getClass().equals(to.processor.getClass())) {
            if (currentBuffer.data == null || !currentBuffer.processor.getClass().equals(from.processor.getClass())) {
                currentBuffer.processor = from.processor;
                currentBuffer.data = from.processor.makeDataStorage();
            }
            //noinspection unchecked
            from.processor.interpolate(subprogress, interpolator, from.data, to.data, currentBuffer);

            return currentBuffer;
        }
        return null;
    }

}
