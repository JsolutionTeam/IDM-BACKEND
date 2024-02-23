package kr.co.jsol.domain.telecom.entity.enums

enum class OpenType(
    private val kor: String,
    private val description: String,
) {
    NEW("NEW", "신규가입"),
    MNP("MNP", "번호이동"),
    DEV("DEV", "기기변경"),
    GEN("GEN", "전환신규");

    class Response(openType: OpenType) {
        private val kor = openType.kor
        private val description = openType.description
    }
}
