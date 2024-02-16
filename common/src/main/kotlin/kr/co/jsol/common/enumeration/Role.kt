package kr.co.jsol.common.enumeration

enum class Role(private val role: String) {
    MASTER("master"),
    USER("user"),
    COMPANY("company"),
    NONE("none");

    fun role(): String {
        return role
    }
}
