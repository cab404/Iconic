package com.cab404.iconic.numbers;

/**
 * Simple link container class
 * Created at 07:39 on 04/06/15
 *
 * @author cab404
 */
public class ShapeLink {
    /**
     * Source shape
     */
    ShapeBundle a;
    /**
     * Destination shape
     */
    ShapeBundle b;
    /**
     * Container shape
     */
    ShapeBundle c;

    /**
     * @param a Source shape
     * @param b Destination shape
     * @param c Container shape
     */
    public ShapeLink(ShapeBundle a, ShapeBundle b, ShapeBundle c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
}
