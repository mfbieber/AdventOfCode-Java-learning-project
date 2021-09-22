package org.haffson.adventofcode.days.day01;

import org.haffson.adventofcode.ProblemStatusEnum;
import org.haffson.adventofcode.utils.DataLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;


public class Day01Test {

    DataLoader dataLoader;
    // @Matthias: I think this is Java 8 the command List.of() does not exist, yet.
    List<Integer> testNumbers = new ArrayList<>(Arrays.asList(1721, 979, 366, 299, 675, 1456));

    @BeforeEach
    void setup() {
        dataLoader = mock(DataLoader.class);
    }

    @Test
    public void test_getProblemStatus() {
        when(dataLoader.getDataDay01(anyString(), eq("\n"))).thenReturn(testNumbers);
        Day01 day01 = new Day01("input_day01.txt", dataLoader);
        Map<Integer, ProblemStatusEnum> expectedResult = new HashMap<Integer, ProblemStatusEnum>() {{
            put(1, ProblemStatusEnum.SOLVED);
            put(2, ProblemStatusEnum.SOLVED);
        }};
        Map<Integer, ProblemStatusEnum> actualResult = day01.getProblemStatus();
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    public void testGetDay() {
        when(dataLoader.getDataDay01(anyString(), eq("\n"))).thenReturn(testNumbers);
        Day01 day01 = new Day01("input_day01.txt", dataLoader);
        int expectedResult = 1;
        int actualResult = day01.getDay();
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    public void test_rawDataNotEmpty() {
        //arrange
        when(dataLoader.getDataDay01(anyString(), eq("\n"))).thenReturn(testNumbers);
        Day01 day01 = new Day01("input_day01.txt", dataLoader);
        int expectedSize = 6;
        //act
        List<Integer> actual = day01.getNumbers();
        //assert
        verify(dataLoader, times(1)).getDataDay01(anyString(), anyString());
        assertThat(actual).hasSize(expectedSize);
    }

    @Test
    public void test_firstPart_returnsExpectedResult() {
        //arrange
        when(dataLoader.getDataDay01(anyString(), eq("\n"))).thenReturn(testNumbers);
        Day01 day01 = new Day01("input_day01.txt", dataLoader);
        String expectedResult = "Product 1: " + 514579;
        //act
        String actualResult = day01.firstPart();
        //assert
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    public void test_secondPart_returnsExpectedResult() {
        //arrange
        when(dataLoader.getDataDay01(anyString(), eq("\n"))).thenReturn(testNumbers);
        Day01 day01 = new Day01("input_day01.txt", dataLoader);
        String expectedResult = "Product 2: " + 241861950;
        //act
        String actualResult = day01.secondPart();
        //assert
        assertThat(actualResult).isEqualTo(expectedResult);
    }
}