import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  kotlin("jvm")
  kotlin("plugin.serialization")
}

group = "io.dnsk"
version = "0.0.1"

repositories {
  mavenCentral()
}

dependencies {
  api("org.jetbrains.kotlin:kotlin-stdlib:1.5.31")
  api("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.5.31")
  api("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0")

  @Suppress("GradlePackageUpdate")
  api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")

  implementation("org.slf4j:slf4j-api:1.7.32")
  implementation("org.slf4j:slf4j-simple:1.7.32")

  testImplementation(kotlin("test", "1.5.31"))
  testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
  testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

java.sourceCompatibility = JavaVersion.VERSION_11
java.targetCompatibility = JavaVersion.VERSION_11

tasks.withType<KotlinCompile> {
  kotlinOptions.jvmTarget = "11"
}

tasks.test {
  useJUnitPlatform()
  reports.html.required.set(true)
  reports.junitXml.required.set(true)
}
