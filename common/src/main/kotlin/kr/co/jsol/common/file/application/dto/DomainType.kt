package kr.co.jsol.common.file.application.dto

enum class DomainType(
    val rootDir: String,
) {

    DEVICE("device"),
    DEVICE_INFO("device_info"),
    TELECOM_DEVICE("telecom_device"),
}
