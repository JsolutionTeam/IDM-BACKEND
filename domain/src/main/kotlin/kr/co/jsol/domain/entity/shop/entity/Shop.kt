package kr.co.jsol.domain.entity.shop.entity

import kr.co.jsol.common.domain.BaseEntity
import org.hibernate.annotations.Comment
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Table
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToMany
import javax.persistence.UniqueConstraint

@SQLDelete(sql = "UPDATE shop SET deleted_at = now() WHERE id = ?")
@Entity
@Table(
    name = "shop",
    uniqueConstraints = [
        UniqueConstraint(
            name = "uk_shop_name",
            columnNames = ["name"],
        ),
        UniqueConstraint(
            name = "uk_shop_manager_id",
            columnNames = ["managerId"],
        )
    ]
)
class Shop(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    @field:Column(name = "id")
    @field:Comment("아이디")
    var id: Long,

    @field:Comment("이름")
    var name: String,

    @field:Comment("권한")
    var role: String,

    @field:Comment("상태")
    var status: String,

    @field:Comment("전화번호")
    var phone: String,

    @field:Comment("관리자 아이디")
    var managerId: String,

    @field:Comment("관리자 이름")
    var managerName: String,

    @field:Comment("관리자 전화번호")
    var managerPhone: String,

    @field:ManyToMany(fetch = javax.persistence.FetchType.EAGER)
    val parent: Set<Shop> = setOf(),

    @field:ManyToMany(fetch = javax.persistence.FetchType.EAGER, mappedBy = "parent")
    val child: Set<Shop> = setOf(),
) : BaseEntity()
