plugins {
    application
    id("com.github.ben-manes.versions") version "0.51.0"
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

application {
    mainClass = "hexlet.code.App"
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

tasks.named<com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask>("dependencyUpdates") {
    checkForGradleUpdate = true
    outputFormatter = "html"
    outputDir = "build/dependencyUpdates"
    reportfileName = "report"
}
