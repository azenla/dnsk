package dnsk.core

class DnsRcode(val id: Int, val name: String = "RCODE_${id}", val description: String = "") {
  companion object {
    // START GENERATED CODE
    val NOERROR = DnsRcode(0, "NOERROR", "No Error")
    val FORMERR = DnsRcode(1, "FORMERR", "Format Error")
    val SERVFAIL = DnsRcode(2, "SERVFAIL", "Server Failure")
    val NXDOMAIN = DnsRcode(3, "NXDOMAIN", "Non-Existent Domain")
    val NOTIMP = DnsRcode(4, "NOTIMP", "Not Implemented")
    val REFUSED = DnsRcode(5, "REFUSED", "Query Refused")
    val YXDOMAIN = DnsRcode(6, "YXDOMAIN", "Name Exists when it should not")
    val YXRRSET = DnsRcode(7, "YXRRSET", "RR Set Exists when it should not")
    val NXRRSET = DnsRcode(8, "NXRRSET", "RR Set that should exist does not")
    val NOTAUTH = DnsRcode(9, "NOTAUTH", "Server Not Authoritative for zone/Not Authorized")
    val NOTZONE = DnsRcode(10, "NOTZONE", "Name not contained in zone")
    val DSOTYPENI = DnsRcode(11, "DSOTYPENI", "DSO-TYPE Not Implemented")
    val BADVERS_BADSIG = DnsRcode(16, "BADVERS_BADSIG", "Bad OPT Version/TSIG Signature Failure")
    val BADKEY = DnsRcode(17, "BADKEY", "Key not recognized")
    val BADTIME = DnsRcode(18, "BADTIME", "Signature out of time window")
    val BADMODE = DnsRcode(19, "BADMODE", "Bad TKEY Mode")
    val BADNAME = DnsRcode(20, "BADNAME", "Duplicate key name")
    val BADALG = DnsRcode(21, "BADALG", "Algorithm not supported")
    val BADTRUNC = DnsRcode(22, "BADTRUNC", "Bad Truncation")
    val BADCOOKIE = DnsRcode(23, "BADCOOKIE", "Bad/missing Server Cookie")

    val ianaAssignedRcodes: Map<Int, DnsRcode> = listOf(
      NOERROR,
      FORMERR,
      SERVFAIL,
      NXDOMAIN,
      NOTIMP,
      REFUSED,
      YXDOMAIN,
      YXRRSET,
      NXRRSET,
      NOTAUTH,
      NOTZONE,
      DSOTYPENI,
      BADVERS_BADSIG,
      BADKEY,
      BADTIME,
      BADMODE,
      BADNAME,
      BADALG,
      BADTRUNC,
      BADCOOKIE
    ).associateBy { it.id }
    // END GENERATED CODE

    fun decode(id: Int): DnsRcode = ianaAssignedRcodes[id] ?: DnsRcode(id)
  }
}
