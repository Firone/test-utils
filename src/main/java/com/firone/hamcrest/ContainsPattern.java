package com.firone.hamcrest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.util.regex.Pattern;

public class ContainsPattern extends TypeSafeMatcher<CharSequence> {

    private final Pattern pattern;
    private final boolean match;

    public ContainsPattern(Pattern pattern, boolean match) {
        super(String.class);
        this.pattern = pattern;
        this.match = match;
    }

    public ContainsPattern(String regex, boolean match) {
        this(Pattern.compile(regex), match);
    }

    @Override
    public void describeTo(Description description) {
        description
                .appendText("a string ")
                .appendText(match ? "matching" : "containing")
                .appendText(" #")
                .appendText(pattern.pattern())
                .appendText("#");
    }

    @Override
    protected boolean matchesSafely(CharSequence item) {
        if (match) {
            return pattern.matcher(item).matches();
        } else {
            return pattern.matcher(item).find();
        }
    }

    @Override
    protected void describeMismatchSafely(CharSequence item, Description mismatch) {
        mismatch
                .appendValue(item)
                .appendText(" did not ")
                .appendText(match ? "match" : "contain")
                .appendText(" /")
                .appendText(pattern.pattern())
                .appendText("/");
    }

    public static Matcher<CharSequence> containsPattern(String regex) {
        return new ContainsPattern(regex, false);
    }

    public static Matcher<CharSequence> containsPattern(Pattern pattern) {
        return new ContainsPattern(pattern, false);
    }

    public static Matcher<CharSequence> matchesPattern(String regex) {
        return new ContainsPattern(regex, true);
    }

    public static Matcher<CharSequence> matchesPattern(Pattern pattern) {
        return new ContainsPattern(pattern, true);
    }
}
