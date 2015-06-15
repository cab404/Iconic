package com.cab404.iconic.numbers;

/**
 * Well, sorry for no comments here!
 * Still you can send me your question to me@cab404.ru!
 * <p/>
 * Created at 12:49 on 12/06/15
 *
 * @author cab404
 */
public class Generic2Tuple {

    Class first;
    Class second;

    public Generic2Tuple(Class first, Class second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Generic2Tuple that = (Generic2Tuple) o;

        return first.equals(that.first) && second.equals(that.second);
    }

    @Override
    public int hashCode() {
        int result = first.hashCode();
        result = 31 * result + second.hashCode();
        return result;
    }
}
