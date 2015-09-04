package com.firone.hamcrest;

import org.junit.Test;

import static com.firone.hamcrest.ContainsPattern.containsPattern;
import static com.firone.hamcrest.ContainsPattern.matchesPattern;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContainsPatternTest {

    @Test
    public void can_assert_contains_pattern() {

        String toVerify = "containsThreeNumbers895";

        assertThat(toVerify, containsPattern("\\d{3}"));
    }

    @Test(expected = AssertionError.class)
    public void error_when_string_not_contains_pattern() {

        String toVerify = "containsThreeNumbers";

        assertThat(toVerify, containsPattern("\\d{3}"));
    }

    @Test
    public void can_assert_match_pattern() {

        String toVerify = "endsWithThreeNumbers895";

        assertThat(toVerify, matchesPattern("\\w+\\d{3}"));
    }

    @Test(expected = AssertionError.class)
    public void error_when_string_not_match_pattern() {

        String toVerify = "endsWithThreeNumbers895";

        assertThat(toVerify, matchesPattern("\\d{3}"));
    }
}
