package kr.co.jsol.domain.inouthistory.entity

import kr.co.jsol.common.domain.BaseEntity
import kr.co.jsol.domain.shop.entity.Shop
import kr.co.jsol.domain.shopdepot.entity.ShopDepot
import kr.co.jsol.domain.telecom.entity.Telecom
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
@javax.persistence.Table(name = "in_out_history")
@Table(appliesTo = "in_out_history", comment = "입/출고 이력")
class InOutHistory(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("아이디")
    var id: Long,

    @Comment("모델명")
    var modelName: String,

    @Comment("일련번호")
    var serialNumber: String,

    @Comment("색상")
    var deviceColor: String,

    @Comment("처리작업 ex) 입고,출고, 반품 등..")
    var operation: String,

    @Comment("처리자")
    var operatior: String,

    // 현재는 Kiosk 입/출고 시에만 사용 중
    @Comment("사용자 이름")
    var userName: String,

    @Comment("사용자 일련번호(고유번호) ex) 군번, 사번")
    var userSerialNumber: Long,

    @Comment("메모")
    var memo: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @Comment("등록처 아이디")
    var owner: Shop,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @Comment("보유처 아이디")
    var location: Shop,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @Comment("입고처 아이디")
    var depot: ShopDepot,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "telecom_id", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @Comment("통신사 아이디")
    var telecom: Telecom,
) : BaseEntity()
