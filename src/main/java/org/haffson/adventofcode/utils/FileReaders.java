package org.haffson.adventofcode.utils;

import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * File readers to load and parse input files for the puzzles.
 */
@Component
public class FileReaders {

    public int[] readFileIntoArrayOfIntegers(String path){
        List<String> data = new ArrayList<>();
        InputStream inputStream = FileReaders.class.getResourceAsStream(path);
        Scanner scanner = new Scanner(inputStream);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if(!line.isEmpty()){
              data.add(line);
            }
        }
        scanner.close();
        return data.stream().mapToInt(Integer::parseInt).toArray();
    }
}

