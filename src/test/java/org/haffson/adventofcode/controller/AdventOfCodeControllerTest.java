package org.haffson.adventofcode.controller;

import org.haffson.adventofcode.days.Days;
import org.haffson.adventofcode.service.AdventOfCodeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AdventOfCodeController.class)
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class AdventOfCodeControllerTest {

    @MockBean
    AdventOfCodeService adventOfCodeService;

    private String baseUrl = "/api/adventOfCode";

    private MediaType contentType = new MediaType("application", "hal+json", Charset.forName("UTF-8"));

    private Integer day1 = 1;
    private Integer part1 = 1;
    private String resultDay1Part1 = "Product 1: " + 326211;

    @Autowired
    private MockMvc mvc;


    @BeforeEach
    void setup() {
        Days day01Stub = Mockito.mock(Days.class);
        Days day02Stub = Mockito.mock(Days.class);
        Mockito.when(day01Stub.getDay()).thenReturn(1);
        Mockito.when(day02Stub.getDay()).thenReturn(2);

        List<Days> daysImplementedList = new LinkedList<>();
        daysImplementedList.add(day01Stub);
        daysImplementedList.add(day02Stub);

        Mockito.when(adventOfCodeService.getResultsForASpecificDayAndPuzzlePart(day1, part1))
                .thenReturn(resultDay1Part1);
        Mockito.when(adventOfCodeService.getDaysSolutions())
                .thenReturn(daysImplementedList);
    }


    @Test
    public void testGetResultForASpecificDayAndPuzzlePart() throws Exception {
        mvc.perform(get(baseUrl + "/" + "?day=" + day1 + "&part=" + part1)
                .contentType(contentType))
                .andExpect(status().isOk())
                .andExpect(jsonPath("day", is(1)))
                .andExpect(jsonPath("part", is(1)))
                .andExpect(jsonPath("answer", is("Product 1: " + 326211)))
                .andExpect(jsonPath("$._links.self.href", is("http://localhost:8080" + baseUrl + "/" + "?day=" + day1 + "&part=" + part1)))
                .andDo(document("getResultForASpecificDayAndPuzzlePart",
                        preprocessResponse(prettyPrint()),
                        responseFields(getResultForASpecificDayAndPuzzlePart("")))
                );

    }

    @Test
    public void testDaysImplemented() throws Exception {

        List<Integer> daysImplementedIntegerList = new LinkedList<>(Arrays.asList(1, 2));

        mvc.perform(get(baseUrl + "/daysimplemented")
                .contentType(contentType))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.integerList", is(daysImplementedIntegerList)))
                .andExpect(jsonPath("$._links.self.href", is("http://localhost:8080" + baseUrl + "/daysimplemented")))
                .andDo(document(
                        "daysImplemented",
                        preprocessResponse(prettyPrint()),
                        responseFields(daysImplemented(""))
                        )
                );
    }

    private ArrayList<FieldDescriptor> getResultForASpecificDayAndPuzzlePart(String path) {
        String pathString;
        if (path.isEmpty()) {
            pathString = "";
        } else pathString = path;

        ArrayList<FieldDescriptor> fieldDescriptorList = new ArrayList<>();
        fieldDescriptorList.add(fieldWithPath(pathString + "day")
                .description("Specific day of the puzzle of the AdventOfCode calendar"));
        fieldDescriptorList.add(fieldWithPath(pathString + "part")
                .description("Specific day's part of the puzzle of the AdventOfCode calendar"));
        fieldDescriptorList.add(fieldWithPath(pathString + "answer")
                .description("Result of the Puzzle for a specific day and part of the AdventOfCode calendar"));
        fieldDescriptorList.add(fieldWithPath(pathString + "_links.self.href")
                .description("Self link to the query for the specific solution for a day and part"));

        return fieldDescriptorList;
    }

    private ArrayList<FieldDescriptor> daysImplemented(String path) {
        String pathString;
        if (path.isEmpty()) {
            pathString = "";
        } else pathString = path;

        ArrayList<FieldDescriptor> fieldDescriptorList = new ArrayList<>();
        fieldDescriptorList.add(fieldWithPath(pathString + "_embedded.integerList")
                .description("List of all implemented days"));
        fieldDescriptorList.add(fieldWithPath(pathString + "_links.self.href")
                .description("Self link to the query for the specific solution for a day and part"));

        return fieldDescriptorList;
    }
}