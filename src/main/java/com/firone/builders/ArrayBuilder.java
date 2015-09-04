package com.firone.builders;

public class ArrayBuilder {

    private ArrayBuilder() {
    }

    @SafeVarargs
    public static <T> T[] array(T... data) {
        return data;
    }
}
