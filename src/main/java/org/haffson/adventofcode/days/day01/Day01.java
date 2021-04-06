package org.haffson.adventofcode.days.day01;

import org.haffson.adventofcode.ProblemStatusEnum;
import org.haffson.adventofcode.days.Days;
import org.haffson.adventofcode.utils.FileReaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * Implementation for <i>Day 1: Report Repair/i>.
 */
@Component
public class Day01 implements Days {

    /** The puzzle status {@code HashMap} */
    private final HashMap<String, ProblemStatusEnum> problemStatus;

    private final int[] numbers;

    @Autowired
    Day01(FileReaders fileReaders, @Value("${day1.file}") String filePath) {
        this.problemStatus = new HashMap<>();
        this.problemStatus.put("1", ProblemStatusEnum.SOLVED);
        this.problemStatus.put("2", ProblemStatusEnum.SOLVED);
        this.numbers = fileReaders.readFileIntoArrayOfIntegers(filePath);
    }

    @Override
    public int getDay() {
        return 1;
    }

    @Override
    public HashMap<String, ProblemStatusEnum> getProblemStatus() {
        return problemStatus;
    }

    @Override
    public String firstPart() {
        return "Part 1: " + calculatePart1();
    }

    @Override
    public String secondPart() {
        return "Part 2: " + calculatePart2();
    }

    /**
     * Primary method for Day 1, Part 1.
     * Calculates the Report
     *
     * @return Returns the product of the multiplication of two numbers that add up to 2020.
     */
    private int calculatePart1() {
        int[] entries = this.findTwoEntriesThatSumToX(this.numbers, 2020);
        return multiplyListEntries(entries);
    }


    /**
     * Primary method for Day 1, Part 2.
     * Calculates the Report
     *
     * @return Returns the product of the multiplication of three numbers that add up to 2020.
     */
    private int calculatePart2() {
        int[] entries = this.findThreeEntriesThatSumToX(this.numbers, 2020);
        return multiplyListEntries(entries);
    }

    /***
     * This helper function calculates the product of all list entries.
     *
     * @param values values
     * @return Returns the product of all list entries.
     */
    private int multiplyListEntries(int[] values) {
        int result = 1;
        for (int value : values) {
            result = result * value;
        }
        return result;
    }

    /***
     * This helper function is used to find two list entries that add up to the value x.
     *
     * @param values values
     * @param x sum of the two list entries
     * @return Returns the list entries witch add up to x.
     */
    private int[] findTwoEntriesThatSumToX(int[] values, int x) {
        int[] entries = null;
        outer:
        for (int i = 0; i < values.length - 1; i++) {
            for (int j = i + 1; j < values.length - 1; j++) {
                if (values[i] + values[j] == x) {
                    entries = new int[]{values[i], values[j]};
                    break outer;
                }
            }
        }
        return entries;
    }


    /***
     * This helper function is used to find three list entries that add up to the value x.
     *
     * @param values values
     * @param x sum of the three list entries
     * @return Returns the list entries witch add up to x.
     */
    private int[] findThreeEntriesThatSumToX(int[] values, int x) {
        int[] entries = null;
        outer:
        for (int i = 0; i < values.length - 3; i++) {
            for (int j = i + 1; j < values.length - 2; j++) {
                for (int k = j + 1; k < values.length - 1; k++) {
                    if (values[i] + values[j] + values[k] == x) {
                        entries = new int[]{values[i], values[j], values[k]};
                        break outer;
                    }
                }
            }
        }
        return entries;
    }

}
