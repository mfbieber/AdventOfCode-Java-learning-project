package org.haffson.adventofcode.days.day02;

import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

//@RunWith(SpringRunner.class)
public class Day02Test {

    @Test
    public void testGetDay() {
        Day02 day02 = new Day02("input_day02.txt");
        int expectedResult = 2;
        int actualResult = day02.getDay();
        Assert.assertEquals(expectedResult, actualResult);
    }

    // First part test data
    @Test
    public void test_testData_firstPart_returnsExpectedResult() {
        // arrange
        Day02 day02 = new Day02("day02_testdata.txt");
        String expectedResult = "Part 1 answer: " + 2;
        //act
        String actualResult = day02.firstPart();
        //assert
        Assert.assertEquals(expectedResult, actualResult);
    }

    // First part raw data from file (real puzzle data)
    @Test
    public void test_firstPart_returnsExpectedResult() {
        //arrange
        Day02 day02 = new Day02("input_day02.txt");
        String expectedResult = "Part 1 answer: " + 607;
        //act
        String actualResult = day02.firstPart();
        //assert
        Assert.assertEquals(expectedResult, actualResult);
    }

    // Second part test data
    @Test
    public void test_testData_secondPart_returnsExpectedResult() {
        //arrange
        Day02 day02 = new Day02("day02_testdata.txt");
//        day02.addInput(rawData);
        String expectedResult = "Part 2 answer: " + 1;
        //act
        String actualResult = day02.secondPart();
        //assert
        Assert.assertEquals(expectedResult, actualResult);
    }

    // Second part raw data from file (real puzzle data)
    @Test
    public void test_secondPart_returnsExpectedResult() {
        //arrange
        Day02 day02 = new Day02("input_day02.txt");
        String expectedResult = "Part 2 answer: " + 321;
        //act
        String actualResult = day02.secondPart();
        //assert
        Assert.assertEquals(expectedResult, actualResult);
    }
}