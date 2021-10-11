package dnsk.core

interface DnsClient {
  suspend fun query(message: DnsMessage)
}
