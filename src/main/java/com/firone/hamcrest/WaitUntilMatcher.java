package com.firone.hamcrest;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * This class was inspired by internal classes of Testatoo : https://github.com/Ovea/testatoo
 */
public class WaitUntilMatcher {

    protected WaitUntilMatcher() {
    }

    public static <T> void waitUntilObject(final T object, org.hamcrest.Matcher<? super T> matcher) throws InterruptedException {
        waitUntilObject(object, matcher, max(500, MILLISECONDS));
    }

    public static <T> void waitUntilObject(final T object, org.hamcrest.Matcher<? super T> matcher, long duration) throws InterruptedException {
        waitUntil(wrapWithCallable(object), matcher, duration, freq(100, MILLISECONDS));
    }

    public static <T> void waitUntil(Callable<T> callable, org.hamcrest.Matcher<? super T> matcher) throws InterruptedException {
        waitUntil(callable, matcher, max(500, MILLISECONDS));
    }

    public static <T> void waitUntil(Callable<T> callable, org.hamcrest.Matcher<? super T> matcher, long duration) throws InterruptedException {
        waitUntil(callable, matcher, duration, freq(100, MILLISECONDS));
    }

    public static <T> void waitUntil(Callable<T> callable, org.hamcrest.Matcher<? super T> matcher, long duration, long frequency) throws InterruptedException {
        Throwable ex = null;
        for (long timeout = duration; timeout > 0; timeout -= frequency, Thread.sleep(frequency)) {
            try {
                assertThat(callable.call(), matcher);
                return;
            } catch (Throwable e) {
                ex = e;
            }
        }
        throw new RuntimeException("Unable to reach the condition in " + duration + " ms", ex);
    }

    public static long max(long duration, TimeUnit unit) {
        return unit.toMillis(duration);
    }

    public static long freq(long duration, TimeUnit unit) {
        return unit.toMillis(duration);
    }

    private static <T> Callable<T> wrapWithCallable(final T object) {
        return () -> object;
    }
}
