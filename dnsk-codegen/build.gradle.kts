plugins {
  id("dnsk.kotlin")

  application
}

dependencies {
  implementation("de.brudaswen.kotlinx.serialization:kotlinx-serialization-csv:2.0.0")
}

application {
  mainClass.set("dnsk.codegen.Main")
}

/*
(tasks.getByName("run") as JavaExec).apply {
  workingDir(projectDir)
}
*/
