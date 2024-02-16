package kr.co.jsol.domain.deviceinfo.entity

import kr.co.jsol.common.domain.BaseEntity
import kr.co.jsol.domain.color.entity.Color
import kr.co.jsol.domain.device.entity.Device
import org.hibernate.annotations.Comment
import org.hibernate.annotations.SQLDelete
import javax.persistence.ConstraintMode
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ForeignKey
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@SQLDelete(sql = "UPDATE tb_device_info SET deleted_at = now() WHERE id = ?")
@Entity
@Table(name = "tb_device_info")
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
