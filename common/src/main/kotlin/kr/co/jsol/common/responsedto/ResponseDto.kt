package kr.co.jsol.common.responsedto

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL) //null인 데이터는 json 결과에 나오지 않음
class ResponseDto {
    private var response: Boolean
    private var message: String
    private var id: Long? = null

    constructor(
        id: Long?,
        response: Boolean,
        message: String,
    ) {
        this.id = id
        this.response = response
        this.message = message
    }

    constructor(
        response: Boolean,
        message: String,
    ) {
        this.response = response
        this.message = message
    }

    override fun toString(): String {
        return "ResponseDto{response=$response, message='$message'}"
    }
}
