package org.haffson.adventofcode.days.day01;

import org.haffson.adventofcode.utils.FileReaders;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class Day01Test {

    @Autowired
    private FileReaders fileReaders;

    @Value("${day1.file}")
    private String filePath;

    public void testGetDay() {
        Day01 day01 = new Day01(fileReaders, filePath);
        int expectedResult = 1;
        int actualResult = day01.getDay();
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testFirstPartReturnsExpectedResult() {
        Day01 day01 = new Day01(fileReaders, filePath);
        String expectedResult = "Part 1: " + 514579;
        String actualResult = day01.firstPart();
        Assert.assertEquals(expectedResult, actualResult);
    }


    @Test
    public void testSecondPartReturnsExpectedResult() {
        Day01 day01 = new Day01(fileReaders, filePath);
        String expectedResult = "Part 2: " + 241861950;
        String actualResult = day01.secondPart();
        Assert.assertEquals(expectedResult, actualResult);
    }

}
