package kr.co.jsol.domain.entity.shopdevice.entity

import kr.co.jsol.common.domain.BaseEntity
import org.hibernate.annotations.Comment
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Table
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@SQLDelete(sql = "UPDATE shop_device SET deleted_at = now() WHERE id = ?")
@Entity
@Table(appliesTo = "shop_device")
class ShopDevice(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    @field:Column(name = "id")
    @field:Comment("아이디")
    var id: Long,

    @field:Comment("이름")
    var name: String,
) : BaseEntity()
