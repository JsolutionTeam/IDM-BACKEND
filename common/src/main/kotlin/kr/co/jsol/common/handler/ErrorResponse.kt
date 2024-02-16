package kr.co.jsol.common.handler

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
class ErrorResponse(
    private val message: String?,
    private val response: Boolean,
)
