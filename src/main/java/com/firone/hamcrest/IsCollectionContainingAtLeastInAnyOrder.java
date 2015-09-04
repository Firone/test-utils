package com.firone.hamcrest;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class IsCollectionContainingAtLeastInAnyOrder<T> extends TypeSafeMatcher<Collection<T>> {

    private Collection<T> wanted;

    private IsCollectionContainingAtLeastInAnyOrder(Collection<T> wanted) {
        this.wanted = wanted;
    }

    @Override
    protected boolean matchesSafely(Collection<T> originalCollection) {
        return originalCollection.containsAll(wanted);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("at least this elements ").appendValue(wanted);
    }

    @SafeVarargs
    public static <T> IsCollectionContainingAtLeastInAnyOrder<T> containsAtLeastInAnyOrder(T... wanted) {

        List<T> list = new ArrayList<>(wanted.length);
        Collections.addAll(list, wanted);

        return new IsCollectionContainingAtLeastInAnyOrder<>(list);
    }
}
