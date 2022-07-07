package org.haffson.adventofcode.service;

import org.haffson.adventofcode.ProblemStatusEnum;
import org.haffson.adventofcode.controller.AdventOfCodeController;
import org.haffson.adventofcode.days.Days;
import org.haffson.adventofcode.exceptions.PuzzleNotSolvedYetException;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * The <i>Advent of Code</i> Service is used by
 * {@link AdventOfCodeController}
 * to handle calls made to it.
 *
 * @author Michelle Fernandez Bieber
 */
@Component
public class AdventOfCodeService {

    /**
     * A list containing all {@link Days} from the corresponding subpackages.
     */
    private final List<Days> daysSolutions;

    /**
     * {@code @Autowired} constructor adding all implemented {@link Days} to the list of Days.
     *
     * @param daysSolutions {@code @Autowired} days solutions
     */

    public AdventOfCodeService(final List<Days> daysSolutions) {
        this.daysSolutions = Objects.requireNonNull(daysSolutions);
    }

    /**
     * Asks for the implementation for the requested day and then checks whether the requested part
     * has been solved yet. Then it requests the retrieved implementation to calculate the solution.
     *
     * @param day  the simple day of the advent calendar to be solved
     * @param part the part of the puzzle for that day
     * @return a {@code String} with the result for the puzzle, or in case it has not been implemented,
     * an {@link PuzzleNotSolvedYetException} is thrown.
     */
    public String getResultsForASpecificDayAndPuzzlePart(@NonNull final Integer day, @NonNull final Integer part) {

        Objects.requireNonNull(day, "day is null");
        Objects.requireNonNull(part, "part is null");


        final Days thisDaysClass = findDayForDay(day);
        if (!isProblemSolvedForPart(thisDaysClass, part)) {
            throw new PuzzleNotSolvedYetException(new Throwable());
        } else if (Objects.equals(part, 1)) {
            return thisDaysClass.firstPart();
        } else if (Objects.equals(part, 2)) {
            return thisDaysClass.secondPart();
        } else {
            return "This puzzle has not been solved yet.";
        }
    }

    /**
     * Checks whether the corresponding part to a day has already been solved.
     *
     * @param thisDaysClass the implementation for the requested day
     * @param part          the part to check for it's solution status
     * @return if the part has been solved for a specific day
     */
    private boolean isProblemSolvedForPart(final Days thisDaysClass, final Integer part) {
        return thisDaysClass.getProblemStatus().containsKey(part) && thisDaysClass.getProblemStatus().get(part) == ProblemStatusEnum.SOLVED;
    }

    /**
     * Streams through all the provided implementations for the days inside the list populated by Spring and retrieves
     * a requested implementation for a day.
     *
     * @param day the day for which an implementation should be retrieved
     * @return the {@code Day} implementation for the requested day
     */
    private Days findDayForDay(final int day) {
        return daysSolutions.stream()
                .filter(solution -> solution.getDay() == day)
                .findFirst()
                .orElseThrow(() -> new PuzzleNotSolvedYetException(new Throwable()));
    }

    /**
     * Getter for {@code daysSolutions}
     *
     * @return a sorted (by day!) List of all implemented days
     */
    public List<Days> getDaysSolutions() {
        final Comparator<Days> dayComparator = new DayComparator();
        daysSolutions.sort(dayComparator);
        return daysSolutions;
    }

    /**
     * creation of a Comparator to use the getDay() method of Days to sort the day instances:
     */
    @Component
    public static class DayComparator implements Comparator<Days> {

        @Override
        public int compare(final Days day1, final Days day2) {
            return Integer.compare(day1.getDay(), day2.getDay());
        }
    }
}