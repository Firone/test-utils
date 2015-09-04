package com.firone.builders;

import org.junit.Test;

import static com.firone.builders.ArrayBuilder.array;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.arrayContaining;
import static org.hamcrest.Matchers.emptyArray;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class ArrayBuilderTest {

    @Test
    public void can_build_empty_map() throws Exception {

        Object[] array = array();

        assertThat(array, is(notNullValue()));
        assertThat(array, emptyArray());
    }

    @Test
    public void can_build_typed_array_with_some_entries() throws Exception {

        String[] array = array("value1", "value2");

        assertThat(array, arrayContaining("value1", "value2"));
    }
}
