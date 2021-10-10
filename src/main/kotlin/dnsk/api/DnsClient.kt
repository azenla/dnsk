package dnsk.api

interface DnsClient {
  suspend fun query(message: DnsMessage)
}
