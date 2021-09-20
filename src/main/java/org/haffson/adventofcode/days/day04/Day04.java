package org.haffson.adventofcode.days.day04;

import org.haffson.adventofcode.ProblemStatusEnum;
import org.haffson.adventofcode.days.Days;
import org.haffson.adventofcode.utils.DataLoader;
import org.haffson.adventofcode.utils.ProblemStatus;
import org.springframework.stereotype.Component;
import org.springframework.lang.NonNull;

import java.util.*;

/**
 * Implementation for <i>Day 1: Chronal Calibration</i>.
 */
@Component
public class Day04 implements Days {

    private final Map<Integer, ProblemStatusEnum> problemStatus;
    private final List<String> batchFile;

    Day04(@NonNull String filename) {
        //get data
        this.batchFile = DataLoader.getRawDataAsList("/day04/" + filename, "\n\n");
        // set problemstatus
        this.problemStatus = ProblemStatus.getProblemStatusMap(1, 2,
                ProblemStatusEnum.SOLVED, ProblemStatusEnum.SOLVED);
    }

    public List<String> getBatchFile() {
        return batchFile;
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
        return "Number of valid passports: " + getNumberValidPassports(batchFile);
    }

    // get answer puzzle day04.2:
    @Override
    public String secondPart() {
        return "Number of valid passports: " + getRestrictedNumberValidPassports(batchFile);
    }

    // answer to day04.1
    public int getNumberValidPassports(List<String> batchFile) {
        int numberOfValidPassports = 0;
        for (String passport : batchFile) {
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
    public int getRestrictedNumberValidPassports(List<String> batchFile) {
        int numberOfValidPassports = 0;

        for (String passport : batchFile) {
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
                int birthYear = Integer.parseInt(passports.get("byr"));
                if (1920 <= birthYear && birthYear <= 2002) {
                    count++;
                }
                // check issue year
                int issueYear = Integer.parseInt(passports.get("iyr"));
                if (2010 <= issueYear && issueYear <= 2020) {
                    count++;
                }
                // check expiration year
                int expirationYear = Integer.parseInt(passports.get("eyr"));
                if (2020 <= expirationYear && expirationYear <= 2030) {
                    count++;
                }
                // check body height
                String height = passports.get("hgt");
                // accept only Strings that have more than 3 letters
                if (height.length() > 3) {
                    String unit = height.substring(height.length() - 2);
                    int bodyheight = Integer.parseInt(height.substring(0, (height.length() - 2)));
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
                String hairColor = passports.get("hcl");
                String hclHash = hairColor.substring(0, 1);
                String haircolorID = hairColor.substring(1);
                if (hclHash.equals("#") && haircolorID.length() == 6 && haircolorID.matches("[0-9a-f]+")) {
                    count++;
                }
                // check eye color
                String eyeColor = passports.get("ecl");
                List<String> colors = Arrays.asList("amb", "blu", "brn", "gry", "grn", "hzl", "oth");
                if (colors.contains(eyeColor)) {
                    count++;
                }
                // check passport ID
                String passportID = passports.get("pid");
                if (passportID.length() == 9 && passportID.matches("[0-9]+")) {
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