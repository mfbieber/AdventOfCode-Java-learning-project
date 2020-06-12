package org.haffson.adventofcode.archUnitTestingUtils;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaField;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;

import javax.annotation.Nonnull;
import java.util.Objects;

/**
 * Checks *all* fields of a class against a given predicate.
 */
public class FieldsCondition extends ArchCondition<JavaClass> {

    @Nonnull
    private final DescribedPredicate<? super JavaField> predicate;

    @Nonnull
    public static FieldsCondition haveFieldsThat(@Nonnull final DescribedPredicate<? super JavaField> predicate) {
        return new FieldsCondition(predicate.as("have fields that " + predicate.getDescription()));
    }

    @Nonnull
    public static FieldsCondition areOfType(@Nonnull final DescribedPredicate<? super  JavaField> predicate) {
        return new FieldsCondition(predicate.as("are of type " + predicate.getDescription()));
    }

    public FieldsCondition(@Nonnull final DescribedPredicate<? super JavaField> predicate) {
        super(Objects.requireNonNull(predicate).getDescription());
        this.predicate = predicate;
    }

    @Override
    public void check(@Nonnull final JavaClass javaClass, @Nonnull final ConditionEvents events) {
        javaClass.getFields().forEach((field) -> {
            events.add(new SimpleConditionEvent(field, predicate.apply(field), generateEventDescriptionFor(field)));
        });
    }

    @Nonnull
    private String generateEventDescriptionFor(@Nonnull final JavaField field) {
        return String.format(
            "%s in %s",
            field.getName(),
            field.getOwner().getName()
        );
    }
}
