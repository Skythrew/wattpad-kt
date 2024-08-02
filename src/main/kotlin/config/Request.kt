package com.kattpad.config

import io.ktor.client.*
import io.ktor.client.plugins.compression.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.json.Json

open class Request (val cookie: String = "") {
    private val client = HttpClient {
        install(ContentEncoding) {
            deflate(1.0F)
            gzip(0.9F)
        }
    }

    private val apis = mapOf(
        "v2" to "https://www.wattpad.com/apiv2/",
        "v3" to "https://www.wattpad.com/api/v3/",
        "v4" to "https://api.wattpad.com/v4/",
        "v5" to "https://api.wattpad.com/v5/"
    )

    val jsonDecoder = Json {
        ignoreUnknownKeys = true
    }

    suspend fun simpleGet(url: String): HttpResponse {
        return client.get(url)
    }

    suspend fun makeRequest(api: String, path: String, options: HttpRequestBuilder.() -> Unit): HttpResponse {
        return client.request("${apis[api]}$path", options)
    }

    suspend inline fun <reified T> fetch(api: String, path: String, params: Map<String, String>): T {
        val res = this.makeRequest(api, path) {
            method = HttpMethod.Get
            url {
                params.forEach {entry -> parameters.append(entry.key, entry.value)}
            }
        }

        return jsonDecoder.decodeFromString<T>(res.bodyAsText())
    }

    suspend inline fun <reified T> fetchObjData(api: String, path: String, fields: Set<String>, limit: Int? = null): T {
        val params = mapOf(
            when { fields.isNotEmpty() -> "fields" to fields.joinToString(",") else -> "" to ""},
            "limit" to when (limit) {null -> "0" else -> limit.toString()}
        )

        return fetch(api, path, params)
    }
}