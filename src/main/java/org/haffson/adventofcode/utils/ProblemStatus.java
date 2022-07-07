package org.haffson.adventofcode.utils;

import org.haffson.adventofcode.ProblemStatusEnum;

import java.util.HashMap;
import java.util.Map;

public class ProblemStatus {

    private ProblemStatus() {
    }

    public static Map<Integer, ProblemStatusEnum> getProblemStatusMap(
            final int part1,
            final int part2,
            final ProblemStatusEnum status1,
            final ProblemStatusEnum status2
    ) {
        final Map<Integer, ProblemStatusEnum> problemStatus = new HashMap<>();

        return Map.of(
                part1, status1,
                part2, status2
        );
    }
}