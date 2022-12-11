package com.example.myfootball.util

import okhttp3.Headers
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.SocketPolicy
import okio.Buffer
import okio.source

fun MockWebServer.enqueue(filename: String) {
    this.enqueueWithCode(filename, 200)
}

fun MockWebServer.enqueueWithCode(filename: String, code: Int) {
    this.enqueue(
        MockResponse().setBody(Buffer().apply {
            writeAll(
                MockWebServer::class.java.classLoader.getResourceAsStream(filename).source()

            )
        }).setResponseCode(code)
    )
}

fun MockWebServer.enqueueWithHeaders(filename: String, headers: Headers) {
    val body = Buffer().apply {
        writeAll(
            MockWebServer::class.java.classLoader.getResourceAsStream(filename).source()
        )
    }
    this.enqueue(
        MockResponse().setBody(body).setHeaders(headers).setHeader("Content-Length", body.size)
    )
}

fun MockWebServer.enqueueIOException() {
    this.enqueue(
        MockResponse().apply {
            socketPolicy = SocketPolicy.DISCONNECT_AT_START
        }
    )
}