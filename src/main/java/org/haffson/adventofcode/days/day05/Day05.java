package org.haffson.adventofcode.days.day05;

import io.micrometer.core.lang.NonNull;
import org.apache.commons.lang3.StringUtils;
import org.haffson.adventofcode.ProblemStatusEnum;
import org.haffson.adventofcode.days.Days;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class Day05 implements Days {

    /**
     * the puzzle status {@code HashMap}
     */
    private final Map<Integer, ProblemStatusEnum> problemStatus;

    @Autowired
    Day05() {
        this.problemStatus = new HashMap<>();
        this.problemStatus.put(1, ProblemStatusEnum.SOLVED);
        this.problemStatus.put(2, ProblemStatusEnum.SOLVED);
    }

    // read in test data and AoC data as InputStream and return data as ArrayList<String>
    public InputStream testDataInputStream = getClass().getResourceAsStream("/data/day05/day05_testdata1.txt");
    public InputStream dataInputStream = getClass().getResourceAsStream("/data/day05/input_day05.txt");

    public static ArrayList<String> getRawdataAsList(InputStream data) {
        ArrayList<String> rawdata;
        try (Scanner scan = new Scanner(data)) {
            rawdata = new ArrayList<>();

            while (scan.hasNext()) {
                scan.useDelimiter("\n");
                rawdata.add(scan.next());
            }
        }
        return rawdata;
    }

    // answers puzzle 5
    // binary space partitioning principle
    public int getRowOrCol(@NonNull String s, int stop, char identifier1, char identifier2) {
        s = requireNonNullAndNonEmpty(s);
        int numMin = 0;
        int numMax = stop - 1;
        int rowOrCol = 0;
        int num = 1;
        int count = 0;
        for (Character letter : s.toCharArray()) {
            if (letter.equals(identifier1)) {
                numMax = (int) (numMax - stop / Math.pow(2, num));
                num++;
                count++;
            }
            if (letter.equals(identifier2)) {
                numMin = numMin + (int) (stop / Math.pow(2, num));
                num++;
                count++;
            }
            if (count == s.length() && (letter.equals(identifier1))) {
                rowOrCol = numMin;

            } else if (count == s.length() && (letter.equals(identifier2))) {
                rowOrCol = numMax;
            }
        }
        return rowOrCol;
    }

    @NonNull
    //check for nulls (if input string is empty)
    public static String requireNonNullAndNonEmpty(String string) {
        if (StringUtils.isEmpty(string)) {
            throw new IllegalArgumentException("The string is null or empty");
        } else {
            return string;
        }
    }

    // seatID is row*8 + column
    public List<Integer> getSeatID(@NonNull final InputStream dataIn) {
        return getRawdataAsList(Objects.requireNonNull(dataIn, "Data Inputstream is null.")).stream()
                .map(ss -> getRowOrCol(ss.substring(0, 7), 128, 'F', 'B') * 8
                        + getRowOrCol(ss.substring(7), 8, 'L', 'R'))
                .collect(Collectors.toList());
    }

    // First Part: find highest seatID
    @Override
    public String firstPart() {
        int highestSeatID = Collections.max(getSeatID(dataInputStream));
        return "The highest seatID is: " + highestSeatID;
    }

    // Second Part: find missing seatID
    @Override
    public String secondPart() {
        List<Integer> seatIDs_sorted = getSeatID(dataInputStream);
        Collections.sort(seatIDs_sorted);
        int mySeat = 0;
        for (int i = 0; i < seatIDs_sorted.size() - 1; i++) {
            if (seatIDs_sorted.get(i + 1) - seatIDs_sorted.get(i) != 1) {
                mySeat = seatIDs_sorted.get(i) + 1;
            }
        }
        return "My seatID is: " + mySeat;
    }

    @Override
    public int getDay() {
        return 5;
    }

    @Override
    public Map<Integer, ProblemStatusEnum> getProblemStatus() {
        return problemStatus;
    }
}