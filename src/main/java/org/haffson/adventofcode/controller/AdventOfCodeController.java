package org.haffson.adventofcode.controller;

import org.haffson.adventofcode.days.Days;
import org.haffson.adventofcode.service.AdventOfCodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * The main REST controller for the <i>Advent Of Code 2018</i> Application.
 * It is used to handle calls to {@code /api/adventOfCode}.
 *
 * @author Michelle Fernandez Bieber
 */
@RestController
@RequestMapping(value = "/api/adventOfCode", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AdventOfCodeController {

    /** Adds a logger to the controller */
    private static final Logger logger = LoggerFactory.getLogger(AdventOfCodeController.class);

    /** Implements the {@link AdventOfCodeService}. */
    private AdventOfCodeService adventOfCodeService;

    /**
     * {@code @Autowired} constructor of this controller.
     *
     * @param adventOfCodeService {@code @Autowired} adventOfCodeService
     */
    @Autowired
    public AdventOfCodeController(AdventOfCodeService adventOfCodeService) {
        this.adventOfCodeService = adventOfCodeService;
    }

    /**
     * Handles a GET-Request with the day of the advent calendar and the part to be solved and returns a HATEOAS
     * {@code Resource<>} with the corresponding solution.
     *
     * @param day the simple day of the advent calendar to be solved
     * @param part the part of the puzzle for that day
     * @return a HATEOAS-{@code Resource<>} with the corresponding solution
     */
    @GetMapping
    public Resource getResultForASpecificDayAndPuzzlePart(@RequestParam(value = "day") Integer day,
                                                          @RequestParam(value = "part") Integer part) {

        logger.info("The results for day " + day + ", part " + part + " have been requested.");

        return new Resource<>(
                adventOfCodeService.getResultsForASpecificDayAndPuzzlePart(day, part),
                linkTo(methodOn(AdventOfCodeController.class).getResultForASpecificDayAndPuzzlePart(day, part)).withSelfRel()
        );
    }

    /**
     * Returns a HATEOAS {@code Resources<>} with an integer list of all days that have been implemented
     *
     * @return a HATEOAS-{@code Resources<>} with an integer list of all days that have been implemented
     */
    @GetMapping("/daysimplemented")
    public Resources daysImplemented() {

        logger.info("A list of implemented days has been requested.");

        return new Resources<>(
                adventOfCodeService.getDaysSolutions().stream()
                        .map(Days::getDay)
                        .sorted()
                        .collect(Collectors.toList()),
                linkTo(methodOn(AdventOfCodeController.class).daysImplemented()).withSelfRel()
        );
    }

        @GetMapping("/test")
        public String test () {

            logger.info("A list of implemented days has been requested.");

            return "Hallo";
        }

    @GetMapping("/test11")
    public String test11 () {

        logger.info("A list of implemented days has been requested.");

        return "Hallo11";
    }

}