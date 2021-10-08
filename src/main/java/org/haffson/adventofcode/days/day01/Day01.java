package org.haffson.adventofcode.days.day01;

import org.haffson.adventofcode.ProblemStatusEnum;
import org.haffson.adventofcode.days.Days;
import org.haffson.adventofcode.utils.DataLoader;
import org.haffson.adventofcode.utils.ProblemStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * Implementation for <i>Day 1: Chronal Calibration</i>.
 */
@Component
public class Day01 implements Days {

    private final Map<Integer, ProblemStatusEnum> problemStatus;
    private final List<Integer> numbers;

    public Day01(DataLoader dataLoader) {
        // get data
        this.numbers = dataLoader.getDataDay01();
        // set ProblemStatus
        this.problemStatus = ProblemStatus.getProblemStatusMap(1, 2,
                ProblemStatusEnum.SOLVED, ProblemStatusEnum.SOLVED);
    }

    public List<Integer> getNumbers() {
        return numbers;
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
        return "Product 1: " + calculateProduct_Part1(numbers);
    }

    @Override
    public String secondPart() {
        return "Product 2: " + calculateProduct_Part2(numbers);
    }

    /**
     * utility method: subtract numbers from 2020
     */
    private List<Integer> getSubtractedFrom2020(@NonNull List<Integer> numbers) {
        return numbers.stream()
                .map(value -> 2020 - value)
                .collect(Collectors.toList());
    }

    /**
     * Primary method for Day 1, Part 1.
     * Calculates the product of two specific numbers from a list
     *
     * @return the product
     */
    private int calculateProduct_Part1(@NonNull final List<Integer> numbers) {
        List<Integer> listOfNumbers_part1 = new ArrayList<>(numbers);

        // check for intersection of two lists
        listOfNumbers_part1.retainAll(getSubtractedFrom2020(numbers));
        // product of "intersected" values is the puzzle's answer!
        return listOfNumbers_part1.get(0) * listOfNumbers_part1.get(1);
    }

    /**
     * Primary method for Day 1, Part 2.
     * Calculates the product of two specific numbers from a list
     *
     * @return the product
     */
    private int calculateProduct_Part2(@NonNull final List<Integer> numbers) {
        List<Integer> listOfNumbers_part2 = new ArrayList<>(numbers);

        List<Integer> numbersSubtractedBy2020 = getSubtractedFrom2020(listOfNumbers_part2);

        List<Integer> tempData = new ArrayList<>();
        for (int k = 0; k < listOfNumbers_part2.size(); k++) {
            for (Integer datum : listOfNumbers_part2) {
                tempData.add(listOfNumbers_part2.get(k) + datum);
            }
        }
        // check for intersection of two lists
        numbersSubtractedBy2020.retainAll(tempData);
        // product of "intersected" values is the puzzle's answer!
        return (2020 - numbersSubtractedBy2020.get(0)) * (2020 - numbersSubtractedBy2020.get(1)) * (2020 - numbersSubtractedBy2020.get(2));
    }
}