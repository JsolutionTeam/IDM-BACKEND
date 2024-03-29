package kr.co.jsol.domain.auth.restapi

import kr.co.jsol.common.customresttemplate.CustomRestTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class AuthRest : CustomRestTemplate() {

    @Value("\${custom.api-url.auth: https://localhost:11001}")
    lateinit var authApiUrl: String

    final inline fun <reified T> post(
        endpoint: String,
        body: Map<String, Any> = mapOf(),
    ): ResponseEntity<T> {
        var requestEndpoint = endpoint

        val restTemplate = RestTemplate()
        val headers = defaultHeaders()
        val requestMessage = HttpEntity(body, headers)

        requestEndpoint = parseEndpoint(requestEndpoint)

        val requestUrl = "$authApiUrl/$requestEndpoint"

        return restTemplate.postForEntity(requestUrl, requestMessage, T::class.java)
    }

    final inline fun <reified T> patch(
        endpoint: String,
        body: Map<String, Any?> = mapOf(),
    ): ResponseEntity<T> {
        var requestEndpoint = endpoint

        val restTemplate = RestTemplate()
        val headers = defaultHeaders()
        val requestMessage = HttpEntity(body, headers)

        requestEndpoint = parseEndpoint(requestEndpoint)

        val requestUrl = "$authApiUrl/$requestEndpoint"

        return restTemplate.postForEntity(requestUrl, requestMessage, T::class.java)
    }

    // https://api-auth.j-sol.co.kr/api/users/login
    // https://api-auth.jsol.co.kr/api/users/login

    fun delete(
        endpoint: String,
        body: Map<String, Any>? = mapOf(),
    ) {
        var requestEndpoint = endpoint
        val restTemplate = RestTemplate()
        val headers = defaultHeaders()
        val requestMessage = HttpEntity(body, headers)
        requestEndpoint = parseEndpoint(requestEndpoint)
        val requestUrl = "$authApiUrl/$requestEndpoint"

        restTemplate.delete(requestUrl, requestMessage)
    }

    fun parseEndpoint(requestEndpoint: String): String {
        var requestEndpoint1 = requestEndpoint
        // endpoint가 /로 시작할 경우 지운다
        if (requestEndpoint1.startsWith("/")) {
            requestEndpoint1 = requestEndpoint1.removePrefix("/")
        }
        return requestEndpoint1
    }
}
