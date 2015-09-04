package com.firone.hamcrest.json;

import org.junit.Test;

import static com.firone.hamcrest.json.JsonHasKeysMatcher.jsonContainsKeys;
import static org.hamcrest.MatcherAssert.assertThat;

public class JsonHasKeysMatcherTest {

    @Test
    public void can_assert_json_contains_all_keys() {
        assertThat("{" +
                "\"key1\":\"value1\"," +
                "\"key2\":\"value2\"" +
                "}", jsonContainsKeys("key1", "key2"));
    }

    @Test
    public void can_assert_empty_json() {
        assertThat("{}", jsonContainsKeys());
    }

    @Test(expected = AssertionError.class)
    public void fails_if_json_contains_more_keys() {
        assertThat("{" +
                "\"key1\":\"value1\"," +
                "\"key2\":\"value2\"" +
                "}", jsonContainsKeys("key1"));
    }

    @Test(expected = AssertionError.class)
    public void fails_if_json_contains_less_keys() {
        assertThat("{" +
                "\"key1\":\"value1\"," +
                "\"key2\":\"value2\"" +
                "}", jsonContainsKeys("key1", "key2", "key3"));
    }

    @Test(expected = AssertionError.class)
    public void fails_if_json_contains_unmatched_key() {
        assertThat("{" +
                "\"key1\":\"value1\"," +
                "\"key2\":\"value2\"" +
                "}", jsonContainsKeys("key1", "key3"));
    }
}
