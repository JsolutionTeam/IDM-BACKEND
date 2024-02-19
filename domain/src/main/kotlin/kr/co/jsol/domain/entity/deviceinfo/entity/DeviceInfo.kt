package kr.co.jsol.domain.entity.deviceinfo.entity

import kr.co.jsol.common.domain.BaseEntity
import kr.co.jsol.domain.entity.color.entity.Color
import kr.co.jsol.domain.entity.device.entity.Device
import org.hibernate.annotations.Comment
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Table
import javax.persistence.ConstraintMode
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ForeignKey
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@SQLDelete(sql = "UPDATE device_info SET deleted_at = now() WHERE id = ?")
@Entity
@Table(appliesTo = "device_info", comment = "단말 상세")
class DeviceInfo(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    @field:Comment("아이디")
    var id: Long,

    @field:Comment("바코드 모델구분")
    var barcode: String,

    @field:Comment("바코드 색상구분")
    var barcodeColor: String,

    @field:ManyToOne(fetch = FetchType.LAZY)
    @field:JoinColumn(name = "device_id", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @field:Comment("단말 아이디")
    var device: Device,

    @field:ManyToOne(fetch = FetchType.LAZY)
    @field:JoinColumn(name = "color_id", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @field:Comment("색상 아이디")
    var color: Color,
) : BaseEntity()
