package org.haffson.adventofcode.archUnitTestingUtils;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaAccess;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaMethod;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;
import com.tngtech.archunit.lang.conditions.ArchPredicates;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Stream;

public class MethodCondition extends ArchCondition<JavaMethod> {

    /**
     * A predicate that can handle a {@link JavaMethod} object.
     */
    private final DescribedPredicate<? super JavaMethod> predicate;

    /**
     * Factory method that can be imported statically to get more readable rule chains.
     * e.g.:
     * no(methods()).that(...).should(beReturning(...))
     *
     * @return The method transformer.
     */
    public static MethodCondition beReturning(final DescribedPredicate<? super JavaMethod> predicate) {
        return new MethodCondition(ArchPredicates.be(Objects.requireNonNull(predicate)));
    }

    /**
     * Factory method that can be imported statically to get more readable
     * rule chains.
     *
     * Example:
     *
     *      no(...).that(...).should(have(...))
     *
     * @param predicate The predicate that is applied to the methods.
     * @return The condition object.
     */
    @Nonnull
    public static MethodCondition have(@Nonnull final DescribedPredicate<? super JavaMethod> predicate) {
        return new MethodCondition(ArchPredicates.have(Objects.requireNonNull(predicate)));
    }

    /**
     * Factory method that can be imported statically to get more readable
     * rule chains.
     *
     * Example:
     *
     *      no(...).that(...).should(be(...))
     *
     * @param predicate The predicate that is applied to the methods.
     * @return The condition object.
     */
    @Nonnull
    public static MethodCondition be(@Nonnull final DescribedPredicate<? super JavaMethod> predicate) {
        return new MethodCondition(ArchPredicates.be(Objects.requireNonNull(predicate)));
    }

    public MethodCondition(final DescribedPredicate<? super JavaMethod> predicate) {
        super(Objects.requireNonNull(predicate).getDescription());
        this.predicate = predicate;
    }

    @Override
    public void check(final JavaMethod method, final ConditionEvents events) {
        events.add(new SimpleConditionEvent(method, predicate.apply(method), generateEventDescriptionFor(method)));
    }

    private String generateEventDescriptionFor(final JavaMethod method) {
        return method.getFullName() + " " + getDeclaringClass(method).getName() + ":" + determineLineNumberOfDeclaration(method);
    }

    /**
     * Determines the class that declares the given method. In case it is declared in an inner class, the parent
     * class is returned.
     *
     * @param method
     * @return the class that contains the method declaration
     */
    private JavaClass getDeclaringClass(final JavaMethod method) {
        JavaClass declaringClass = method.getOwner();
        while (declaringClass.getEnclosingClass().isPresent()) {
            declaringClass = declaringClass.getEnclosingClass().get();
        }
        return declaringClass;
    }

    /**
     * Trying to determine the line number of the method declaration. Usually a method calls another
     * method, the constructor or accesses fields in the first line after the declaration.
     *
     * @param method
     * @return the line number of the method declaration
     */
    private int determineLineNumberOfDeclaration(final JavaMethod method) {
        final int lineOfFirstStatement = Stream.of(
                method.getMethodCallsFromSelf(),
                method.getConstructorCallsFromSelf(),
                method.getFieldAccesses()
        )
                .flatMap(Collection::stream)
                .mapToInt(JavaAccess::getLineNumber)
                .min()
                .orElse(0);
        return lineOfFirstStatement - 1;
    }
}