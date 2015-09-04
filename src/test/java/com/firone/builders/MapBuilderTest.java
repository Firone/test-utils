package com.firone.builders;

import org.junit.Test;

import java.util.Map;

import static com.firone.builders.MapBuilder.map;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.is;

public class MapBuilderTest {

    @Test
    public void can_build_empty_map() throws Exception {

        Map builtMap = map().build();

        assertThat(builtMap.size(), is(0));
    }

    @Test
    public void can_build_typed_map_with_some_entries() throws Exception {

        Map<String, String> builtMap = map("key1", "value1")
                .entry("key2", "value2")
                .build();

        assertThat(builtMap, hasEntry("key1", "value1"));
        assertThat(builtMap, hasEntry("key2", "value2"));
    }
}
