package org.haffson.adventofcode.days.day04;

import org.haffson.adventofcode.utils.DataLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class Day04Test {

    DataLoader dataLoader = new DataLoader();
    //@Matthias: """ multiline is also not available in java 8
    List<String> batchFilePart1 = new ArrayList<>(Arrays
            .asList("ecl:gry pid:860033327 eyr:2020 hcl:#fffffd\n" +
                            "byr:1937 iyr:2017 cid:147 hgt:183cm\n" +
                            "\n",
                    "iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884\n" +
                            "hcl:#cfa07d byr:1929\n" +
                            "\n",
                    "hcl:#ae17e1 iyr:2013\n" +
                            "eyr:2024\n" +
                            "ecl:brn pid:760753108 byr:1931\n" +
                            "hgt:179cm\n" +
                            "\n",
                    "hcl:#cfa07d eyr:2025 pid:166559648\n" +
                            "iyr:2011 ecl:brn hgt:59in"));

    List<String> batchFilePart2 = new ArrayList<>(Arrays
            .asList("eyr:1972 cid:100\n" +
                            "hcl:#18171d ecl:amb hgt:170 pid:186cm iyr:2018 byr:1926\n" +
                            "\n",
                    "iyr:2019\n" +
                            "hcl:#602927 eyr:1967 hgt:170cm\n" +
                            "ecl:grn pid:012533040 byr:1946\n" +
                            "\n",
                    "hcl:dab227 iyr:2012\n" +
                            "ecl:brn hgt:182cm pid:021572410 eyr:2020 byr:1992 cid:277\n" +
                            "\n",
                    "hgt:59cm ecl:zzz\n" +
                            "eyr:2038 hcl:74454a iyr:2023\n" +
                            "pid:3556412378 byr:2007\n" +
                            "\n",
                    "pid:087499704 hgt:74in ecl:grn iyr:2012 eyr:2030 byr:1980\n" +
                            "hcl:#623a2f\n" +
                            "\n",
                    "eyr:2029 ecl:blu cid:129 byr:1989\n" +
                            "iyr:2014 pid:896056539 hcl:#a97842 hgt:165cm\n" +
                            "\n",
                    "hcl:#888785\n" +
                            "hgt:164cm byr:2001 iyr:2015 cid:88\n" +
                            "pid:545766238 ecl:hzl\n" +
                            "eyr:2022\n" +
                            "\n",
                    "iyr:2010 hgt:158cm hcl:#b6652a ecl:blu byr:1944 eyr:2021 pid:093154719"));

    @BeforeEach
    void setup() {
        dataLoader = mock(DataLoader.class);
    }

    // test length of output of method
    @Test
    public void test_getRawDataArray2() {
        when(dataLoader.getDataDay04()).thenReturn(batchFilePart1);
        Day04 day04 = new Day04(dataLoader);
        Integer expected = 4;
        Integer actual = day04.getBatchFile().size();
        assertThat(actual).isEqualTo(expected);
    }

    // puzzle 4.1 test number of valid passports of input data
    @Test
    public void test_getNumberValidPassports1() {
        when(dataLoader.getDataDay04()).thenReturn(batchFilePart1);
        Day04 day04 = new Day04(dataLoader);
        String expected = "Number of valid passports: " + 2;
        String actual = day04.firstPart();
        assertThat(actual).isEqualTo(expected);
    }

    // puzzle 4.2 test number of valid passports of input data (stricter rules)
    @Test
    public void test_getRestrictedNumberValidPassports() {
        when(dataLoader.getDataDay04()).thenReturn(batchFilePart2);
        Day04 day04 = new Day04(dataLoader);
        String expected = "Number of valid passports: " + 4;
        String actual = day04.secondPart();
        assertThat(actual).isEqualTo(expected);
    }
}