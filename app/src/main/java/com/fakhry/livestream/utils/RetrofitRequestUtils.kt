package com.fakhry.livestream.utils

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

private val mediaTypeJson = "application/json; charset=utf-8".toMediaTypeOrNull()

/**
 * Create JSON RequestBody for OKHttp3 Request
 * @param params vararg of key and value
 * @return JSON RequestBody for OkHttp3
 * */
fun createJsonRequestBody(vararg params: Pair<String, String>) =
    JSONObject(mapOf(*params)).toString().toRequestBody(mediaTypeJson)