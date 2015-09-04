package com.firone.hamcrest;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.util.Map;

public class ContainsAtLeastMatcher<K, V> extends TypeSafeMatcher<Map<K, V>> {

    private K wantedKey;
    private V wantedValue;

    private ContainsAtLeastMatcher(K wantedKey, V wantedValue) {
        this.wantedKey = wantedKey;
        this.wantedValue = wantedValue;
    }

    @Override
    protected boolean matchesSafely(Map<K, V> map) {
        try {
            return map.get(wantedKey).equals(wantedValue);
        } catch (NullPointerException e) {
            return false;
        }
    }

    @Override
    public void describeTo(Description description) {
        description.appendText(String.format("a map containing at least entry [%s=%s]", wantedKey, wantedValue));
    }

    public static <K, V> TypeSafeMatcher<Map<K, V>> containsAtLeastEntry(final K wantedKey, final V wantedValue) {
        return new ContainsAtLeastMatcher<>(wantedKey, wantedValue);
    }
}
