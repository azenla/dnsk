@file:JvmName("RcodeGenerator")
package dnsk.codegen

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.csv.Csv
import java.nio.charset.StandardCharsets
import java.nio.file.Path
import java.util.*
import kotlin.io.path.readText

@Serializable
data class IanaRcodeEntry(
  @SerialName("RCODE")
  val rcode: String,
  @SerialName("Name")
  val name: String,
  @SerialName("Description")
  val description: String,
  @SerialName("Reference")
  val reference: String
)

@ExperimentalSerializationApi
fun main() {
  val ianaRcodesCsvFile = Path.of("src/codegen/resources/iana/rcodes.csv")
  val ianaRcodesCsvText = ianaRcodesCsvFile.readText(StandardCharsets.UTF_8)
  val decodedIanaRcodeEntries = Csv {
    hasHeaderRecord = true
  }.decodeFromString(
    ListSerializer(IanaRcodeEntry.serializer()),
    ianaRcodesCsvText
  )

  val expandedIanaRcodeEntries = decodedIanaRcodeEntries.map { entry ->
    if (entry.rcode.contains("-")) {
      val parts = entry.rcode.split("-")
      val range = IntRange(parts[0].toInt(), parts[1].toInt())
      return@map range.map { rcode ->
        entry.copy(rcode = rcode.toString())
      }
    } else {
      return@map listOf(entry)
    }
  }.flatten()

  val mappedWithDuplicates = TreeMap<String, MutableList<IanaRcodeEntry>>()
  for (entry in expandedIanaRcodeEntries) {
    mappedWithDuplicates.computeIfAbsent(entry.rcode) {
      mutableListOf()
    }.add(entry)
  }

  val finalizedIanaRcodeEntries = mutableListOf<IanaRcodeEntry>()
  for ((rcode, entries) in mappedWithDuplicates) {
    if (entries.size > 1) {
      finalizedIanaRcodeEntries.add(IanaRcodeEntry(
        rcode = rcode,
        name = entries.joinToString("_") { it.name },
        description = entries.joinToString("/") { it.description },
        reference = entries.joinToString("/") { it.reference }
      ))
    } else {
      finalizedIanaRcodeEntries.add(entries.single())
    }
  }
  finalizedIanaRcodeEntries.sortBy { it.rcode.toInt() }
  finalizedIanaRcodeEntries.removeIf { entry ->
    entry.name == "Unassigned" ||
      entry.name.startsWith("Reserved")
  }

  for (entry in finalizedIanaRcodeEntries) {
    //language=kotlin
    println("""
      val ${entry.name.uppercase()} = DnsRcode(${entry.rcode}, "${entry.name.uppercase()}", "${entry.description}")
    """.trimIndent())
  }

  println("""
    val ianaAssignedRcodes: Map<Int, DnsRcode> = listOf(
      ${finalizedIanaRcodeEntries.map { it.name.uppercase() }.joinToString(",")}
    ).associateBy { it.id }
  """.trimIndent())
}
