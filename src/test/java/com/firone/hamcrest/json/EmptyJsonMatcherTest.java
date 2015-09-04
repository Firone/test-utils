package com.firone.hamcrest.json;

import org.junit.Test;

import static com.firone.hamcrest.json.EmptyJsonMatcher.emptyJson;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class EmptyJsonMatcherTest {

    @Test
    public void can_assert_json_is_empty() {
        assertThat("{}", is(emptyJson()));
    }

    @Test
    public void can_assert_json_is_empty_with_one_whitespace() {
        assertThat("{ }", is(emptyJson()));
    }

    @Test
    public void can_assert_json_is_empty_with_whitespaces() {
        assertThat("{         }", is(emptyJson()));
    }

    @Test
    public void can_assert_json_is_empty_with_cariage_return() {
        assertThat("{     \n\r    }", is(emptyJson()));
    }

    @Test(expected = AssertionError.class)
    public void fails_if_json_not_empty() {
        assertThat("{" +
                "\"key1\":\"value1\"," +
                "\"key2\":\"value2\"" +
                "}", is(emptyJson()));
    }
}
