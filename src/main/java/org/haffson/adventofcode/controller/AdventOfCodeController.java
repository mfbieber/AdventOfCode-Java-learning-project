package org.haffson.adventofcode.controller;

import org.haffson.adventofcode.days.Days;
import org.haffson.adventofcode.service.AdventOfCodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


/**
 * The main REST controller for the <i>Advent Of Code 2018</i> Application.
 * It is used to handle calls to {@code /api/adventOfCode}.
 *
 * @author Michelle Fernandez Bieber
 */
@RestController
@RequestMapping(value = "/api/adventOfCode")
public class AdventOfCodeController {

    /**
     * Adds a logger to the controller
     */
    private static final Logger logger = LoggerFactory.getLogger(AdventOfCodeController.class);

    /**
     * Implements the {@link AdventOfCodeService}.
     */
    private final AdventOfCodeService adventOfCodeService;

    /**
     * {@code @Autowired} constructor of this controller.
     *
     * @param adventOfCodeService {@code @Autowired} adventOfCodeService
     */
    public AdventOfCodeController(AdventOfCodeService adventOfCodeService) {
        this.adventOfCodeService = adventOfCodeService;
    }

    /**
     * EntityModel<String> no longer writable:
     * DayModel() is wrapper class for String answer
     */
    private static class DayModel {
        private final Integer day;
        private final Integer part;
        private final String answer;

        public DayModel(Integer day, Integer part, String answer) {
            this.day = day;
            this.part = part;
            this.answer = answer;
        }

        public Integer getDay() {
            return day;
        }

        public Integer getPart() {
            return part;
        }

        public String getAnswer() {
            return answer;
        }
    }

    /**
     * Handles a GET-Request with the day of the advent calendar and the part to be solved and returns a HATEOAS
     * {@code EntityModel<>} with the corresponding solution.
     * <p>
     * //     * @param day  the simple day of the advent calendar to be solved
     * //     * @param part the part of the puzzle for that day
     *
     * @return a HATEOAS-{@code EntityModel<>} with the corresponding solution
     */
    @GetMapping(value = "/")
    public EntityModel<DayModel> getResultForASpecificDayAndPuzzlePart(@RequestParam(value = "day", defaultValue = "1") Integer day,
                                                                       @RequestParam(value = "part", defaultValue = "1") Integer part) {

        logger.info("The results for day {}, part {} have been requested.", day, part);

        String answer = adventOfCodeService.getResultsForASpecificDayAndPuzzlePart(day, part);
        Link link = linkTo(methodOn(AdventOfCodeController.class).getResultForASpecificDayAndPuzzlePart(day, part)).withSelfRel();
        DayModel dayModel = new DayModel(day, part, answer);
        return EntityModel.of(dayModel, link);
    }



    /**
     * Returns a HATEOAS {@code CollectionModel<>} with an integer list of all days that have been implemented
     *
     * @return a HATEOAS-{@code CollectionModel<>} with an integer list of all days that have been implemented
     */
    @GetMapping("/daysimplemented")
    public CollectionModel<Integer> daysImplemented() {

        logger.info("A list of implemented days (sorted) has been requested.");

        List<Integer> daysImplemented = adventOfCodeService.getDaysSolutions().stream()
                .map(Days::getDay)
                .collect(Collectors.toList());
        Link link = linkTo(methodOn(AdventOfCodeController.class).daysImplemented()).withSelfRel();

        return CollectionModel.of(daysImplemented, link);
    }
}