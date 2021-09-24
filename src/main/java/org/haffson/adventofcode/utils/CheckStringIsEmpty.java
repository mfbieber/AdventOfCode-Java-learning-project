package org.haffson.adventofcode.utils;

import org.apache.commons.lang3.StringUtils;

public class CheckStringIsEmpty {

    private CheckStringIsEmpty() {
    }

    /*
    requireNonNullAndNonEmpty() checks if strings are null or empty
    and if true throws IllegalArgumentException.
    It helps crashing code "fail-fast" before code has already performed some side effects.
     */
    public static String requireNonNullAndNonEmpty(String string) {
        if (StringUtils.isEmpty(string)) {
            throw new IllegalArgumentException("The string is null or empty: " + string);
        }
        return string;
    }
}