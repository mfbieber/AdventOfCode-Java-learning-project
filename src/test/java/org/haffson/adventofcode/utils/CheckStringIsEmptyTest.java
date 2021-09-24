package org.haffson.adventofcode.utils;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class CheckStringIsEmptyTest {

    @Test
    public void test_null_IllegalArgumentExceptionIsThrown() {
        // Given
        String expectedResult = null;
        // Then
        assertThatIllegalArgumentException().isThrownBy(() -> CheckStringIsEmpty.requireNonNullAndNonEmpty(expectedResult))
                .withMessage("The string is null or empty: " + expectedResult);
    }

    @Test
    public void test_inputString_requireNonNullAndNonEmptyIsTrue() {
        // Given
        String expectedResult = "Hallo Welt!";
        // Then
        String actualResult = CheckStringIsEmpty.requireNonNullAndNonEmpty(expectedResult);
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    public void test_emptyString_IllegalArgumentExceptionIsThrown() {
        // Given
        String expectedResult = "";
        // Then
        assertThatIllegalArgumentException().isThrownBy(() -> CheckStringIsEmpty.requireNonNullAndNonEmpty(expectedResult))
                .withMessage("The string is null or empty: " + expectedResult);
    }
}