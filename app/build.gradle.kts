/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Kotlin application project to get you started.
 * For more details on building Java & JVM projects, please refer to https://docs.gradle.org/8.13/userguide/building_java_projects.html in the Gradle documentation.
 * This project uses @Incubating APIs which are subject to change.
 */

plugins {
    // Apply the org.jetbrains.kotlin.jvm Plugin to add support for Kotlin.
    alias(libs.plugins.kotlin.jvm)

    // Apply the application plugin to add support for building a CLI application in Java.
    application
    id("jacoco")
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}


tasks.withType<JacocoReport> {
    reports {
        xml.required.set(true)  // ✅ XML for CI/CD
        csv.required.set(false) // ❌ Disable CSV
        html.required.set(true) // ✅ HTML report
    }
}

dependencies {
    // This dependency is used by the application.
    implementation(libs.guava)
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
}

tasks.named<JacocoReport>("jacocoTestReport") {
    dependsOn(tasks.test)  // Ensure tests run before generating the report
}


tasks.build {
    dependsOn("test", "jacocoTestReport")  // ✅ Runs tests + coverage
}

testing {
    suites {
        // Configure the built-in test suite
        val test by getting(JvmTestSuite::class) {
            // Use Kotlin Test test framework
            useKotlinTest("2.1.0")
        }
    }
}

// Apply a specific Java toolchain to ease working on different environments.
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

application {
    // Define the main class for the application.
    mainClass = "org.example.AppKt"
}
