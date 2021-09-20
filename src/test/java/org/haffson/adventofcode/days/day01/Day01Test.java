package org.haffson.adventofcode.days.day01;

import org.haffson.adventofcode.ProblemStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;


@RunWith(SpringRunner.class)
public class Day01Test {

    @Test
    public void test_getProblemStatus() {
        Day01 day01 = new Day01("input_day01.txt");
        Map<Integer, ProblemStatusEnum> expectedResult = new HashMap<Integer, ProblemStatusEnum>() {{
            put(1, ProblemStatusEnum.SOLVED);
            put(2, ProblemStatusEnum.SOLVED);
        }};
        Map<Integer, ProblemStatusEnum> actualResult = day01.getProblemStatus();
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testGetDay() {
        Day01 day01 = new Day01("input_day01.txt");
        int expectedResult = 1;
        int actualResult = day01.getDay();
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void test_rawDataNotEmpty() {
        //arrange
        Day01 day01 = new Day01("input_day01.txt");
        Integer expectedSize = 200;
        //act
        Integer actualSize = day01.getNumbers().size();
        //assert
        Assert.assertEquals(expectedSize, actualSize);
    }

    @Test
    public void test_firstPart_returnsExpectedResult() {
        //arrange
        Day01 day01 = new Day01("input_day01.txt");
        String expectedResult = "Product 1: " + 326211;
        //act
        String actualResult = day01.firstPart();
        //assert
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void test_secondPart_returnsExpectedResult() {
        //arrange
        Day01 day01 = new Day01("input_day01.txt");
        String expectedResult = "Product 2: " + 131347190;
        //act
        String actualResult = day01.secondPart();
        //assert
        Assert.assertEquals(expectedResult, actualResult);
    }
}