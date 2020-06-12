package org.haffson.adventofcode.archUnitTestingUtils;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaField;

import javax.annotation.Nonnull;

/**
 * Checks if a field is of a specific type and subtype.
 */
public class FieldTypePredicate extends DescribedPredicate<JavaField> {

    private final String genericTypeName;

    private <T,S> FieldTypePredicate(Class<T> type, Class<S> subtype) {
        super("are of type", type);
        this.genericTypeName = type.getTypeName() + "<" + subtype.getTypeName() + ">";
    }

    @Nonnull
    public static <T,S> FieldTypePredicate areOfType(Class<T> type, Class<S> subtype) {
        return new FieldTypePredicate(type, subtype);
    }

    @Override
    public boolean apply(JavaField field) {
        return field.reflect().getGenericType().getTypeName().equals(genericTypeName);
    }

}
