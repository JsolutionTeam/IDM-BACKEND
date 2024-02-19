//package kr.co.jsol.domain.entity.modelimg
//
//import kr.co.jsol.common.domain.BaseEntity
//import javax.persistence.Column
//import javax.persistence.Entity
//import javax.persistence.Id
//
//@Entity
//class ModelImg(
//    @field:Column(nullable = false)
//    private var modelImg: String,
//    @field:Column(nullable = false)
//    private var modelNumber: Long,
//    private var modelColor: String,
//) : BaseEntity() {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(nullable = false)
//    private var id: Long? = null
//
//    fun update(
//        savedName: String,
//        modelName: Long,
//        modelColor: String,
//    ) {
//        this.modelImg = savedName
//        this.modelNumber = modelName
//        this.modelColor = modelColor
//    }
//
//    companion object {
//        fun defaultModelImg(modelNumber: Long?): ModelImg {
//            return ModelImg("", modelNumber ?: 0L, "")
//        }
//    }
//}
