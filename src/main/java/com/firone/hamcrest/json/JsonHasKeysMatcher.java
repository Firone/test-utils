package com.firone.hamcrest.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.Arrays.asList;

public class JsonHasKeysMatcher extends TypeSafeMatcher<String> {

    private ObjectMapper objectMapper = new ObjectMapper();
    private List<String> wantedKeys;

    public JsonHasKeysMatcher(List<String> wantedKeys) {
        if (wantedKeys == null) {
            throw new IllegalArgumentException("expected keys must not be null");
        }
        this.wantedKeys = wantedKeys;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected boolean matchesSafely(String json) {
        try {
            Map<String, Object> jsonMap = objectMapper.readValue(json, Map.class);
            Set<String> keys = jsonMap.keySet();
            return keys.size() == wantedKeys.size() && keys.containsAll(wantedKeys);
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("json to contains keys [" + wantedKeys + "]");
    }

    public static Matcher<String> jsonContainsKeys(String... keys) {
        return new JsonHasKeysMatcher(asList(keys));
    }
}
