package kr.co.jsol.common.utils

fun requireNotEmpty(
    value: String?,
    lazyMessage: () -> Any,
): String {
    if (value.isNullOrEmpty()) {
        val message = lazyMessage()
        throw IllegalArgumentException(message.toString())
    } else {
        return value
    }
}
