package kr.co.jsol.domain.entity.status.entity.enums

enum class StatusType {
    `in`,
    `out`,
    `etc`,
    `tempOut`;

    companion object {
        fun fromString(value: String): StatusType {
            return valueOf(value)
        }
    }
}
