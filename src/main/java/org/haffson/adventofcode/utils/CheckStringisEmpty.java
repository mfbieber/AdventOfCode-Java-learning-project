package org.haffson.adventofcode.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class CheckStringisEmpty {

    private CheckStringisEmpty() {

    }

    public static String requireNonNullAndNonEmpty(String string) {
        if (StringUtils.isEmpty(string)) {
            throw new IllegalArgumentException("The string is null or empty");
        } else {
            return string;
        }
    }
}
