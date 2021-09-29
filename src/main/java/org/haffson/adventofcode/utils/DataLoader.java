package org.haffson.adventofcode.utils;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class DataLoader {

//    public DataLoader() {
//    }

    private List<String> getRawDataAsList(@NonNull String path, @NonNull String delimiter) {
        CheckStringIsEmpty.requireNonNullAndNonEmpty(path);
        CheckStringIsEmpty.requireNonNullAndNonEmpty(delimiter);

        InputStream dataIn = Objects.requireNonNull(DataLoader.class.getResourceAsStream("/data" + path),
                "Data InputStream must not be null: " + path);
        List<String> rawData;
        try (Scanner scan = new Scanner(dataIn)) {
            rawData = new ArrayList<>();
            while (scan.hasNext()) {
                scan.useDelimiter(delimiter);
                rawData.add(scan.next());
            }
        }
        return rawData;
    }

    public List<Integer> getDataDay01() {
        return getRawDataAsList("/day01/input_day01.txt", "\n").stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public List<String> getDataDay02() {
        return getRawDataAsList("/day02/input_day02.txt", "\n");
    }

    public List<String> getDataDay03() {
        return getRawDataAsList("/day03/input_day03.txt", "\n");
    }

    public List<String> getDataDay04() {
        return getRawDataAsList("/day04/input_day04.txt", "\n\n");
    }

    public List<String> getDataDay05() {
        return getRawDataAsList("/day05/input_day05.txt", "\n");
    }

    public List<String> getDataDay06() {
        return getRawDataAsList("/day06/input_day06.txt", "\n");
    }
}