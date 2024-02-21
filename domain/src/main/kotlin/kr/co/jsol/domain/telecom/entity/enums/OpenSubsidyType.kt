package kr.co.jsol.domain.telecom.entity.enums

enum class OpenSubsidyType(
    val kor: String,
    val description: String,
) {
    DEVICE("DEVICE", "단말기기 지원금, 단말기 지원금을 제공받아 단말기 가격을 할인받을 수 있습니다."),
    PLAN("PLAN", "선택 약정, 요금제 비용의 25%만큼 선택 약정 기간동안 할인됩니다.")
    ;
}
