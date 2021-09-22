package org.haffson.adventofcode.days.day05;

import org.haffson.adventofcode.utils.DataLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class Day05Test {

    DataLoader dataLoader = new DataLoader();
    List<String> boardingPassesPart1 = new ArrayList<>(Arrays.asList("BFFFBBFRRR", "FFFBBBFRRR", "BBFFBBFRLL"));
    List<String> boardingPassesPart2 = new DataLoader().getDataDay05("/day05/input_day05.txt", "\n");

    @BeforeEach
    void setup() {
        dataLoader = mock(DataLoader.class);
    }

    // puzzle 5.1 test method firstPart()
    @Test
    public void test_firstPart() {
        when(dataLoader.getDataDay05(anyString(), anyString())).thenReturn(boardingPassesPart1);
        Day05 day05 = new Day05("input_day05.txt", dataLoader);
        String expected = "The highest seatID is: " + 820;
        String actual = day05.firstPart();
        assertThat(actual).isEqualTo(expected);
    }

    // @Daniel, @Matthias I tried to avoid to instantiate a DataLoader Object but for this method I only have a
    // huge dataset and no test data set (designing test data was too time consuming), so I decided to do testing with DataLoader Object
    // puzzle 5.2 test method secondPart()
    @Test
    public void test_secondPart() {
        when(dataLoader.getDataDay05(anyString(), anyString())).thenReturn(boardingPassesPart2);
        Day05 day05 = new Day05("input_day05.txt", dataLoader);
        String expected = "My seatID is: " + 515;
        String actual = day05.secondPart();
        assertThat(actual).isEqualTo(expected);
    }
}