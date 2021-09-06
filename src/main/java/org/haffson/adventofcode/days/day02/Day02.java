package org.haffson.adventofcode.days.day02;

import org.apache.commons.lang3.StringUtils;
import org.haffson.adventofcode.ProblemStatusEnum;
import org.haffson.adventofcode.days.Days;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;


/**
 * Implementation for <i>Day 1: Chronal Calibration</i>.
 */
@Component
public class Day02 implements Days {

//    // Adds a logger
//    private static final Logger logger = LoggerFactory.getLogger(Day02.class);

    // Read content of input file
    public InputStream resource = getClass().getResourceAsStream("/data/day02/input_day02.txt");
    private List<String> rawData = getRawDataAsList(resource);

    /** The puzzle status {@code HashMap} */
    private final Map<Integer, ProblemStatusEnum> problemStatus;

    @Autowired
    Day02() {
        this.problemStatus = new HashMap<>();
        this.problemStatus.put(1, ProblemStatusEnum.SOLVED);
        this.problemStatus.put(2, ProblemStatusEnum.SOLVED);
    }

    @Override
    public int getDay() {
        return 2;
    }

    @Override
    public Map<Integer, ProblemStatusEnum> getProblemStatus() {
        return problemStatus;
    }

    @Override
    public String firstPart() {
        try {
            return "Part 1 answer: " + getNumberPwd1();
        } catch (FileNotFoundException e) {
            return "Error in method 1: " + e.getMessage();
        }
    }

    @Override
    public String secondPart() {
        try {
            return "Part 2 answer: " + getNumberPwd2();
        } catch (FileNotFoundException e) {
            return "Error in method 2: " + e.getMessage();
        }
    }




    /**
     * Method to read raw data from file into list
     * @return raw data as list
     */
    public List<String> getRawDataAsList(InputStream resource) {

        ArrayList<String> rawData;
        try (Scanner scan = new Scanner(resource)) {
            rawData = new ArrayList<>();

            while (scan.hasNextLine()) {
                rawData.add(scan.nextLine());
            }
        }

        return rawData;
    }

    /**
     * Method getData() to get min and max number, letter and password from raw data
     * First, create class Data to avoid having multiple return values
     */
    public static class Data {
        private final int min;
        private final int max;
        private final String letter;
        private final String password;

        public Data(int min, int max, @NonNull String letter, @NonNull String password) {

            this.min = min;
            this.max = max;
            this.letter = requireNonNullAndNonEmpty(letter);
            this.password = requireNonNullAndNonEmpty(password);

        }

    }

    public static String requireNonNullAndNonEmpty(String string){
        if (StringUtils.isEmpty(string)){
            throw new NullPointerException("The string is null or empty");
        } else {
            return string;
        }
    }

    public List<Data> getData(List<String> rawData) {

        List<Data> dataArrayList = new ArrayList<>();

        for (String line : rawData) {
            // match patterns to determine min and max number of the letter in password,
            // the searched letter and password itself
            String pattern = "(\\d+)-(\\d+)\\s+(\\w):\\s+(\\w*)";
            int min = Integer.parseInt(line.replaceAll(pattern, "$1"));
            int max = Integer.parseInt(line.replaceAll(pattern, "$2"));
            String letter = line.replaceAll(pattern, "$3");
            String password = line.replaceAll(pattern, "$4");

            dataArrayList.add(new Data(min, max, letter, password));
        }
        return dataArrayList;
    }


    /**
     * Primary method for Day 1, Part 1.
     * Gets the number of correct passwords from a list
     *
     * @return the number of correct passwords in list
     */

    private int getNumberPwd1() throws FileNotFoundException {
        // get data
        List<Data> data = getData(rawData);
        int countCorrPwd = 0; // answer to puzzle day 2.1: number of correct passwords in list

        // loop through passwords
        for (Data datum : data) {
            int count = 0;  // number of letter appearance in password

            // count how often specific letter appears in pwd
            for (int j = 0; j < datum.password.length(); j++){
                if (datum.password.charAt(j) == datum.letter.charAt(0)) count++;
            }
            // check if count meets criteria for correct password
            if (count >= datum.min && count <= datum.max) {
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
        for (Data datum : data) {

            boolean onlyFirstPosition = datum.password.charAt(datum.min - 1) == datum.letter.charAt(0) &&
                    datum.password.charAt(datum.max - 1) != datum.letter.charAt(0);
            boolean onlySecondPosition = datum.password.charAt(datum.max - 1) == datum.letter.charAt(0) &&
                    datum.password.charAt(datum.min - 1) != datum.letter.charAt(0);

            // letter must appear only once (either only(!) at first or only(!) at second position)
            if ((onlyFirstPosition && !onlySecondPosition) || (!onlyFirstPosition && onlySecondPosition)) {
                countCorrPwd++;
            }
        }
        return countCorrPwd;
    }

}
