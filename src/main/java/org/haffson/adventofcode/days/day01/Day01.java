package org.haffson.adventofcode.days.day01;

import org.haffson.adventofcode.ProblemStatusEnum;
import org.haffson.adventofcode.days.Days;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

/**
 * Implementation for <i>Day 1: Chronal Calibration</i>.
 */
@Component
public class Day01 implements Days {

    /** The puzzle status {@code HashMap} */
    private final HashMap<String, ProblemStatusEnum> problemStatus;

    // Adds a logger
    private static final Logger logger = LoggerFactory.getLogger(Day01.class);


    // Read content of input file
    public InputStream resource = getClass().getResourceAsStream("/data/day01/input_day01.txt");

//    // Read content of input file (real data)
//    public File resource;
//    {
//        try {
//            resource = new ClassPathResource(
//                    "data/day01/input_day01.txt").getFile();
//        } catch (IOException e) {
//            logger.error("Raw Data (Input) file not found: " + e.getMessage());
//        }
//    }



    @Autowired
    Day01() {
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
        return "Product 1: " + calculateNumber1(getRawData(resource));
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


    // read raw data and transform it to String[]
    public Integer[] getRawData(InputStream resource) {
//        try (Scanner s = new Scanner(new File(String.valueOf(resource.toPath()))).useDelimiter("\n")){
//            while (s.hasNext()) {
//                rawData.add(s.next());
//            }
//        } catch (FileNotFoundException e) {
//            logger.error("File not found!" + e.getMessage());
//        }

        ArrayList<String> rawData;
        try (Scanner scan = new Scanner(resource)) {
            rawData = new ArrayList<>();

            while (scan.hasNextLine()) {
                rawData.add(scan.nextLine());
            }
        }


        Integer[] rawData_array = new Integer[rawData.size()];
        for(int i = 0; i < rawData.size(); i++) rawData_array[i] = Integer.parseInt(rawData.get(i));

        return rawData_array;
    }



    private int calculateNumber1(Integer[] rawData_array){

        List<Integer> data = new ArrayList<>(rawData_array.length);
        data.addAll(Arrays.asList(rawData_array));

        // create arraylist that is subtracted by 2020
        ArrayList<Integer> data2 = new ArrayList<>();

        for (Integer datum : data) {
            data2.add(2020 - datum);
        }

        // check for intersection of two arraylists
        ArrayList<Integer> test = new ArrayList<Integer>();
        data.retainAll(data2);

        // multiplication of "intersected" values is the puzzle's answer!
//        System.out.println("The answer of Puzzle Day 1.1 is: " + data.get(0) * data.get(1));
        return data.get(0) * data.get(1);
    }

    private int calculateNumber2() throws FileNotFoundException {

        // read data file
        Scanner s = new Scanner(new File("/Users/jenni/dedica/AdventOfCode/AdventOfCode-Java-learning-project/src/main/resources/data/day01/input_day01.txt"));
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
//        System.out.println("The answer of Puzzle Day 1.2 is: " + (2020-data2.get(0))*(2020-data2.get(1))*(2020-data2.get(2)));

        return (2020-data2.get(0))*(2020-data2.get(1))*(2020-data2.get(2));

    }


}
