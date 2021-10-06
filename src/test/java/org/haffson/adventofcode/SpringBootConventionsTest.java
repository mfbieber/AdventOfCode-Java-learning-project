package org.haffson.adventofcode;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.domain.properties.CanBeAnnotated;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.haffson.adventofcode.archUnitTestingUtils.FieldsCondition;
import org.haffson.adventofcode.archUnitTestingUtils.IsAutowired;
import org.haffson.adventofcode.archUnitTestingUtils.IsSpringBootTest;
import org.haffson.adventofcode.archUnitTestingUtils.MethodCondition;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Nonnull;

import static com.tngtech.archunit.base.DescribedPredicate.not;
import static com.tngtech.archunit.core.domain.JavaMember.Predicates.declaredIn;
import static com.tngtech.archunit.core.domain.properties.CanBeAnnotated.Predicates.annotatedWith;
import static com.tngtech.archunit.lang.conditions.ArchPredicates.are;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.*;

public class SpringBootConventionsTest {

    private final JavaClasses allClasses = new ClassFileImporter()
            .withImportOption(new ImportOption.DoNotIncludeTests())
            .importPackages("org.haffson.adventofcode");

    /**
     * @return Predicate that matches Spring Boot Application classes.
     */
    @Nonnull
    private static DescribedPredicate<CanBeAnnotated> springBootApplicationClass() {
        return annotatedWith(SpringBootApplication.class).as("@%s class", SpringBootApplication.class.getSimpleName());
    }

    @Test
    public void doNotCreateBeansInApplicationClass() {
        noMethods()
            .that(are(annotatedWith(Bean.class)))
            .should(MethodCondition.be(declaredIn(springBootApplicationClass())))
            .because("this may lead to problems with missing dependencies in tests that bootstrap the Spring Boot Application only partially (e.g. @DataMongoTest tests). Simply declare your beans in separate @" + Configuration.class.getSimpleName() + " classes.")
            .check(allClasses);
    }

    @Test
    public void validateConfigurations() {
        classes()
            .that(are(annotatedWith(Configuration.class)))
            .should()
            .beAnnotatedWith(Validated.class)
            .because("otherwise mapped configuration values are *not* validated by Spring Boot.")
            .check(allClasses);
    }

    @Test
    public void doNotAutowireFields() {
        noClasses()
            .that(are(not(IsSpringBootTest.springBootTests())))
            .should(FieldsCondition.haveFieldsThat(are(IsAutowired.autowired())))
            .because("autowiring fields is not recommended and makes classes harder to test. Inject necessary objects via constructor, split classes that require too many autowired constructor parameters.")
            .check(allClasses);
    }
}