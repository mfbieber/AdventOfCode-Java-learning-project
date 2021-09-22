package org.haffson.adventofcode.days.day02;

import org.haffson.adventofcode.ProblemStatusEnum;
import org.haffson.adventofcode.days.Days;
import org.haffson.adventofcode.utils.CheckStringIsEmpty;
import org.haffson.adventofcode.utils.DataLoader;
import org.haffson.adventofcode.utils.ProblemStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Implementation for <i>Day 1: Chronal Calibration</i>.
 */
@Component
public class Day02 implements Days {

    private final Map<Integer, ProblemStatusEnum> problemStatus;
    private final List<String> passwordDatabase;

    Day02(@Value("filename") String filename, DataLoader dataLoader) {
        //get data
        this.passwordDatabase = dataLoader.getDataDay02("/day02/" + filename, "\n");
        // set problemstatus
        this.problemStatus = ProblemStatus.getProblemStatusMap(1, 2,
                ProblemStatusEnum.SOLVED, ProblemStatusEnum.SOLVED);
    }

    public List<String> getPasswordDatabase() {
        return passwordDatabase;
    }

    @Override
    public int getDay() {
        return 2;
    }

    @Override
    public Map<Integer, ProblemStatusEnum> getProblemStatus() {
        return problemStatus;
    }

    @Override
    public String firstPart() {
        return "Part 1 answer: " + getNumberPassword1(passwordDatabase);
    }

    @Override
    public String secondPart() {
        return "Part 2 answer: " + getNumberPassword2(passwordDatabase);
    }

    /**
     * Method getData() to get min and max number, letter and password from raw data
     * First, create class Data to avoid having multiple return values
     */
    public static class Data {
        private final int min;
        private final int max;
        private final String letter;
        private final String password;

        public Data(int min, int max, @NonNull String letter, @NonNull String password) {
            this.min = min;
            this.max = max;
            this.letter = CheckStringIsEmpty.requireNonNullAndNonEmpty(letter);
            this.password = CheckStringIsEmpty.requireNonNullAndNonEmpty(password);
        }

        public static List<Data> getPasswordData(List<String> passwordDatabase) {

            List<Data> PasswordDataArrayList = new ArrayList<>();
            for (String line : passwordDatabase) {
                // match patterns to determine min and max number of the letter in password,
                // the searched letter and password itself
                String pattern = "(\\d+)-(\\d+)\\s+(\\w):\\s+(\\w*)";
                int min = Integer.parseInt(line.replaceAll(pattern, "$1"));
                int max = Integer.parseInt(line.replaceAll(pattern, "$2"));
                String letter = line.replaceAll(pattern, "$3");
                String password = line.replaceAll(pattern, "$4");

                PasswordDataArrayList.add(new Data(min, max, letter, password));
            }
            return PasswordDataArrayList;
        }

    }

    /**
     * Primary method for Day 1, Part 1.
     * Gets the number of correct passwords from a list
     *
     * @return the number of correct passwords in list
     */
    public int getNumberPassword1(final List<String> passwordDatabase) {
        List<Data> data = Data.getPasswordData(passwordDatabase);
        int countCorrPasswords = 0; // number of correct passwords in list

        // loop through passwords
        for (Data datum : data) {
            int count = 0;  // number of letter appearance in password
            // count how often specific letter appears in pwd
            for (int j = 0; j < datum.password.length(); j++) {
                if (datum.password.charAt(j) == datum.letter.charAt(0)) count++;
            }
            // check if count meets criteria for correct password
            if (count >= datum.min && count <= datum.max) {
                countCorrPasswords++;
            }
        }
        return countCorrPasswords;
    }

    public int getNumberPassword2(final List<String> passwordDatabase) {

        List<Data> data = Data.getPasswordData(passwordDatabase);
        int countCorrPasswords = 0; // answer to puzzle day 2.2: number of correct passwords in list

        // loop through passwords
        for (Data datum : data) {
            boolean onlyFirstPosition = datum.password.charAt(datum.min - 1) == datum.letter.charAt(0) &&
                    datum.password.charAt(datum.max - 1) != datum.letter.charAt(0);
            boolean onlySecondPosition = datum.password.charAt(datum.max - 1) == datum.letter.charAt(0) &&
                    datum.password.charAt(datum.min - 1) != datum.letter.charAt(0);
            // letter must appear only once (either only(!) at first or only(!) at second position)
            if ((onlyFirstPosition && !onlySecondPosition) || (!onlyFirstPosition && onlySecondPosition)) {
                countCorrPasswords++;
            }
        }
        return countCorrPasswords;
    }
}