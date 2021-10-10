import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "io.dnsk"
version = "0.0.1"

plugins {
  kotlin("jvm") version "1.5.31"
  kotlin("plugin.serialization") version "1.5.31"

  id("com.github.johnrengelman.shadow") version "7.1.0"

  application
}

repositories {
  mavenCentral()
}

configurations {
  create("codegenCompile") {
    extendsFrom(implementation.get(), api.get())
  }
}

sourceSets {
  create("codegen") {
    java {
      compileClasspath += configurations["codegenCompile"]
    }
  }
}

dependencies {
  api("org.jetbrains.kotlin:kotlin-stdlib:1.5.31")
  api("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.5.31")
  api("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0")

  @Suppress("GradlePackageUpdate")
  api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")

  implementation("com.github.ajalt.clikt:clikt:3.3.0")

  implementation("org.slf4j:slf4j-api:1.7.32")
  implementation("org.slf4j:slf4j-simple:1.7.32")

  testImplementation(kotlin("test", "1.5.31"))
  testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
  testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")

  add("codegenCompile", "de.brudaswen.kotlinx.serialization:kotlinx-serialization-csv:2.0.0")
}

tasks.withType<KotlinCompile> {
  kotlinOptions.jvmTarget = "11"
}

tasks.test {
  useJUnitPlatform()
  reports.html.required.set(true)
  reports.junitXml.required.set(true)
}

application {
  mainClass.set("dnsk.tool.Main")
}

tasks.withType<Wrapper> {
  gradleVersion = "7.2"
  distributionType = Wrapper.DistributionType.ALL
}
