plugins {
    application
    checkstyle
    jacoco
    id("org.sonarqube") version "4.4.1.3373"
}

application {
    mainClass.set("hexlet.code.App")
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.fasterxml.jackson.core:jackson-databind:2.16.1")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.16.1")
    implementation("info.picocli:picocli:4.7.5")
    annotationProcessor("info.picocli:picocli-codegen:4.7.5")
    
    testImplementation(platform("org.junit:junit-bom:5.10.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

tasks.compileJava {
    options.release = 17
    options.encoding = "UTF-8"
    options.compilerArgs.add("-Aproject=${project.group}/${project.name}")
}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
}

checkstyle {
    toolVersion = "10.12.1"
    configFile = file("${project.rootDir}/config/checkstyle/checkstyle.xml")
}

jacoco {
    toolVersion = "0.8.11"
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required = true
        html.required = true
    }
}

sonarqube {
    properties {
        property("sonar.projectKey", "java-project-71")
        property("sonar.projectName", "java-project-71")
        property("sonar.host.url", "https://sonarcloud.io")
        property("sonar.organization", "YOUR_ORG_NAME")
        property("sonar.coverage.jacoco.xmlReportPaths", "build/reports/jacoco/test/jacocoTestReport.xml")
        property("sonar.sourceEncoding", "UTF-8")
    }
}

sonarqube {
    properties {
        property("sonar.projectKey", "java-project-71")
        property("sonar.projectName", "java-project-71")
        property("sonar.host.url", "https://sonarcloud.io")
        property("sonar.organization", "YOUR_ORG_NAME")
        property("sonar.coverage.jacoco.xmlReportPaths", "build/reports/jacoco/test/jacocoTestReport.xml")
    }
}
