//package kr.co.jsol.domain.entity.masteroption
//
//import kr.co.jsol.common.domain.BaseEntity
//import lombok.AccessLevel
//import javax.persistence.Column
//import javax.persistence.Entity
//import javax.persistence.Id
//
//@Getter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@Entity
//class MasterOption(masterOptionDto: MasterOptionDto) : BaseEntity() {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(nullable = false)
//    private var id: Long? = null
//
//    private var name: String
//
//    private var isShow: Boolean
//
//    init {
//        this.name = masterOptionDto.getName()
//        this.isShow = masterOptionDto.getIsShow()
//    }
//
//    fun update(masterOptionDto: MasterOptionDto) {
//        this.name = masterOptionDto.getName()
//        this.isShow = masterOptionDto.getIsShow()
//    }
//}
