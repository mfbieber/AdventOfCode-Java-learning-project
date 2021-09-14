package org.haffson.adventofcode.days.day04;

import org.junit.Assert;
import org.junit.Test;

public class Day04Test {

    // test output of method
    @Test
    public void test_getRawDataArray1() {
        Day04 day04 = new Day04();
        String expected = "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd\n" +
                "byr:1937 iyr:2017 cid:147 hgt:183cm";
        String actual = day04.getRawData(day04.testResource)[0];
        Assert.assertEquals(actual, expected);
    }

    // test length of output of method
    @Test
    public void test_getRawDataArray2() {
        Day04 day04 = new Day04();
        Integer expected = 4;
        Integer actual = day04.getRawData(day04.testResource).length;
        Assert.assertEquals(actual, expected);
    }

    // test number of valid passports of test data
    @Test
    public void test_getNumberValidPassports() {
        Day04 day04 = new Day04();
        int expected = 2;
        int actual = day04.getNumberValidPassports(day04.getRawData(day04.testResource));
        Assert.assertEquals(actual, expected);
    }

    // test number of valid passports of input data
    @Test
    public void test_getNumberValidPassports1() {
        Day04 day04 = new Day04();
        int expected = 250;
        int actual = day04.getNumberValidPassports(day04.getRawData(day04.resource));
        Assert.assertEquals(actual, expected);
    }

    // puzzle 2 test number of valid passports of input data (stricter rules)
    @Test
    public void test_getRestrictedNumberValidPassports() {
        Day04 day04 = new Day04();
        int expected = 158;
        int actual = day04.getRestrictedNumberValidPassports(day04.getRawData(day04.resource));
        Assert.assertEquals(actual, expected);
    }
}