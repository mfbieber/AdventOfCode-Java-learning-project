package org.haffson.adventofcode.days.day03;

import org.haffson.adventofcode.utils.DataLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class Day03Test {

    DataLoader dataLoader = new DataLoader();
    List<String> grid = new ArrayList<>(List.of("..##.......", "#...#...#..", ".#....#..#.",
            "..#.#...#.#", ".#...##..#.", "..#.##.....", ".#.#.#....#", ".#........#",
            "#.##...#...", "#...##....#", ".#..#...#.#"));

    @BeforeEach
    void setup() {
        dataLoader = mock(DataLoader.class);
    }

    // Test getDay()
    @Test
    public void testGetDay() {
        when(dataLoader.getDataDay03()).thenReturn(grid);
        Day03 day03 = new Day03(dataLoader);
        int expectedDay = 3;
        int actualDay = day03.getDay();
        assertEquals(expectedDay, actualDay);
    }

    // Test getTestData()
    @Test
    public void testGetTestData() {
        when(dataLoader.getDataDay03()).thenReturn(grid);
        Day03 day03 = new Day03(dataLoader);
        List<String> grid = day03.getGrid();
        String expectedSquare = ".";
        String actualSquare = Character.toString(grid.get(0).charAt(0));
        assertEquals(expectedSquare, actualSquare);
    }

    // puzzle day03 part 1 Test getNumberTrees()
    @Test
    public void testGetNumberTreesRealData() {
        when(dataLoader.getDataDay03()).thenReturn(grid);
        Day03 day03 = new Day03(dataLoader);
        String expectedTrees = "Trees encountered: " + 7;
        String actualTrees = day03.firstPart();
        assertEquals(expectedTrees, actualTrees);
    }

    // puzzle day03 part 2 Test getProduct()
    @Test
    public void testGetProduct_realData() {
        when(dataLoader.getDataDay03()).thenReturn(grid);
        Day03 day03 = new Day03(dataLoader);
        String expectedTrees = "Product of all slopes: " + "336";
        String actualTrees = day03.secondPart();
        assertEquals(expectedTrees, actualTrees);
    }
}