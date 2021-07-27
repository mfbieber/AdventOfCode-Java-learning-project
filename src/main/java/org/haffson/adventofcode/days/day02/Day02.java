package org.haffson.adventofcode.days.day02;

import org.haffson.adventofcode.ProblemStatusEnum;
import org.haffson.adventofcode.days.Days;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;



/**
 * Implementation for <i>Day 1: Chronal Calibration</i>.
 */
@Component
public class Day02 implements Days {

    private final String path = "/Users/jenni/dedica/AdventOfCode/Jenni/Day02/src/input.txt";
    private List<String> rawData = getRawDataAsList(path);

    /** The puzzle status {@code HashMap} */
    private final HashMap<String, ProblemStatusEnum> problemStatus;

    @Autowired
    Day02() {
        this.problemStatus = new HashMap<>();
        this.problemStatus.put("1", ProblemStatusEnum.SOLVED);
        this.problemStatus.put("2", ProblemStatusEnum.SOLVED);
    }

    @Override
    public int getDay() {
        return 2;
    }

    @Override
    public HashMap<String, ProblemStatusEnum> getProblemStatus() {
        return problemStatus;
    }

    @Override
    public String firstPart() {
        try {
            return "Part 1 answer: " + getNumberPwd1();
        } catch (FileNotFoundException e) {
            return "Error in method 1";
        }
    }

    @Override
    public String secondPart() {
        try {
            return "Part 2 answer: " + getNumberPwd2();
        } catch (FileNotFoundException e) {
            return "Error in method 2";
        }
    }


    /** Method to read raw data from file into list
     *
     * @return raw data as list
     */
    public List<String> getRawDataAsList(String path) {
        List<String> rawData = new ArrayList<>();

        try {
            Scanner s = new Scanner(new File(path)).useDelimiter("\n");
            while (s.hasNext()) {
                rawData.add(s.next());
            }
            s.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
        return rawData;
    }

    /**
     * Method getData() to get min and max number, letter and password from raw data
     * First, create class Data to avoid having multiple return values
     */
    public static class Data {
        int min;
        int max;
        String letter;
        String pwd;

        public Data(int min, int max, String letter, String pwd) {
            this.min = min;
            this.max = max;
            this.letter = letter;
            this.pwd = pwd;
        }
    }

    public List<Data> getData(List<String> rawData) {

        List<Data> dataArrayList = new ArrayList<>();

        for (int i = 0; i < rawData.size(); i++) {

            String line = rawData.get(i);

            // match patterns to determine min and max number of the letter in pwd, the searched letter and pwd itself
            String pattern = "(\\d+)-(\\d+)\\s+(\\w):\\s+(\\w*)";
            int min = Integer.parseInt(line.replaceAll(pattern, "$1"));
            int max = Integer.parseInt(line.replaceAll(pattern, "$2"));
            String letter = line.replaceAll(pattern, "$3");
            String pwd = line.replaceAll(pattern, "$4");

            dataArrayList.add(new Data(min, max, letter, pwd));
        }
        return dataArrayList;
    }


    /**
     * Primary method for Day 1, Part 1.
     * Gets the number of correct passwords from a list
     *
     * @return the number of correct pwds in list
     */

    private int getNumberPwd1() throws FileNotFoundException {
        // get data
        List<Data> data = getData(rawData);

        int countCorrPwd = 0; // answer to puzzle day 2.1: number of correct passwords in list

        // loop through passwords
        for (int i=0; i < data.size(); i++ ) {
            int count = 0;  // number of letter appearance in pwd

            // count how often specific letter appears in pwd
            for (int j = 0; j < data.get(i).pwd.length(); j++) {
                if (data.get(i).pwd.charAt(j) == data.get(i).letter.charAt(0)) count++;
            }
             // check if count meets criteria for correct pwd
            if (count >= data.get(i).min && count <= data.get(i).max) {
                countCorrPwd++;
            }
        }
        return countCorrPwd;
    }

    // if raw data is not read via file, e.g. test data
    public void addInput(List<String> rawData) {
        this.rawData = rawData;
    }

    private int getNumberPwd2() throws FileNotFoundException {
        // get data
        List<Data> data = getData(rawData);

        int countCorrPwd = 0; // answer to puzzle day 2.2: number of correct passwords in list

        // loop through passwords
        for (int i=0; i < data.size(); i++ ) {

            boolean onlyFirstPosition = data.get(i).pwd.charAt(data.get(i).min - 1) == data.get(i).letter.charAt(0) && data.get(i).pwd.charAt(data.get(i).max - 1) != data.get(i).letter.charAt(0);
            boolean onlySecondPosition = data.get(i).pwd.charAt(data.get(i).max - 1) == data.get(i).letter.charAt(0) && data.get(i).pwd.charAt(data.get(i).min - 1) != data.get(i).letter.charAt(0);

            // letter must appear only once (either only(!) at first or only(!) at second position)
            if ((onlyFirstPosition && !onlySecondPosition) || (!onlyFirstPosition && onlySecondPosition)) {
                countCorrPwd++;
            }
        }
        return countCorrPwd;
    }

}
