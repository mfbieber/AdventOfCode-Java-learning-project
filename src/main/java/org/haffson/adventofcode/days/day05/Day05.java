package org.haffson.adventofcode.days.day05;

import org.haffson.adventofcode.utils.CheckStringisEmpty;
import org.haffson.adventofcode.utils.DataLoader;
import org.haffson.adventofcode.utils.ProblemStatus;
import org.springframework.lang.NonNull;
import org.haffson.adventofcode.ProblemStatusEnum;
import org.haffson.adventofcode.days.Days;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class Day05 implements Days {

    private final Map<Integer, ProblemStatusEnum> problemStatus;
    private final List<String> boardingPasses;

    Day05(@NonNull String filename) {
        //get data
        this.boardingPasses = DataLoader.getRawDataAsList("/day05/" + filename, "\n");
        // set problemstatus
        this.problemStatus = ProblemStatus.getProblemStatusMap(1, 2,
                ProblemStatusEnum.SOLVED, ProblemStatusEnum.SOLVED);
    }

    public List<String> getBoardingPasses() {
        return boardingPasses;
    }

    // answers puzzle 5
    // binary space partitioning principle
    public int getRowOrCol(@NonNull String seatName, int maxNumberRowsOrCols, char character1, char character2) {
        CheckStringisEmpty.requireNonNullAndNonEmpty(seatName);
        int numMin = 0;
        int numMax = maxNumberRowsOrCols - 1;
        int rowOrCol = 0;
        int exponent = 1;
        int count = 0;
        for (Character letter : seatName.toCharArray()) {
            if (letter.equals(character1)) {
                numMax = (int) (numMax - maxNumberRowsOrCols / Math.pow(2, exponent));
                exponent++;
                count++;
            }
            if (letter.equals(character2)) {
                numMin = numMin + (int) (maxNumberRowsOrCols / Math.pow(2, exponent));
                exponent++;
                count++;
            }
            if (count == seatName.length() && (letter.equals(character1))) {
                rowOrCol = numMin;

            } else if (count == seatName.length() && (letter.equals(character2))) {
                rowOrCol = numMax;
            }
        }
        return rowOrCol;
    }

    // seatID is row*8 + column
    private List<Integer> getSeatID(@NonNull List<String> boardingPasses) {
        return boardingPasses.stream()
                .map(seat -> getRowOrCol(seat.substring(0, 7), 128, 'F', 'B') * 8
                        + getRowOrCol(seat.substring(7), 8, 'L', 'R'))
                .collect(Collectors.toList());
    }

    // First Part: find highest seatID
    @Override
    public String firstPart() {
        int highestSeatID = Collections.max(getSeatID(boardingPasses));
        return "The highest seatID is: " + highestSeatID;
    }

    // Second Part: find missing seatID
    @Override
    public String secondPart() {
        List<Integer> seatIDs_sorted = getSeatID(boardingPasses);
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