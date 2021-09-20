package org.haffson.adventofcode.utils;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class DataLoader {

    private DataLoader() {
    }

    public static List<String> getRawDataAsList(@NonNull String path, @NonNull String delimiter) {
        InputStream dataIn = Objects.requireNonNull(DataLoader.class.getResourceAsStream("/data"
                        + CheckStringisEmpty.requireNonNullAndNonEmpty(path)),
                "Data InputStream must not be null.");
        List<String> rawData;
        try (Scanner scan = new Scanner(dataIn)) {
            rawData = new ArrayList<>();
            while (scan.hasNext()) {
                scan.useDelimiter(CheckStringisEmpty.requireNonNullAndNonEmpty(delimiter));
                rawData.add(scan.next());
            }
        }
        return rawData;
    }

    public static List<Integer> getDataDay01(@NonNull String path, @NonNull String delimiter) {
        return getRawDataAsList(path, delimiter).stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public static List<String> getDataDay02(@NonNull String path, @NonNull String delimiter) {
        return getRawDataAsList(path, delimiter);
    }

    public static List<String> getDataDay03(@NonNull String path, @NonNull String delimiter) {
        return getRawDataAsList(path, delimiter);
    }

    public static List<String> getDataDay04(@NonNull String path, @NonNull String delimiter) {
        return getRawDataAsList(path, delimiter);
    }

    public static List<String> getDataDay05(@NonNull String path, @NonNull String delimiter) {
        return getRawDataAsList(path, delimiter);
    }

    public static List<String> getDataDay06(@NonNull String path, @NonNull String delimiter) {
        return getRawDataAsList(path, delimiter);
    }
}