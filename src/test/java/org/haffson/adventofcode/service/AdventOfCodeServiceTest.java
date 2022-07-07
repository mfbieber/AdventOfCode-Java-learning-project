package org.haffson.adventofcode.service;

import org.haffson.adventofcode.ProblemStatusEnum;
import org.haffson.adventofcode.days.Days;
import org.haffson.adventofcode.days.day01.Day01;
import org.haffson.adventofcode.exceptions.PuzzleNotSolvedYetException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AdventOfCodeServiceTest {

    private final List<Days> daysSolutions = new LinkedList<>();

    private final HashMap<Integer, ProblemStatusEnum> problemStatus = new HashMap<>();

    private AdventOfCodeService adventOfCodeService;

    @BeforeEach
    public void setup() {
        final Day01 day01 = Mockito.mock(Day01.class);
        problemStatus.put(1, ProblemStatusEnum.SOLVED);
        problemStatus.put(2, ProblemStatusEnum.UNSOLVED);
        daysSolutions.add(day01);
        Mockito.when(day01.getDay()).thenReturn(1);
        Mockito.when(day01.getProblemStatus()).thenReturn(problemStatus);
        Mockito.when(day01.firstPart()).thenReturn("Part 1 - Frequency: 599");
        adventOfCodeService = new AdventOfCodeService(daysSolutions);
    }

    @Test
    void getResultsForASpecificDayAndPuzzlePartTest() {
        final String actualResult = adventOfCodeService.getResultsForASpecificDayAndPuzzlePart(1, 1);
        assertEquals("Part 1 - Frequency: 599", actualResult);
    }

    @Test
    void tryingToGetResultsForANotYetImplementedPartThrowsExceptionTest() {
        Assertions.assertThrows(PuzzleNotSolvedYetException.class, () ->
                adventOfCodeService.getResultsForASpecificDayAndPuzzlePart(1, 2));
    }

    @Test
    void tryingToGetResultsForANotYetImplementedDayThrowsException() {
        Assertions.assertThrows(PuzzleNotSolvedYetException.class, () ->
                adventOfCodeService.getResultsForASpecificDayAndPuzzlePart(10, 1));
    }

    @Test
    void tryingToGetResultsForAnyOtherPartThrowsException() {
        Assertions.assertThrows(PuzzleNotSolvedYetException.class, () ->
                adventOfCodeService.getResultsForASpecificDayAndPuzzlePart(2, 3));
    }
}
