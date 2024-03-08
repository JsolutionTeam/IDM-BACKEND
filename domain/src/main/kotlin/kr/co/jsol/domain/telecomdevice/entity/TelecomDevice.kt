package kr.co.jsol.domain.telecomdevice.entity

import kr.co.jsol.common.domain.BaseEntity
import kr.co.jsol.domain.telecomdevice.application.dto.UpdateTelecomDeviceDto
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

    @Comment("회사 지원금(행사가) 설명")
    var companySubsidy: String,

    @Comment("요금제 설명")
    var phonePlan: String,

    @Comment("총 요금 설명")
    var totalPrice: String,

    @Comment("통신사 바로가기 링크")
    var link: String,

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
        updateTelecomDeviceDto.etc1?.let { etc1 = it }
        updateTelecomDeviceDto.etc2?.let { etc2 = it }
        updateTelecomDeviceDto.etc3?.let { etc3 = it }
        updateTelecomDeviceDto.etc4?.let { etc4 = it }
        updateTelecomDeviceDto.etc5?.let { etc5 = it }
        updateTelecomDeviceDto.etc6?.let { etc6 = it }
    }
}
