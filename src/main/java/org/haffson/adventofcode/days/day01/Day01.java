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
        this.problemStatus.put("1", ProblemStatusEnum.UNSOLVED);
        this.problemStatus.put("2", ProblemStatusEnum.UNSOLVED);
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
        return "Part 1: " + calculateReport();
    }

    @Override
    public String secondPart() {
        return null;
    }

    /**
     * Primary method for Day 1, Part 1.
     * Calculates the Report
     *
     * @return Returns the product of the multiplication of the numbers that add up to 2020.
     */
    private int calculateReport() {
        int sum;
        int report = 0;
        for(int i = 0; i < this.numbers.length - 1; i++){
            for(int j = 1; j < this.numbers.length - 1; j++){
                sum = numbers[i] + numbers[j];
                if(sum == 2020){
                    report = numbers[i] * numbers[j];
                    break;
                }
            }
        }
        return report;
    }
}
