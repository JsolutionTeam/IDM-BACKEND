//package kr.co.jsol.domain.entity.telecom
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
//class Telecom(telecomDto: TelecomDto) : BaseEntity() {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(nullable = false)
//    private var id: Long? = null
//
//    private var telecomName: String
//
//    private var contactFlag: Boolean
//
//    private var disclosureFlag: Boolean
//
//    init {
//        this.telecomName = telecomDto.getTelecomName()
//        this.contactFlag = telecomDto.getDisclosureFlag()
//        this.disclosureFlag = telecomDto.getContactFlag()
//    }
//
//    fun update(telecomDto: TelecomDto) {
//        this.telecomName = telecomDto.getTelecomName()
//        this.contactFlag = telecomDto.getDisclosureFlag()
//        this.disclosureFlag = telecomDto.getContactFlag()
//    }
//}
