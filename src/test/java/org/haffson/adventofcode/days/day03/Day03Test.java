package org.haffson.adventofcode.days.day03;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;


//@RunWith(SpringRunner.class)
public class Day03Test {

    // Test getDay()
    @Test
    public void testGetDay() {
        Day03 day03 = new Day03("input_day03.txt");
        int expectedDay = 3;
        int actualDay = day03.getDay();
        Assert.assertEquals(expectedDay, actualDay);
    }

    // Test getTestData()
    @Test
    public void testGetTestData() {
        Day03 day03 = new Day03("day03_testdata.txt");
        List<String> grid = day03.getGrid();
        String expectedSquare = ".";
        String actualSquare = Character.toString(grid.get(0).charAt(0));
        Assert.assertEquals(expectedSquare, actualSquare);
    }

    // Test getNumberTrees() with testData
    @Test
    public void testGetNumberTrees() {
        Day03 day03 = new Day03("day03_testdata.txt");
        String expectedTrees = "Trees encountered: " + 7;
        String actualTrees = day03.firstPart();
        Assert.assertEquals(expectedTrees, actualTrees);
    }

    // Test getNumberTrees() with real data (input_day03.txt)
    @Test
    public void testGetNumberTreesRealData() {
        Day03 day03 = new Day03("input_day03.txt");
        String expectedTrees = "Trees encountered: " + 159;
        String actualTrees = day03.firstPart();
        Assert.assertEquals(expectedTrees, actualTrees);
    }

    // puzzle day03 part 2
    // Test getProduct() with test data
    @Test
    public void testGetProduct() {
        Day03 day03 = new Day03("day03_testdata.txt");
        String expectedTrees = "Product of all slopes: " + 336;
        String actualTrees = day03.secondPart();
        Assert.assertEquals(expectedTrees, actualTrees);
    }

    // puzzle day03 part 2
    // Test getProduct() with real data
    @Test
    public void testGetProduct_realData() {
        Day03 day03 = new Day03("input_day03.txt");
        String expectedTrees = "Product of all slopes: " + "6419669520";
        String actualTrees = day03.secondPart();
        Assert.assertEquals(expectedTrees, actualTrees);
    }
}