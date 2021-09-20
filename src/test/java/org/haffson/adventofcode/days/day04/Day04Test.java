package org.haffson.adventofcode.days.day04;

import org.junit.Assert;
import org.junit.Test;

public class Day04Test {

    // test length of output of method
    @Test
    public void test_getRawDataArray2() {
        Day04 day04 = new Day04("day04_testdata.txt");
        Integer expected = 4;
        Integer actual = day04.getBatchFile().size();
        Assert.assertEquals(expected, actual);
    }

    // test number of valid passports of test data
    @Test
    public void test_getNumberValidPassports() {
        Day04 day04 = new Day04("day04_testdata.txt");
        String expected = "Number of valid passports: " + 2;
        String actual = day04.firstPart();
        Assert.assertEquals(expected, actual);
    }

    // test number of valid passports of input data
    @Test
    public void test_getNumberValidPassports1() {
        Day04 day04 = new Day04("input_day04.txt");
        String expected = "Number of valid passports: " + 250;
        String actual = day04.firstPart();
        Assert.assertEquals(expected, actual);
    }

    // puzzle 2 test number of valid passports of input data (stricter rules)
    @Test
    public void test_getRestrictedNumberValidPassports() {
        Day04 day04 = new Day04("input_day04.txt");
        String expected = "Number of valid passports: " + 158;
        String actual = day04.secondPart();
        Assert.assertEquals(expected, actual);
    }
}