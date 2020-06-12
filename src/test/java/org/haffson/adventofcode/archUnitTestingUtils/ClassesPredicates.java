package org.haffson.adventofcode.archUnitTestingUtils;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaClass;

public class ClassesPredicates {

    /**
     * A predicate that handles a {@link JavaClass}
     * @param namePart a {@code String} with the namePart of the Class
     * @return if the name of the class contains the given namePart
     */
    public static DescribedPredicate<? super JavaClass> dontHaveANameContaining(String namePart) {
        return new DescribedPredicate<JavaClass>("don't have a namePart containing " + namePart) {
            @Override
            public boolean apply(JavaClass input) {
                return !input.getSimpleName().contains(namePart);
            }
        };
    }
}
