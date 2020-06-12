package org.haffson.adventofcode.archUnitTestingUtils;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaClass;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Nonnull;

/**
 * Checks if a class is a Spring Boot (integration) test.
 */
public class IsSpringBootTest extends DescribedPredicate<JavaClass> {

    /**
     * Factory method that can be used to get more readable method chains.
     *
     * @return The predicate.
     */
    @Nonnull
    public static IsSpringBootTest springBootTests() {
        return new IsSpringBootTest();
    }

    public IsSpringBootTest() {
        super("a Spring Boot Test");
    }

    @Override
    public boolean apply(@Nonnull final JavaClass javaClass) {
        return javaClass.isAnnotatedWith(SpringBootTest.class);
    }
}
