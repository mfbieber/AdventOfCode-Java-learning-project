package org.haffson.adventofcode.archUnitTestingUtils;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.domain.JavaMethod;
import com.tngtech.archunit.core.domain.JavaModifier;
import com.tngtech.archunit.core.domain.properties.HasReturnType;
import com.tngtech.archunit.lang.AbstractClassesTransformer;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.tngtech.archunit.core.domain.properties.HasReturnType.Predicates.rawReturnType;

public class MethodTransformer extends AbstractClassesTransformer<JavaMethod> {
    public MethodTransformer() {
        super("methods");
    }

    /**
     * Factory method that can be imported statically to get more readable rule chains.
     * e.g.:
     * no(methods()).that(...).should(...)
     *
     * @return The method transformer.
     */
    public static MethodTransformer methods() {
        return new MethodTransformer();
    }

    /**
     * A predicate that handles a {@link JavaMethod}
     *
     * @param className a {@code String} with the name of the Class
     * @return if the method is implemented in a specific classname
     */
    public static DescribedPredicate<JavaMethod> implementedIn(String className) {
        return new DescribedPredicate<JavaMethod>("is a " + className) {
            @Override
            public boolean apply(JavaMethod input) {
                JavaClass declaringClass = MethodTransformer.getDeclaringClass(input);
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

    public static DescribedPredicate<HasReturnType> string() {
        return rawReturnType(String.class);
    }

    public Iterable<JavaMethod> doTransform(final JavaClasses classes) {
        return StreamSupport.stream(classes.spliterator(), false)
                .flatMap(javaClass -> javaClass.getMethods().stream())
                .collect(Collectors.toList());
    }

}