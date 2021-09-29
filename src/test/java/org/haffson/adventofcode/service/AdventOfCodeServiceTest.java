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

import static org.assertj.core.api.Assertions.assertThat;

public class AdventOfCodeServiceTest {

    private final List<Days> daysSolutions = new LinkedList<>();
    private HashMap<Integer, ProblemStatusEnum> problemStatus = new HashMap<>();

    private AdventOfCodeService adventOfCodeService;

    @BeforeEach
    public void setup() {
        Day01 day01 = Mockito.mock(Day01.class);
        problemStatus.put(1, ProblemStatusEnum.SOLVED);
        problemStatus.put(2, ProblemStatusEnum.UNSOLVED);
        daysSolutions.add(day01);
        Mockito.when(day01.getDay()).thenReturn(1);
        Mockito.when(day01.getProblemStatus()).thenReturn(problemStatus);
        Mockito.when(day01.firstPart()).thenReturn("Product 1: " + 326211);
        adventOfCodeService = new AdventOfCodeService(daysSolutions);
    }

    @Test
    public void getResultsForASpecificDayAndPuzzlePartTest() {
        String actualResult = adventOfCodeService.getResultsForASpecificDayAndPuzzlePart(1, 1);
        assertThat(actualResult).isEqualTo("Product 1: " + 326211);
    }

    @Test
    public void tryingToGetResultsForANotYetImplementedPartThrowsExceptionTest() throws Exception{
        Assertions.assertThrows(PuzzleNotSolvedYetException.class, () -> {
        adventOfCodeService.getResultsForASpecificDayAndPuzzlePart(1, 2);
        });
    }

    @Test
    public void tryingToGetResultsForANotYetImplementedDayThrowsException() throws Exception {
        Assertions.assertThrows(PuzzleNotSolvedYetException.class, () -> {
            adventOfCodeService.getResultsForASpecificDayAndPuzzlePart(10, 1);
        });
    }

    @Test
    public void tryingToGetResultsForAnyOtherPartThrowsException() throws Exception {
        Assertions.assertThrows(PuzzleNotSolvedYetException.class, () -> {
            adventOfCodeService.getResultsForASpecificDayAndPuzzlePart(2, 3);
        });
    }
}