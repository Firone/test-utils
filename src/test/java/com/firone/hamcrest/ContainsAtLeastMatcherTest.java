package com.firone.hamcrest;

import org.junit.Test;

import java.util.Map;

import static com.firone.builders.ArrayBuilder.array;
import static com.firone.builders.MapBuilder.map;
import static com.firone.hamcrest.ContainsAtLeastMatcher.containsAtLeastEntry;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.fail;

public class ContainsAtLeastMatcherTest {

    @Test
    public void can_assert_entry_exists_in_map() {

        Map<String, String> map = map("key1", "value1").build();

        assertThat(map, containsAtLeastEntry("key1", "value1"));
    }

    @Test
    public void can_assert_entry_exists_in_map_with_other_entries() {

        Map<String, String> map = map("key1", "value1")
                .entry("key2", "value2")
                .entry("key3", "value3")
                .build();

        assertThat(map, containsAtLeastEntry("key2", "value2"));
        assertThat(map, containsAtLeastEntry("key3", "value3"));
    }

    @Test
    public void can_assert_entry_exists_with_many_types() {

        String[] array1 = array("value1");
        String[] array2 = array("value2");
        String[] array3 = array("value3");

        Map<String, String[]> map = map("key1", array1)
                .entry("key2", array2)
                .entry("key3", array3)
                .build();

        assertThat(map, containsAtLeastEntry("key1", array1));
        assertThat(map, containsAtLeastEntry("key2", array2));
        assertThat(map, containsAtLeastEntry("key3", array3));
    }

    @Test(expected = AssertionError.class)
    public void error_when_map_does_not_contain_correct_value_for_key() {

        Map<String, String> map = map("key1", "value1")
                .entry("key2", "value2")
                .entry("key3", "value3")
                .build();

        assertThat(map, containsAtLeastEntry("key1", "value2"));
    }

    @Test(expected = AssertionError.class)
    public void error_when_map_does_not_contain_key() {

        Map<String, String> map = map("key1", "value1")
                .entry("key2", "value2")
                .entry("key3", "value3")
                .build();

        assertThat(map, containsAtLeastEntry("not_existent_key", "value"));
    }

    @Test(expected = AssertionError.class)
    public void error_when_map_is_null() {

        assertThat(null, containsAtLeastEntry("not_existent_key", "value"));
    }

    @Test
    public void get_correct_error_message() {

        Map<String, String> map = map("key1", "value1")
                .build();

        try {
            assertThat(map, containsAtLeastEntry("key1", "wrongValue"));
            fail("Have to fail !");
        } catch (AssertionError e) {
            String wantedErrorMessage = "\nExpected: a map containing at least entry [key1=wrongValue]\n" +
                    "     but: was <{key1=value1}>";
            assertThat(e.getMessage(), is(wantedErrorMessage));
        }
    }
}
