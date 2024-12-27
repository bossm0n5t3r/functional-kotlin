plugins {
    kotlin("jvm") version "2.1.0"
    id("org.jlleitschuh.gradle.ktlint") version "12.1.2"
}

group = "functional"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("reflect"))
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}

ktlint {
    version.set("1.5.0")
}
