package org.haffson.adventofcode.exceptions;

import org.haffson.adventofcode.service.AdventOfCodeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * A {@link RuntimeException} to be used in {@link AdventOfCodeService}.
 *
 * @author Michelle Fernandez Bieber
 */
@ResponseStatus(value = HttpStatus.NOT_IMPLEMENTED, reason = "This puzzle has not been solved yet. Please contact your local nerd to implement a solution for that first.")
public class PuzzleNotSolvedYetException extends RuntimeException {
    public PuzzleNotSolvedYetException(final Throwable e) {
        super(e);
    }
}