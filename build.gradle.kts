import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.20"
}

group = "cl.ravenhill"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.kotest:kotest-assertions-core:5.5.0")
    implementation("io.kotest:kotest-property:5.5.0")
    implementation("io.kotest:kotest-framework-datatest:5.5.0")
    testImplementation("io.kotest:kotest-runner-junit5-jvm:5.5.0")

}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}