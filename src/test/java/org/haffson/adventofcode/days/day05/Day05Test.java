package org.haffson.adventofcode.days.day05;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day05Test {

    // puzzle 5.1 real data:
    // test method firstPart()
    @Test
    public void test_firstPart() {
        Day05 day05 = new Day05("input_day05.txt");
        String expected = "The highest seatID is: " + 930;
        String actual = day05.firstPart();
        Assert.assertEquals(expected, actual);
    }

    // puzzle 5.2 real data:
    // test method secondPart()
    @Test
    public void test_secondPart() {
        Day05 day05 = new Day05("input_day05.txt");
        String expected = "My seatID is: " + 515;
        String actual = day05.secondPart();
        Assert.assertEquals(expected, actual);
    }
}