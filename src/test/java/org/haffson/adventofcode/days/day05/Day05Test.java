package org.haffson.adventofcode.days.day05;

import org.haffson.adventofcode.days.day04.Day04;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class Day05Test {


    // Test return type
    @Test
    public void test_getRawDataArray1() {
        Day05 day05 = new Day05();
        ArrayList<String> expected = new ArrayList<String>(
                Arrays.asList("BFFFBBFRRR", "FFFBBBFRRR", "BBFFBBFRLL"));
        ArrayList<String> actual = day05.getRawdataAsList(day05.testDataInputStream);
        Assert.assertEquals(expected, actual);
    }

    // puzzle 5.1 real data:
    // test method firstPart()
    @Test
    public void test_firstPart() {
        Day05 day05 = new Day05();
        String expected = "The highestSeatID is: " + 930;
        String actual = day05.firstPart();
        Assert.assertEquals(expected, actual);
    }

    // test method firstPart()
    @Test
    public void test_secondPart() {
        Day05 day05 = new Day05();
        String expected = "My seatID is: " + 515;
        String actual = day05.secondPart();
        Assert.assertEquals(expected, actual);
    }

}
