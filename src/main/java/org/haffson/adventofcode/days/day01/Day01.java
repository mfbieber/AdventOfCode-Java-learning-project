package org.haffson.adventofcode.days.day01;

import org.haffson.adventofcode.ProblemStatusEnum;
import org.haffson.adventofcode.days.Days;
import org.haffson.adventofcode.utils.FileReaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Implementation for <i>Day 1: Chronal Calibration</i>.
 */
@Component
public class Day01 implements Days {

    /** The puzzle status {@code HashMap} */
    private final HashMap<String, ProblemStatusEnum> problemStatus;

    /**
     * Causes the input file to be parsed into the frequencies array ({@code frequencies}).
     *
     * @param fileReaders {@code @Autowired} fileReader //TODO: inject what you need
     */
    @Autowired
    Day01(FileReaders fileReaders) {
        this.problemStatus = new HashMap<>();
        this.problemStatus.put("1", ProblemStatusEnum.SOLVED);
        this.problemStatus.put("2", ProblemStatusEnum.UNSOLVED);
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
        try {
            return "" + calculateNumber();
        } catch (FileNotFoundException e) {
            return "error";
        }
    }

    @Override
    public String secondPart() {
        return null;
    }

    /**
     * Primary method for Day 1, Part 1.
     * Calculates the final frequency as the sum of all frequencies.
     *
     * @return the final frequency
     */
//    private int calculateFrequency() {
//        return 0;
//    }
    private int calculateNumber() throws FileNotFoundException {

        // read data file

        Scanner s = new Scanner(new File("/Users/jenni/dedica/AdventOfCode/Jenni/Day01/src/input.txt"));
        ArrayList<Integer> data = new ArrayList<Integer>();

        while (s.hasNext()) {
            int i = Integer.parseInt(s.next());
            data.add(i);
        }
        s.close();


        // DAY 1 PUZZLE 1:

        // clone data list
        ArrayList<Integer> data1 = (ArrayList<Integer>) data.clone();

        // create arraylist that is subtracted by 2020
        ArrayList<Integer> data2 = new ArrayList<Integer>();

        for (int j = 0; j < data.size(); j++) {
            data2.add(2020 - data1.get(j));
        }

//        data.stream().map(a -> 2020-a).collect(Collectors.toList());

        // check for intersection of two arraylists
        ArrayList<Integer> test = new ArrayList<Integer>();
        data1.retainAll(data2);

        // multiplication of "intersected" values is the puzzle's answer!
        System.out.println("The answer of Puzzle Day 1.1 is: " + data1.get(0) * data1.get(1));
        return data1.get(0) * data1.get(1);
    }

    }
