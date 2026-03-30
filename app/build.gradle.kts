plugins {
    id("application")
    id("checkstyle")
    id("jacoco")
    id("com.github.ben-manes.versions") version "0.51.0"
    id("org.sonarqube") version "4.4.1.3373"
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

application {
    mainClass = "hexlet.code.App"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.2")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.17.2")
    implementation("info.picocli:picocli:4.7.6")
    
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.assertj:assertj-core:3.25.3")
}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required.set(true)
        csv.required.set(false)
        html.required.set(true)
    }
}

checkstyle {
    toolVersion = "10.12.0"
    configFile = file("config/checkstyle/checkstyle.xml")
    configProperties["checkstyle.cache.file"] = file("build/checkstyle/cache")
}

tasks.withType<Checkstyle> {
    reports {
        xml.required.set(false)
        html.required.set(true)
    }
}
