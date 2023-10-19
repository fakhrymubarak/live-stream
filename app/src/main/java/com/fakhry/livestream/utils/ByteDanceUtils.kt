package com.fakhry.livestream.utils

import java.security.MessageDigest

/**
 * This class used to authenticate ByteLive API
 *
 * @see <a href="https://docs.byteplus.com/en/byteplus-livesaas/docs/API-authentication#Step%203:%20Calculate%20a%20signing%20key">API Authentication of ByteDance</a>
 * */
data class CanonicalRequest(
    val httpMethod: String = "POST",
    val canonicalURI: String = "/",
    val canonicalQueryString: String = "Action=GetActivityAPI&ActivityId=1231231231&Version=2020-06-01",
    val headers: Map<String, String> =
        mapOf("Content-Type" to "application/json; charset=utf-8", "Content-Length" to "46"),
    val canonicalHeaders: String = generateCanonicalHeaders(headers),
    val signedHeaders: String = headers.keys.joinToString(";").lowercase(),
    val requestPayload: String = "",
) {
    fun generateByteDanceHeaders() =
        httpMethod + '\n' +
                canonicalURI + '\n' +
                canonicalQueryString + '\n' +
                canonicalHeaders + '\n' +
                signedHeaders + '\n' +
                hashStringSha256(requestPayload)
}


/**
 * Use to generate canonical headers for [CanonicalRequest]
 *
 * @see <a href="https://docs.byteplus.com/en/byteplus-livesaas/docs/API-authentication#Step%203:%20Calculate%20a%20signing%20key:~:text=the%20canonical%20headers%20(-,CanonicalHeaders,-)%2C%20which%20consist%20of">Generate Canonical Headers</a>
 * */
fun generateCanonicalHeaders(headers: Map<String, String>): String {
    val canonicalHeaderEntry = headers.map {
        val headerName = it.key.lowercase()
        val headerValue = it.value.trim()
        "$headerName:$headerValue\n"
    }
    return canonicalHeaderEntry.joinToString("")
}

@OptIn(ExperimentalStdlibApi::class)
fun hashStringSha256(string: String): String {
    val messageDigest = MessageDigest.getInstance("SHA-256")
    val hashBytes = messageDigest.digest(string.toByteArray())
    return hashBytes.toHexString()
}