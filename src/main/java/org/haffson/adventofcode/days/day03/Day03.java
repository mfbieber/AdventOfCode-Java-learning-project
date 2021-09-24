package org.haffson.adventofcode.days.day03;

import org.haffson.adventofcode.ProblemStatusEnum;
import org.haffson.adventofcode.days.Days;
import org.haffson.adventofcode.utils.DataLoader;
import org.haffson.adventofcode.utils.ProblemStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Implementation for <i>Day 1: chronal Calibration</i>.
 */
@Component
public class Day03 implements Days {

    private final Map<Integer, ProblemStatusEnum> problemStatus;
    private final List<String> grid;

    Day03(DataLoader dataLoader) {
        //get data
        this.grid = dataLoader.getDataDay03();
        // set problemstatus
        this.problemStatus = ProblemStatus.getProblemStatusMap(1, 2,
                ProblemStatusEnum.SOLVED, ProblemStatusEnum.SOLVED);
    }

    public List<String> getGrid() {
        return grid;
    }

    @Override
    public int getDay() {
        return 3;
    }

    @Override
    public Map<Integer, ProblemStatusEnum> getProblemStatus() {
        return problemStatus;
    }

    @Override
    public String firstPart() {
        return "Trees encountered: " + getNumberTrees(grid);
    }

    @Override
    public String secondPart() {
        return "Product of all slopes: " + getProduct(grid);
    }

    //    method for answer of puzzle day03 part 1
    //    search for number of trees encountered
    private int getNumberTrees(final List<String> grid) {

        int sizeX = grid.get(0).length(); // vertical direction
        int sizeY = grid.size();    // horizontal direction
        int numberTrees = 0; // number of trees encountered
        char square = 0; // each coordinate on grid is called square

        for (int i = 1; i < sizeY; i++) {
            // every step: 1 square vertical, 3 squares horizontal
            if ((i * 3) <= sizeX) {
                square = grid.get(i * 1).charAt(i * 3);
            }
            // as in horizontal direction the same pattern repeats to the right many times, use modulo in if-condition
            else if (i * 3 > sizeX) {
                int repeatedPosition = (i * 3) % sizeX;
                square = grid.get(i * 1).charAt(repeatedPosition);
            }
            if (square == '#') {
                numberTrees++;
            }
        }
        return numberTrees;
    }

    // method for answer of puzzle day03 part 2
    private long getProduct(final List<String> grid) {
        // 5 slopes need to be checked
        int[] stepY = new int[5];
        int[] stepX = new int[5];

        stepY[0] = 1;
        stepY[1] = 1;
        stepY[2] = 1;
        stepY[3] = 1;
        stepY[4] = 2;

        stepX[0] = 1;
        stepX[1] = 3;
        stepX[2] = 5;
        stepX[3] = 7;
        stepX[4] = 1;

        int sizeX = grid.get(0).length(); // horizontal direction
        int sizeY = grid.size();      // vertical direction
        long product = 1; // number of product of all trees with all slopes

        for (int j = 0; j < stepY.length; j++) {
            int numberTrees = 0; // number of trees encountered

            for (int i = 1; i * stepY[j] < sizeY; i++) {
                if (grid.get(i * stepY[j]).charAt((i * stepX[j]) % sizeX) == '#') {
                    numberTrees++;
                }
            }
            product *= numberTrees;
        }
        return product;
    }
}