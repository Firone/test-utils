package com.firone.hamcrest.json;

import org.junit.Test;

import static com.firone.hamcrest.json.JsonHasKeyWithValueMatcher.jsonHasKeyWithValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class JsonHasKeyWithValueMatcherTest {

    @Test
    public void can_assert_key_with_value_are_valid() {
        assertThat("{" +
                   "\"key1\":\"value1\"," +
                   "\"key2\":\"value2\"" +
                   "}", jsonHasKeyWithValue("key1", "value1"));
    }

    @Test
    public void can_assert_multi_level_key_with_value_are_valid() {
        assertThat("{" +
                   "\"multi\":{" +
                   "    \"level\":{" +
                   "        \"key1\":\"value1\"," +
                   "        \"key2\":\"value2\"" +
                   "    }" +
                   "}" +
                   "}", jsonHasKeyWithValue("multi.level.key1", "value1"));
    }

    @Test(expected = AssertionError.class)
    public void fails_when_multi_level_key_has_invalid_sub_level() {
        assertThat("{" +
                   "\"multi\":{" +
                   "    \"level\":{" +
                   "        \"key1\":\"value1\"," +
                   "    }" +
                   "}" +
                   "}", jsonHasKeyWithValue("multi.unknown.sub.level.key1", "value1"));
    }

    @Test(expected = AssertionError.class)
    public void fails_if_value_does_not_match_with_existing_key() {
        assertThat("{" +
                   "\"key1\":\"value\"" +
                   "}", jsonHasKeyWithValue("key1", "no match"));
    }

    @Test(expected = AssertionError.class)
    public void fails_if_value_does_not_match_with_existing_multi_level_key() {
        assertThat("{" +
                   "\"multi\":{" +
                   "    \"level\":{" +
                   "        \"key\":\"value\"" +
                   "    }" +
                   "}" +
                   "}", jsonHasKeyWithValue("multi.level.key", "no match"));
    }

    @Test(expected = AssertionError.class)
    public void fails_if_nonexistent_key() {
        assertThat("{" +
                   "\"key\":\"value\"" +
                   "}", jsonHasKeyWithValue("non existent", "value"));
    }
}
