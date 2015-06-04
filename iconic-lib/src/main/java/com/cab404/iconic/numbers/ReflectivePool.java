package com.cab404.iconic.numbers;

/**
 * Simple reflection-based reuse pool.
 * Created at 20:41 on 03/06/15
 *
 * @author cab404
 */
public class ReflectivePool<A> extends ConstructingPool<A> {
    @Override
    protected A makeInstance(Class<? extends A> clazz) {
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Error while making instance of class " + clazz.getName(), e);
        }
    }
}
