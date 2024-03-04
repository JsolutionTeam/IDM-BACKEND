package kr.co.jsol.domain.devicestatus.entity.enums

enum class DeviceStatusType(
    private val description: String,
) {
    `in`("입고"),
    `out`("출고"),
    etc("기타"),
    tempOut("임시출고"),
    ;

    class Response(deviceStatusType: DeviceStatusType) {
        val name = deviceStatusType.name
        val description = deviceStatusType.description
    }
}
