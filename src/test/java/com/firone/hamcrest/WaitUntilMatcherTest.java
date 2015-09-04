package com.firone.hamcrest;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.Callable;

import static com.firone.hamcrest.WaitUntilMatcher.freq;
import static com.firone.hamcrest.WaitUntilMatcher.max;
import static com.firone.hamcrest.WaitUntilMatcher.waitUntil;
import static com.firone.hamcrest.WaitUntilMatcher.waitUntilObject;
import static java.lang.System.currentTimeMillis;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class WaitUntilMatcherTest {

    private Callable<Boolean> callable;

    @Before
    public void setUp() throws Exception {

        final long startTime = currentTimeMillis();
        callable = () -> currentTimeMillis() - startTime > 50;

    }

    @Test
    public void can_wait_until_assertion_with_default_params() throws Exception {

        waitUntil(callable, is(true));

    }

    @Test
    public void can_wait_until_assertion() throws Exception {

        waitUntil(callable, is(true), max(70, MILLISECONDS), freq(10, MILLISECONDS));

    }

    @Test(expected = RuntimeException.class)
    public void stop_test_after_timeout() throws Exception {

        waitUntil(callable, is(true), max(30, MILLISECONDS), freq(10, MILLISECONDS));

    }

    @Test
    public void can_call_matcher_without_callable() throws InterruptedException {

        waitUntilObject(currentTimeMillis(), isGoingBy(100));

    }

    @Test
    public void verify_there_is_not_ambiguous_method_call() throws InterruptedException {

        waitUntil(callable, is(notNullValue()));

    }

    private TypeSafeMatcher<Long> isGoingBy(final long delayed) {
        return new TypeSafeMatcher<Long>() {

            private long delay = delayed;

            @Override
            protected boolean matchesSafely(Long time) {
                return currentTimeMillis() - delay > time;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("time passed by " + delay + "ms from now");
            }
        };
    }
}
