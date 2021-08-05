package org.haffson.adventofcode.days.day03;

import org.junit.Assert;
import org.junit.Test;


//@RunWith(SpringRunner.class)
public class Day03Test {

    // Test getDay()
    @Test
    public void testGetDay() {
        Day03 day03 = new Day03();
        int actualDay = day03.getDay();
        int expectedDay = 3;
        Assert.assertEquals(actualDay, expectedDay);
    }

    // Test getTestData()
    @Test
    public void testGetTestData(){
        Day03 day03 = new Day03();
        String[] lines = day03.getTestData();
        String actualSquare = Character.toString(lines[0].charAt(0));
        String expectedSquare = ".";
        Assert.assertEquals(actualSquare, expectedSquare);
    }

    // Test getNumTrees() with testData
    @Test
    public void testGetNumTrees(){
        Day03 day03 = new Day03();
        String actualTrees = "Trees encountered: " + day03.getNumTrees(day03.getTestData());
        String expectedTrees = "Trees encountered: " + 7;
        Assert.assertEquals(actualTrees, expectedTrees);

    }

    // Test getNumTrees() with real data (input_day03.txt)
    @Test
    public void testGetNumTreesRealData(){
        Day03 day03 = new Day03();
        String actualTrees = day03.firstPart();
        String expectedTrees = "Trees encountered: " + 159;
        Assert.assertEquals(actualTrees, expectedTrees);

    }


    // puzzle day03 part 2
    // Test getProduct() with test data
    @Test
    public void testGetProduct(){
        Day03 day03 = new Day03();
        String actualTrees = "Product of all slopes: " + day03.getProduct(day03.getTestData());
        String expectedTrees = "Product of all slopes: " + 336;
        Assert.assertEquals(actualTrees, expectedTrees);

    }

    // puzzle day03 part 2
    // Test getProduct() with real data
    @Test
    public void testGetProduct_realData(){
        Day03 day03 = new Day03();
        String actualTrees = day03.secondPart();
        String expectedTrees = "Product of all slopes: " + "6419669520";
        Assert.assertEquals(actualTrees, expectedTrees);

    }
}
