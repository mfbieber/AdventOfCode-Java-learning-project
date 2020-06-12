package org.haffson.adventofcode.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The PuzzleRepository manages the {@link Puzzle} entities.
 *
 * @author Michelle Fernandez Bieber
 */
@Repository
public interface PuzzleRepository extends CrudRepository<Puzzle, Long> {

    List<Puzzle> findAll();

    // todo JavaDoc - ask Michelle
    List<Puzzle> findAllByDay(String day);

    Puzzle save(Puzzle puzzle);
}
