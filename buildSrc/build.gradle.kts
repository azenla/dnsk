plugins {
  `kotlin-dsl`
}

repositories {
  gradlePluginPortal()
}

dependencies {
  implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.31")
  implementation("org.jetbrains.kotlin:kotlin-serialization:1.5.31")
  implementation("com.adarshr:gradle-test-logger-plugin:3.0.0")
  implementation("gradle.plugin.com.github.johnrengelman:shadow:7.1.0")
}
