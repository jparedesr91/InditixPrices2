package com.inditex.price;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.junit.jupiter.api.Test;

class DependencyRuleTests {

  @Test
  void domainDoesNotDependOnOutside() {
    noClasses()
        .that()
        .resideInAPackage("com.inditex.price.domain..")
        .should()
        .dependOnClassesThat()
        .resideOutsideOfPackages(
            "com.inditex.price.domain..",
            "lombok..",
            "jakarta.validation..",
            "org.mapstruct..",
            "java..",
            "org.junit..",
            "org.mockito..",
            "org.springframework.test.."
        )
        .check(new ClassFileImporter()
            .importPackages("com.inditex.price.."));
  }

}
