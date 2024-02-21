package kr.co.jsol.domain.shop.entity

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
@javax.persistence.Table(
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
@Table(appliesTo = "shop", comment = "업체[매장], 대리점, 판매점 등")
class Shop(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Comment("아이디")
    var id: Long,

    @Comment("이름")
    var name: String,

    @Comment("권한")
    var role: String,

    @Comment("상태")
    var status: String,

    @Comment("전화번호")
    var phone: String,

    @Comment("관리자 아이디")
    var managerId: String,

    @Comment("관리자 이름")
    var managerName: String,

    @Comment("관리자 전화번호")
    var managerPhone: String,

    @ManyToMany(fetch = javax.persistence.FetchType.EAGER)
    val parent: Set<Shop> = setOf(),

    @ManyToMany(fetch = javax.persistence.FetchType.EAGER, mappedBy = "parent")
    val child: Set<Shop> = setOf(),
) : BaseEntity()
