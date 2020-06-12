package org.haffson.adventofcode.archUnitTestingUtils;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.properties.CanBeAnnotated;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Nonnull;

public class IsAutowired extends DescribedPredicate<CanBeAnnotated> {

    /**
     * Factory method for the {@link IsAutowired} predicate.
     *
     * Allows to construct more readable method chains.
     *
     * @return Autowired predicate.
     */
    @Nonnull
    public static IsAutowired autowired() {
        return new IsAutowired();
    }

    public IsAutowired() {
        super("autowired");
    }

    @Override
    public boolean apply(@Nonnull final CanBeAnnotated fieldOrMethod) {
        return fieldOrMethod.isAnnotatedWith(Autowired.class);
    }
}
