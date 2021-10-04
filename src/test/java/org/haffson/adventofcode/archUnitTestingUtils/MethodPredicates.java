package org.haffson.adventofcode.archUnitTestingUtils;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaMethod;
import com.tngtech.archunit.core.domain.JavaModifier;

public class MethodPredicates {

    /**
     * A predicate that handles a {@link JavaMethod}
     * @param className a {@code String} with the name of the Class
     * @return if the method is implemented in a specific classname
     */
    public static DescribedPredicate<JavaMethod> implementedIn(String className) {
        return new DescribedPredicate<JavaMethod>("is a " + className) {
            @Override
            public boolean apply(JavaMethod input) {
                JavaClass declaringClass = MethodPredicates.getDeclaringClass(input);
                return declaringClass.getSimpleName().toLowerCase().contains(className);
            }
        };
    }

    public static DescribedPredicate<JavaMethod> publicModifier() {
        return new DescribedPredicate<JavaMethod>("is a public method") {
            @Override
            public boolean apply(JavaMethod input) {
                return input.getModifiers().contains(JavaModifier.PUBLIC);
            }
        };
    }

    private static JavaClass getDeclaringClass(final JavaMethod method) {
        JavaClass declaringClass = method.getOwner();
        while (declaringClass.getEnclosingClass().isPresent()) {
            declaringClass = declaringClass.getEnclosingClass().get();
        }
        return declaringClass;
    }

//    private static Class<String> rawReturnType(Class<String> stringClass) {
//        return stringClass;
//    }
}