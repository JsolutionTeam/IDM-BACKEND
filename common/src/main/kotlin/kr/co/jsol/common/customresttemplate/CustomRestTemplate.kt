package kr.co.jsol.common.customresttemplate

import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType

open class CustomRestTemplate {

    fun defaultHeaders(): HttpHeaders {

        // Header set
        val httpHeaders: HttpHeaders = HttpHeaders()
        httpHeaders.contentType = MediaType.APPLICATION_JSON

        return httpHeaders
    }
}
