plugins {
    id("java")
    id("application")
    id("org.openjfx.javafxplugin") version "0.0.10"
}

group = "edu.bsu.cs"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    implementation("org.apiguardian:apiguardian-api:1.1.2")
    implementation(platform("org.junit:junit-bom:5.9.1"))
    implementation("org.mockito:mockito-core:5.11.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    testImplementation("org.junit.jupiter:junit-jupiter-params")
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.junit.platform:junit-platform-commons")
    testImplementation("org.opentest4j:opentest4j:1.2.0")
}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass.set("edu.bsu.cs.BedroomDesignerCLI")
}

javafx {
    version = "17.0.2"
    modules("javafx.controls", "javafx.fxml")
}