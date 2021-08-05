package org.haffson.adventofcode.days.day02;

import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

//@RunWith(SpringRunner.class)
public class Day02Test {

    @Test
    public void testGetDay() {
        Day02 day02 = new Day02();
        int expectedResult = 2;
        int actualResult = day02.getDay();
        Assert.assertEquals(expectedResult, actualResult);
    }


    // First part test data
    @Test
    public void test_firstPart_returnsExpectedResult() {

        List<String> rawData = new ArrayList<>();
        rawData.add("1-3 a: abcde");
        rawData.add("1-3 b: cdefg");
        rawData.add("2-9 c: ccccccccc");

        //arrange
        Day02 day02 = new Day02();
        day02.addInput(rawData);

        String expectedResult =  "Part 1 answer: " + 2;

        //act
        String actualResult = day02.firstPart();

        //assert
        Assert.assertEquals(expectedResult, actualResult);
    }


    // First part raw data from file (real puzzle data)
    @Test
    public void test_firstPart_puzzleData_returnsExpectedResult() {

        //arrange
        Day02 day02 = new Day02();
        String expectedResult = "Part 1 answer: " + 607;

        //act
        String actualResult = day02.firstPart();

        //assert
        Assert.assertEquals(expectedResult, actualResult);
    }


    // Second part test data
    @Test
    public void test_secondPart_returnsExpectedResult() {

        List<String> rawData = new ArrayList<>();
        rawData.add("1-3 a: abcde");
        rawData.add("1-3 b: cdefg");
        rawData.add("2-9 c: ccccccccc");

        //arrange
        Day02 day02 = new Day02();
        day02.addInput(rawData);

        String expectedResult = "Part 2 answer: " + 1;

        //act
        String actualResult = day02.secondPart();

        //assert
        Assert.assertEquals(expectedResult, actualResult);
    }


    // Second part raw data from file (real puzzle data)
    @Test
    public void test_secondPart_puzzleData_returnsExpectedResult() {

        //arrange
        Day02 day02 = new Day02();

        String expectedResult = "Part 2 answer: " + 321;

        //act
        String actualResult = day02.secondPart();

        //assert
        Assert.assertEquals(expectedResult, actualResult);
    }

}
