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
        this.problemStatus.put("2", ProblemStatusEnum.SOLVED);
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
            return "Product 1: " + calculateNumber1();
        } catch (FileNotFoundException e) {
            return "error";
        }
    }

    @Override
    public String secondPart() {
        try {
            return "Product 2: " + calculateNumber2();
        } catch (FileNotFoundException e) {
            return "error";
        }
    }

    /**
     * Primary method for Day 1, Part 1.
     * Calculates the product of two specific numbers from a list
     *
     * @return the final frequency
     */


    private int calculateNumber1() throws FileNotFoundException {

        // read data file
        Scanner s = new Scanner(new File("/Users/jenni/dedica/AdventOfCode/Jenni/Day01/src/input.txt"));
        ArrayList<Integer> data = new ArrayList<Integer>();

        while (s.hasNext()) {
            int i = Integer.parseInt(s.next());
            data.add(i);
        }
        s.close();

        // create arraylist that is subtracted by 2020
        ArrayList<Integer> data2 = new ArrayList<Integer>();

        for (int j = 0; j < data.size(); j++) {
            data2.add(2020 - data.get(j));
        }

        // check for intersection of two arraylists
        ArrayList<Integer> test = new ArrayList<Integer>();
        data.retainAll(data2);

        // multiplication of "intersected" values is the puzzle's answer!
        System.out.println("The answer of Puzzle Day 1.1 is: " + data.get(0) * data.get(1));
        return data.get(0) * data.get(1);
    }

    private int calculateNumber2() throws FileNotFoundException {

        // read data file
        Scanner s = new Scanner(new File("/Users/jenni/dedica/AdventOfCode/Jenni/Day01/src/input.txt"));
        ArrayList<Integer> data = new ArrayList<Integer>();

        while (s.hasNext()) {
            int i = Integer.parseInt(s.next());
            data.add(i);
        }
        s.close();

        // create arraylist that is subtracted by 2020
        ArrayList<Integer> data2 = new ArrayList<Integer>();

        for (int j = 0; j < data.size(); j++) {
            data2.add(2020 - data.get(j));
        }

        ArrayList<Integer> data3 = new ArrayList<Integer>();

        for (int k=0; k<data.size();k++) {
            for (int l=0; l<data.size(); l++){
                data3.add(data.get(k) + data.get(l));

            }
        }

        data2.retainAll(data3);

        // multiplication of "intersected" values is the puzzle's answer!
        System.out.println("The answer of Puzzle Day 1.2 is: " + (2020-data2.get(0))*(2020-data2.get(1))*(2020-data2.get(2)));

        return (2020-data2.get(0))*(2020-data2.get(1))*(2020-data2.get(2));

    }


}
