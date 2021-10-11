@file:JvmName("Main")
package dnsk.codegen

import kotlinx.serialization.ExperimentalSerializationApi
import dnsk.codegen.rcodes.main as rcodeGeneratorMain

@ExperimentalSerializationApi
fun main() {
  rcodeGeneratorMain()
}
