plugins {
    id("java")
    id("org.openjfx.javafxplugin") version ("0.0.7")
    id ("application")
}

group = "edu.bsu.cs"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.2")


}

tasks.test {
    useJUnitPlatform()
}

