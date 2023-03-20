import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.8.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.reactivex:rxnetty-tcp:0.5.3")
    implementation("io.reactivex:rxnetty-http:0.5.3")
    implementation("io.netty:netty-all:4.1.90.Final")
    implementation("io.reactivex:rxnetty-common:0.5.3")
    implementation("org.mongodb:mongodb-driver-rx:1.5.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-rx2:1.6.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactive:1.6.4")

//    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-flow:1.6.4")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}