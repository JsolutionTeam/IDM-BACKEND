package kr.co.jsol.domain.telecomdevice.entity

import kr.co.jsol.common.domain.BaseEntity
import kr.co.jsol.domain.telecomdevice.application.dto.UpdateTelecomDeviceDto
import org.hibernate.annotations.BatchSize
import org.hibernate.annotations.Comment
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Table
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@SQLDelete(sql = "UPDATE tb_telecom_device SET deleted_at = now() WHERE idx = ?")
@Entity
@javax.persistence.Table(name = "tb_telecom_device")
@BatchSize(size = 50)
@Table(appliesTo = "tb_telecom_device", comment = "통신팀 판매용 단말 리스트")
class TelecomDevice(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx")
    @Comment("아이디")
    val id: Long = 0,

    @Column(name = "model_name")
    @Comment("단말 모델명")
    var modelName: String,

    @Column(name = "pet_name")
    @Comment("단말 펫네임")
    var petName: String,

    @Column(name = "image_url")
    @Comment("해당 단말에 대한 대표 이미지 URL")
    var imageUrl: String,

    @Comment("출고가 설명")
    var price: String,

    @Column(name = "company_subsidy")
    @Comment("회사 지원금(행사가) 설명")
    var companySubsidy: String,

    @Column(name = "phone_plan")
    @Comment("요금제 설명")
    var phonePlan: String,

    @Column(name = "total_price")
    @Comment("총 요금 설명")
    var totalPrice: String,

    @Comment("이동 링크")
    var link: String,

    @Column(name = "display_order")
    @Comment("표시 순서")
    var displayOrder: Int = 1,

    @Column(name = "is_display")
    @Comment("단말 정보 표시 여부")
    var isDisplay: Boolean = true,

    @Comment("기타 1")
    var etc1: String,

    @Comment("기타 2")
    var etc2: String,

    @Comment("기타 3")
    var etc3: String,

    @Comment("기타 4")
    var etc4: String,

    @Comment("기타 5")
    var etc5: String,

    @Comment("기타 6")
    var etc6: String,

    // 혹시 몰라서 일단 들고있도록 함
    @Column(name = "device_id")
    @Comment("단말 기초 아이디")
    val deviceId: Long,
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "device_id", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
//    @Comment("단말 기초 아이디")
//    var device: Device,
) : BaseEntity() {

    fun update(updateTelecomDeviceDto: UpdateTelecomDeviceDto) {
        updateTelecomDeviceDto.modelName?.let { modelName = it }
        updateTelecomDeviceDto.petName?.let { petName = it }
        updateTelecomDeviceDto.imageUrl?.let { imageUrl = it }
        updateTelecomDeviceDto.price?.let { price = it }
        updateTelecomDeviceDto.companySubsidy?.let { companySubsidy = it }
        updateTelecomDeviceDto.phonePlan?.let { phonePlan = it }
        updateTelecomDeviceDto.totalPrice?.let { totalPrice = it }
        updateTelecomDeviceDto.link?.let { link = it }
        updateTelecomDeviceDto.displayOrder?.let { displayOrder = it }
        updateTelecomDeviceDto.etc1?.let { etc1 = it }
        updateTelecomDeviceDto.etc2?.let { etc2 = it }
        updateTelecomDeviceDto.etc3?.let { etc3 = it }
        updateTelecomDeviceDto.etc4?.let { etc4 = it }
        updateTelecomDeviceDto.etc5?.let { etc5 = it }
        updateTelecomDeviceDto.etc6?.let { etc6 = it }
    }
}
