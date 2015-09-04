package com.firone.hamcrest.json;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class EmptyJsonMatcher extends TypeSafeMatcher<String> {

    @Override
    protected boolean matchesSafely(String s) {
        return s.matches("\\{\\s*\\}");
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("json to be empty");
    }

    public static Matcher<String> emptyJson() {
        return new EmptyJsonMatcher();
    }
}
