package com.firone.hamcrest.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.io.IOException;

public class JsonHasKeyWithValueMatcher extends TypeSafeMatcher<String> {

    private ObjectMapper objectMapper = new ObjectMapper();
    private String key;
    private String value;

    public JsonHasKeyWithValueMatcher(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    protected boolean matchesSafely(String jsonString) {
        try {
            JsonNode jsonNode = objectMapper.readTree(jsonString);
            String[] keyParts = key.split("\\.");

            for (String keyPart : keyParts) {
                if (jsonNode == null) {
                    return false;
                }
                jsonNode = jsonNode.get(keyPart);
            }
            return jsonNode == null ? value == null : jsonNode.asText().equals(value);
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("json to contains key [" + key + "] with value [" + value + "]");
    }

    public static Matcher<String> jsonHasKeyWithValue(String key, String value) {
        return new JsonHasKeyWithValueMatcher(key, value);
    }
}
