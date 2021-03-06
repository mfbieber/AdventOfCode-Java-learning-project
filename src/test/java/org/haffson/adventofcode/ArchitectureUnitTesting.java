package org.haffson.adventofcode;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.haffson.adventofcode.days.Days;
import org.haffson.adventofcode.service.AdventOfCodeService;
import org.haffson.adventofcode.archUnitTestingUtils.ClassesPredicates;
import org.haffson.adventofcode.archUnitTestingUtils.FieldTypePredicate;
import org.haffson.adventofcode.archUnitTestingUtils.FieldsCondition;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.tngtech.archunit.core.domain.JavaClass.Predicates.implement;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.theClass;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;


@Category(ArchitectureUnitTest.class)
public class ArchitectureUnitTesting {

    private final JavaClasses allClasses = new ClassFileImporter()
            .withImportOption(new ImportOption.DontIncludeTests())
            .importPackages("org.haffson.adventofcode");

    @Test
    public void classesImplementingDaysShouldBeAnnotatedAsComponents() {
        classes().that(implement(Days.class))
                .should().beAnnotatedWith(Component.class)
                .because("we need to register the Day's solutions within the application context.")
                .check(allClasses);
    }

    @Test
    public void theListWithTheImplementedDaysShouldOnlyBeHandledInAdventOfCodeService() {
        noClasses().that().dontHaveSimpleName("AdventOfCodeService")
                .should(FieldsCondition.haveFieldsThat(FieldTypePredicate.areOfType(List.class, Days.class)))
                .because("we want only AdventOfCodeService to handle the access to the implementations of Days.")
                .check(allClasses);
        theClass(AdventOfCodeService.class)
                .should(FieldsCondition.haveFieldsThat(FieldTypePredicate.areOfType(List.class, Days.class)))
                .because("we want only AdventOfCodeService to handle the access to the implementations of Days.")
                .check(allClasses);
    }

    @Test
    public void onlyControllersAreSupposedToDoRESTCommunication() {
        noClasses().that(ClassesPredicates.dontHaveANameContaining("Controller"))
                .should().beAnnotatedWith(RestController.class)
                .because("we want only the controllers to do REST communication.")
                .check(allClasses);
    }

    @Test
    public void layersMustStickToTheirAccessLevels() {
        layeredArchitecture()
                .layer("Controller").definedBy("..controller..")
                .layer("Domain").definedBy("..domain..")
                .layer("Service").definedBy("..service..")
                .layer("Days").definedBy("..days..")

                .whereLayer("Controller").mayNotBeAccessedByAnyLayer()
                .whereLayer("Domain").mayOnlyBeAccessedByLayers("Service")
                .whereLayer("Service").mayOnlyBeAccessedByLayers("Controller")
                .whereLayer("Days").mayOnlyBeAccessedByLayers("Service")
                .check(allClasses);
    }

}