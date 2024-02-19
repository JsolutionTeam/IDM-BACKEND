package kr.co.jsol.domain.entity.inouthistory.entity

import kr.co.jsol.common.domain.BaseEntity
import kr.co.jsol.domain.entity.shop.entity.Shop
import kr.co.jsol.domain.entity.shopdepot.entity.ShopDepot
import kr.co.jsol.domain.entity.telecom.entity.Telecom
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

@SQLDelete(sql = "UPDATE in_out_history SET deleted_at = now() WHERE id = ?")
@Entity
@Table(appliesTo = "in_out_history", comment = "입/출고 이력")
class InOutHistory(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    @field:Comment("아이디")
    var id: Long,

    @field:Comment("모델명")
    var modelName: String,

    @field:Comment("일련번호")
    var serialNumber: String,

    @field:Comment("색상")
    var deviceColor: String,

    @field:Comment("처리작업 ex) 입고,출고, 반품 등..")
    var operation: String,

    @field:Comment("처리자")
    var operatior: String,

    // 현재는 Kiosk 입/출고 시에만 사용 중
    @field:Comment("사용자 이름")
    var userName: String,

    @field:Comment("사용자 일련번호(고유번호) ex) 군번, 사번")
    var userSerialNumber: Long,

    @field:Comment("메모")
    var memo: String,

    @field:ManyToOne(fetch = FetchType.LAZY)
    @field:JoinColumn(name = "manager_id", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @field:Comment("등록처 아이디")
    var owner: Shop,

    @field:ManyToOne(fetch = FetchType.LAZY)
    @field:JoinColumn(name = "location_id", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @field:Comment("보유처 아이디")
    var location: Shop,

    @field:ManyToOne(fetch = FetchType.LAZY)
    @field:JoinColumn(name = "owner_id", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @field:Comment("입고처 아이디")
    var depot: ShopDepot,

    @field:ManyToOne(fetch = FetchType.LAZY)
    @field:JoinColumn(name = "telecom_id", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @field:Comment("통신사 아이디")
    var telecom: Telecom,
) : BaseEntity()
