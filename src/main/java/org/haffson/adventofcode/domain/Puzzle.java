package org.haffson.adventofcode.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * The Puzzle entity is managed by the {@link PuzzleRepository}.
 *
 * @author Michelle Fernandez Bieber
 */
@Entity
public class Puzzle {

    /** id of this Puzzle entity */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /** current day for this Puzzle */
    private String day;
    /** current part of this Puzzle */
    private String part;


    public Puzzle(String day, String part) {
        this.day = day;
        this.part = part;
    }
}
