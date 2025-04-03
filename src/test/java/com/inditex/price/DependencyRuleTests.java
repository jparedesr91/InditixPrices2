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
            "java..",
            "lombok..",
            "org.junit..",
            "org.mockito..",
            "org.springframework.test.."
        )
        .check(new ClassFileImporter()
            .importPackages("com.inditex.price.."));
  }

    @Test
    void applicationDoesNotDependOnOutside() {
        noClasses()
                .that()
                .resideInAPackage("com.inditex.price.application..")
                .should()
                .dependOnClassesThat()
                .resideOutsideOfPackages(
                        "com.inditex.price.domain..",
                        "com.inditex.price.application..",
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
