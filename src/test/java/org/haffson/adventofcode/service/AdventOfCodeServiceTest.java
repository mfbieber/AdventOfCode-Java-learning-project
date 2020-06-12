package org.haffson.adventofcode.service;

import org.haffson.adventofcode.ProblemStatusEnum;
import org.haffson.adventofcode.days.Days;
import org.haffson.adventofcode.days.day01.Day01;
import org.haffson.adventofcode.exceptions.PuzzleNotSolvedYetException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@RunWith(SpringRunner.class)
public class AdventOfCodeServiceTest {

    private final List<Days> daysSolutions = new LinkedList<>();
    private HashMap<String, ProblemStatusEnum> problemStatus = new HashMap<>();

    private AdventOfCodeService adventOfCodeService;


    @MockBean
    private Day01 day01;

    @Before
    public void setup() {
        problemStatus.put("1", ProblemStatusEnum.SOLVED);
        problemStatus.put("2", ProblemStatusEnum.UNSOLVED);
        daysSolutions.add(day01);
        Mockito.when(day01.getDay()).thenReturn(1);
        Mockito.when(day01.getProblemStatus()).thenReturn(problemStatus);
        Mockito.when(day01.firstPart()).thenReturn("Part 1 - Frequency: 599");
        adventOfCodeService = new AdventOfCodeService(daysSolutions);
    }

    @Test
    public void getResultsForASpecificDayAndPuzzlePartTest() {
        String actualResult = adventOfCodeService.getResultsForASpecificDayAndPuzzlePart("1", "1");

        Assert.assertEquals("Part 1 - Frequency: 599", actualResult);
    }

    @Test(expected = PuzzleNotSolvedYetException.class)
    public void tryingToGetResultsForANotYetImplementedPartThrowsExceptionTest() {
        adventOfCodeService.getResultsForASpecificDayAndPuzzlePart("1", "2");
    }

    @Test(expected = PuzzleNotSolvedYetException.class)
    public void tryingToGetResultsForANotYetImplementedDayThrowsException() {
        adventOfCodeService.getResultsForASpecificDayAndPuzzlePart("2", "1");
    }

    @Test(expected = PuzzleNotSolvedYetException.class)
    public void tryingToGetResultsForAnyOtherPartThrowsException() {
        adventOfCodeService.getResultsForASpecificDayAndPuzzlePart("1", "3");
    }

}