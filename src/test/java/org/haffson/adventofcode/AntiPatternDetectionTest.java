package org.haffson.adventofcode;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.library.GeneralCodingRules;
import org.haffson.adventofcode.archUnitTestingUtils.MethodCondition;
import org.haffson.adventofcode.archUnitTestingUtils.MethodParameterNumberPredicate;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.conditions.ArchConditions.callMethod;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noMethods;


public class AntiPatternDetectionTest {

    private final JavaClasses allClasses = new ClassFileImporter()
            .importPackagesOf(Application.class);

    @Test
    public void doNotAccessStandardStreams() {
        GeneralCodingRules.NO_CLASSES_SHOULD_ACCESS_STANDARD_STREAMS
                .because("output written directly to standard streams *might* be discarded by the starter of the Java process. You should use logging instead, which allows sophisticated configuration of the output channel.")
                .check(allClasses);
    }

    @Test
    public void doNotUseSystemExit() {
        noClasses().should(callMethod(System.class, "exit", Integer.TYPE))
                .because("the JVM should not be killed from anywhere in the code. You should implement procedures for a controlled shutdown.")
                .check(allClasses);
    }

    @Test
    public void doNotUseTooManyMethodParameters() {
        noMethods()
                .should(MethodCondition.have(MethodParameterNumberPredicate.moreParametersThan(4)))
                .because("methods with too many parameters are hard to understand and complicated to test. Consider refactoring, e.g. splitting functionality or introducing Parameter Objects.")
                .check(allClasses);
    }

}
