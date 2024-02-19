//package kr.co.jsol.domain.entity.telecom_data
//
//import kr.co.jsol.common.domain.BaseEntity
//import lombok.AccessLevel
//import javax.persistence.Column
//import javax.persistence.Entity
//import javax.persistence.Id
//
//@Entity
//@Getter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//class TelecomData(telecomDataDto: TelecomDataDto) : BaseEntity() {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(nullable = false)
//    private var id: Long? = null
//
//    @Column(nullable = false)
//    private var telecomUrl: String
//
//    @Column(nullable = false)
//    private var modelIdx: Long
//
//    @Column(nullable = false)
//    private var telecomName: String
//
//    init {
//        this.telecomUrl = telecomDataDto.getTelecomUrl()
//        this.modelIdx = telecomDataDto.getModelIdx()
//        this.telecomName = telecomDataDto.getTelecomName()
//    }
//
//    fun updateTelecomData(telecomDataDto: TelecomDataDto) {
//        this.telecomUrl = telecomDataDto.getTelecomUrl()
//        this.modelIdx = telecomDataDto.getModelIdx()
//        this.telecomName = telecomDataDto.getTelecomName()
//    }
//}
