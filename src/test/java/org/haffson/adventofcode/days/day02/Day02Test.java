package org.haffson.adventofcode.days.day02;

import org.haffson.adventofcode.utils.DataLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class Day02Test {

    DataLoader dataLoader = new DataLoader();
    List<String> passwordDatabase = new ArrayList<>(Arrays.asList("1-3 a: abcde", "1-3 b: cdefg", "2-9 c: ccccccccc"));

    @BeforeEach
    void setup() {
        dataLoader = mock(DataLoader.class);
    }

    @Test
    public void testGetDay() {
        when(dataLoader.getDataDay02()).thenReturn(passwordDatabase);
        Day02 day02 = new Day02(dataLoader);
        int expectedResult = 2;
        int actualResult = day02.getDay();
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    // First part
    @Test
    public void test_firstPart_returnsExpectedResult() {
        //arrange
        when(dataLoader.getDataDay02()).thenReturn(passwordDatabase);
        Day02 day02 = new Day02(dataLoader);
        String expectedResult = "Part 1 answer: " + 2;
        //act
        String actualResult = day02.firstPart();
        //assert
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    // Second part
    @Test
    public void test_secondPart_returnsExpectedResult() {
        //arrange
        when(dataLoader.getDataDay02()).thenReturn(passwordDatabase);
        Day02 day02 = new Day02(dataLoader);
        String expectedResult = "Part 2 answer: " + 1;
        //act
        String actualResult = day02.secondPart();
        //assert
        assertThat(actualResult).isEqualTo(expectedResult);
    }
}