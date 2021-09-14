package org.haffson.adventofcode.days.day01;

import org.haffson.adventofcode.ProblemStatusEnum;
import org.haffson.adventofcode.days.Days;
import org.springframework.stereotype.Component;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

/**
 * Implementation for <i>Day 1: Chronal Calibration</i>.
 */
@Component
public class Day01 implements Days {

    /**
     * The puzzle status {@code HashMap}
     */
    private final Map<Integer, ProblemStatusEnum> problemStatus;

    // Read content of input file
    public InputStream resource = getClass().getResourceAsStream("/data/day01/input_day01.txt");
    private final Integer[] data = getRawData(resource);

    //    @Autowired
    Day01() {
        this.problemStatus = new HashMap<>();
        this.problemStatus.put(1, ProblemStatusEnum.SOLVED);
        this.problemStatus.put(2, ProblemStatusEnum.SOLVED);
    }

    @Override
    public int getDay() {
        return 1;
    }

    @Override
    public Map<Integer, ProblemStatusEnum> getProblemStatus() {
        return problemStatus;
    }

    @Override
    public String firstPart() {
        return "Product 1: " + calculateNumber1(data);
    }

    @Override
    public String secondPart() {
        try {
            return "Product 2: " + calculateNumber2(data);
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
    // read raw data and transform it to String[]
    public Integer[] getRawData(InputStream resource) {
        ArrayList<String> rawData;
        try (Scanner scan = new Scanner(resource)) {
            rawData = new ArrayList<>();

            while (scan.hasNextLine()) {
                rawData.add(scan.nextLine());
            }
        }
        Integer[] rawData_array = new Integer[rawData.size()];
        for (int i = 0; i < rawData.size(); i++) rawData_array[i] = Integer.parseInt(rawData.get(i));

        return rawData_array;
    }

    private int calculateNumber1(Integer[] rawData_array) {
        List<Integer> data = new ArrayList<>(rawData_array.length);
        data.addAll(Arrays.asList(rawData_array));
        // create arraylist that is subtracted by 2020
        ArrayList<Integer> data2 = new ArrayList<>();
        for (Integer datum : data) {
            data2.add(2020 - datum);
        }
        // check for intersection of two arraylists
        data.retainAll(data2);
        // multiplication of "intersected" values is the puzzle's answer!
        return data.get(0) * data.get(1);
    }

    private int calculateNumber2(Integer[] rawData_array) throws FileNotFoundException {
        List<Integer> data = new ArrayList<>(rawData_array.length);
        data.addAll(Arrays.asList(rawData_array));
        // create arraylist that is subtracted by 2020
        ArrayList<Integer> data2 = new ArrayList<>();
        for (Integer datum : data) {
            data2.add(2020 - datum);
        }
        List<Integer> data3 = new ArrayList<>();
        for (int k = 0; k < data.size(); k++) {
            for (Integer datum : data) {
                data3.add(data.get(k) + datum);
            }
        }
        data2.retainAll(data3);
        // multiplication of "intersected" values is the puzzle's answer!
        return (2020 - data2.get(0)) * (2020 - data2.get(1)) * (2020 - data2.get(2));
    }
}