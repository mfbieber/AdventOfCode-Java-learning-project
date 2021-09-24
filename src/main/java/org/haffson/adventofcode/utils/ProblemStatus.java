package org.haffson.adventofcode.utils;

import org.haffson.adventofcode.ProblemStatusEnum;

import java.util.HashMap;
import java.util.Map;

public class ProblemStatus {

    private ProblemStatus() {
    }

    public static Map<Integer, ProblemStatusEnum> getProblemStatusMap(int part1, int part2,
                                                                      ProblemStatusEnum status1, ProblemStatusEnum status2) {
        final Map<Integer, ProblemStatusEnum> problemStatus = new HashMap<>();

        problemStatus.put(part1, status1);
        problemStatus.put(part2, status2);
        return problemStatus;
    }
}