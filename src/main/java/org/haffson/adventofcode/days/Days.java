package org.haffson.adventofcode.days;

import org.haffson.adventofcode.ProblemStatusEnum;

import java.util.HashMap;

/**
 * An interface for the individual puzzles,
 * which are located in subpackages.
 *
 * @author Michelle Fernandez Bieber
 */
public interface Days {

    /**
     * Returns the solution of Part 1.
     *
     * @return the solution of Part 1
     */
    String firstPart();

    /**
     * Returns the solution of Part 2.
     *
     * @return the solution of Part 2
     */
    String secondPart();

    /**
     * Returns an integer of the current day.
     *
     * @return an integer of the current day
     */
    int getDay();

    /**
     * Returns the status of each part as defined in {@link ProblemStatusEnum}
     *
     * @return the status of each part
     */
    HashMap<String, ProblemStatusEnum> getProblemStatus();
}
