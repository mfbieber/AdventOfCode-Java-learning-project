package org.haffson.adventofcode.days.day04;

import org.haffson.adventofcode.ProblemStatusEnum;
import org.haffson.adventofcode.days.Days;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.*;

/**
 * Implementation for <i>Day 1: Chronal Calibration</i>.
 */
@Component
public class Day04 implements Days {

    /**
     * The puzzle status {@code HashMap}
     */
    private final Map<Integer, ProblemStatusEnum> problemStatus;

    // Read content of test file (puzzle part 1)
    public InputStream testResource = getClass().getResourceAsStream("/data/day04/day04_testdata.txt");

    // Read content of test file (puzzle part 2)
    public InputStream testResource2 = getClass().getResourceAsStream("/data/day04/day04_testdata2.txt");

    // Read content of input file (real data)
    public InputStream resource = getClass().getResourceAsStream("/data/day04/input_day04.txt");

    @Autowired
    Day04() {
        this.problemStatus = new HashMap<>();
        this.problemStatus.put(1, ProblemStatusEnum.SOLVED);
        this.problemStatus.put(2, ProblemStatusEnum.SOLVED);
    }

    // get current Day
    @Override
    public int getDay() {
        return 4;
    }

    @Override
    public Map<Integer, ProblemStatusEnum> getProblemStatus() {
        return problemStatus;
    }

    // get answer puzzle day04.1:
    @Override
    public String firstPart() {
        return "Number of valid passports: " + getNumberValidPassports(getRawData(resource));
    }

    // get answer puzzle day04.2:
    @Override
    public String secondPart() {
        return "Number of valid passports: " + getRestrictedNumberValidPassports(getRawData(resource));
    }

    // read raw data and transform it to String[]
    public String[] getRawData(InputStream resource) {

        ArrayList<String> rawData;
        try (Scanner scan = new Scanner(resource)) {
            rawData = new ArrayList<>();

            while (scan.hasNext()) {
                scan.useDelimiter("\n\n");
                rawData.add(scan.next());
            }
        }

        String[] rawData_array = new String[rawData.size()];
        for (int i = 0; i < rawData.size(); i++) rawData_array[i] = rawData.get(i);

        return rawData_array;
    }

    // answer to day04.1
    public int getNumberValidPassports(String[] rawData_array) {
        int numberOfValidPassports = 0;
        for (String passport : rawData_array) {
            Set<String> validKeys = new HashSet<>(Arrays.asList("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"));
            String[] splitPassport = passport.split("[\\s+]");
            for (String field : splitPassport) {
                String[] parts = field.split(":");
                validKeys.remove(parts[0]);
            }
            if (validKeys.size() == 0) {
                numberOfValidPassports++;
            }
        }
        return numberOfValidPassports;
    }

    // answer to day04.2
    public int getRestrictedNumberValidPassports(String[] rawData_array) {
        int numberOfValidPassports = 0;

        for (String passport : rawData_array) {
            int count = 0;
            Map<String, String> passports = new HashMap<>();
            Set<String> validKeys = new HashSet<>(Arrays.asList("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"));
            String[] splitPassport = passport.split("[\\s+]");
            for (String field : splitPassport) {
                String[] parts = field.split(":");
                passports.put(parts[0], parts[1]);
                validKeys.remove(parts[0]);
            }
            // check only passports that contain all keys
            if (validKeys.size() == 0) {
                // check birth year
                int byr = Integer.parseInt(passports.get("byr"));
                if (1920 <= byr && byr <= 2002) {
                    count++;
                }
                // check issue year
                int iyr = Integer.parseInt(passports.get("iyr"));
                if (2010 <= iyr && iyr <= 2020) {
                    count++;
                }
                // check expiration year
                int eyr = Integer.parseInt(passports.get("eyr"));
                if (2020 <= eyr && eyr <= 2030) {
                    count++;
                }
                // check body height
                String hgt = passports.get("hgt");
                // accept only Strings that have more than 3 letters
                if (hgt.length() > 3) {
                    String unit = hgt.substring(hgt.length() - 2);
                    int bodyheight = Integer.parseInt(hgt.substring(0, (hgt.length() - 2)));
                    if (unit.equals("cm")) {
                        if (bodyheight >= 150 && bodyheight <= 193) {
                            count++;
                        }
                    } else if (unit.equals("in")) {
                        if (bodyheight >= 59 && bodyheight <= 76) {
                            count++;
                        }
                    }
                }
                // check hair color
                String hcl = passports.get("hcl");
                String hclHash = hcl.substring(0, 1);
                String haircolorID = hcl.substring(1);
                if (hclHash.equals("#") && haircolorID.length() == 6 && haircolorID.matches("[0-9a-f]+")) {
                    count++;
                }
                // check eye color
                String ecl = passports.get("ecl");
                List<String> colors = Arrays.asList("amb", "blu", "brn", "gry", "grn", "hzl", "oth");
                if (colors.contains(ecl)) {
                    count++;
                }
                // check passport ID
                String pid = passports.get("pid");
                if (pid.length() == 9 && pid.matches("[0-9]+")) {
                    count++;
                }
                // if all keys are present and all values are valid to above 7 rules then count == 7
                if (count == 7) {
                    numberOfValidPassports++;
                }
            }
        }
        return numberOfValidPassports;
    }
}