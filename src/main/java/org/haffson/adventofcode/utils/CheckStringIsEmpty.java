package org.haffson.adventofcode.utils;

import org.apache.commons.lang3.StringUtils;

public class CheckStringIsEmpty {

    private CheckStringIsEmpty() {

    }

    public static String requireNonNullAndNonEmpty(String string) {
        if (StringUtils.isEmpty(string)) {
            throw new IllegalArgumentException("The string is null or empty");
        }
        return string;
    }
}
