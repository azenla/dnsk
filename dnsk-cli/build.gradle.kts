plugins {
  id("dnsk.kotlin")

  application
}

dependencies {
  implementation(project(":dnsk-core"))
  implementation("com.github.ajalt.clikt:clikt:3.3.0")
}

application {
  mainClass.set("dnsk.tool.Main")
}
