package kr.co.jsol.domain.telecom.entity.enums

enum class OpenType(
    private val description: String,
) {
    NEW("신규가입"),
    MNP("번호이동"),
    DEV("기기변경"),
    GEN("전환신규");

    class Response(openType: OpenType) {
        val name = openType.name
        val description = openType.description
    }
}
