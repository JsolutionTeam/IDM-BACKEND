package kr.co.jsol.domain.deviceinfo.entity

import kr.co.jsol.common.domain.BaseEntity
import kr.co.jsol.domain.color.entity.Color
import kr.co.jsol.domain.device.entity.Device
import org.hibernate.annotations.Comment
import org.hibernate.annotations.SQLDelete
import javax.persistence.Column
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

@SQLDelete(sql = "UPDATE tb_device_info SET deleted_at = now() WHERE idx = ?")
@Entity
@Table(name = "tb_device_info", catalog = "idm")
class DeviceInfo(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx")
    @Comment("아이디")
    var id: Long = 0,

    @Comment("바코드 모델구분")
    var barcode: String,

    @Column(name = "barcode_color")
    @Comment("바코드 색상구분")
    var barcodeColor: String,

    @Column(name = "image_url")
    @Comment("모델 이미지 링크")
    var imageUrl: String = "",

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_idx", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @Comment("단말 아이디")
    var device: Device,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "color_idx", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @Comment("색상 아이디")
    var color: Color,
) : BaseEntity() {

    fun update(imageUrl: String) {
        this.imageUrl = imageUrl
    }
}
