package com.firone.hamcrest;

import org.junit.Test;

import java.util.List;

import static com.firone.hamcrest.IsCollectionContainingAtLeastInAnyOrder.containsAtLeastInAnyOrder;
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;

public class IsCollectionContainingAtLeastInAnyOrderTest {

    @Test
    public void can_assert_that_a_collection_contains_at_least_any_elements() {

        List<String> baseData = asList("data1", "data2", "data3");

        assertThat(baseData, containsAtLeastInAnyOrder("data3", "data1"));
    }

    @Test(expected = AssertionError.class)
    public void error_if_a_wanted_element_is_absent() {

        List<String> baseData = asList("data1", "data2", "data3", "data4");

        assertThat(baseData, containsAtLeastInAnyOrder("data1", "data15", "data4"));
    }
}
