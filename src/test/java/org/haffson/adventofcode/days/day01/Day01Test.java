package org.haffson.adventofcode.days.day01;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class Day01Test {


    @Test
    public void testGetDay() {
        Day01 day01 = new Day01();
        int expectedResult = 1;
        int actualResult = day01.getDay();
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void test_firstPart_returnsExpectedResult() {
        //arrange
        Day01 day01 = new Day01();

        String expectedResult = "Product 1: " + 326211;

        //act
        String actualResult = day01.firstPart();

        //assert
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void test_secondPart_returnsExpectedResult() {
        //arrange
        Day01 day01 = new Day01();

        String expectedResult = "Product 2: " + 131347190;

        //act
        String actualResult = day01.secondPart();

        //assert
        Assert.assertEquals(expectedResult, actualResult);
    }

}
