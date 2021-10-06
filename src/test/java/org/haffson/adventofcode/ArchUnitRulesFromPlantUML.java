package org.haffson.adventofcode;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import java.net.URL;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.plantuml.PlantUmlArchCondition.Configurations.consideringOnlyDependenciesInDiagram;
import static com.tngtech.archunit.library.plantuml.PlantUmlArchCondition.adhereToPlantUmlDiagram;


public class ArchUnitRulesFromPlantUML {

    URL myDiagram = getClass().getClassLoader().getResource("adventOfCodeArchitecture.puml");

    private final JavaClasses allClasses = new ClassFileImporter()
            .withImportOption(new ImportOption.DoNotIncludeTests())
            .importPackages("org.haffson.adventofcode");

    @Test
    public void classesShouldAdhereToPlantUmlDiagram() {
        classes().should(adhereToPlantUmlDiagram(myDiagram, consideringOnlyDependenciesInDiagram()))
                .check(allClasses);
        ;
    }

}