plugins {
    kotlin("jvm") version "2.3.0"
    application
}

group = "org.iesra"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

kotlin {
    jvmToolchain(21)
}

application {
    mainClass.set("org.iesra.MainKt")
}

tasks.test {
    useJUnitPlatform()
}