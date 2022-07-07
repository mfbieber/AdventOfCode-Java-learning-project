package org.haffson.adventofcode.days.day01;

import org.haffson.adventofcode.utils.FileReaders;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day01Test {

    @MockBean
    private FileReaders fileReaders;

    @Test
    void testGetDay() {
        final Day01 day01 = new Day01(fileReaders);
        final int expectedResult = 1;
        final int actualResult = day01.getDay();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void test_firstPart_returnsExpectedResult() {
        //arrange
        final Day01 day01 = new Day01(fileReaders);

        final String expectedResult = "Part 1 - Frequency: " + 0;

        //act
        final String actualResult = day01.firstPart();

        //assert
        assertEquals(expectedResult, actualResult);
    }
}
