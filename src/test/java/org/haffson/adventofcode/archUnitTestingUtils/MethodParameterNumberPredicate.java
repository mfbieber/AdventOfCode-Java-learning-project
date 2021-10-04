package org.haffson.adventofcode.archUnitTestingUtils;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaMethod;

import javax.annotation.Nonnull;

/**
 * Checks if the number of parameters in a method exceeds a given threshold.
 */
public class MethodParameterNumberPredicate extends DescribedPredicate<JavaMethod> {

    private final int allowedNumberOfParameters;

    @Nonnull
    public static MethodParameterNumberPredicate moreParametersThan(final int allowedNumberOfParameters) {
        return new MethodParameterNumberPredicate(allowedNumberOfParameters);
    }

    public MethodParameterNumberPredicate(final int allowedNumberOfParameters) {
        super("more than %s parameters", allowedNumberOfParameters);
        this.allowedNumberOfParameters = allowedNumberOfParameters;
    }

    @Override
    public boolean apply(JavaMethod method) {
        return method.getParameterTypes().size() > allowedNumberOfParameters;
    }
}
