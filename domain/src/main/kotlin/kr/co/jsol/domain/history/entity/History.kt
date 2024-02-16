package kr.co.jsol.domain.history.entity

import kr.co.jsol.common.domain.BaseEntity
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

@SQLDelete(sql = "UPDATE tb_history SET deleted_at = now() WHERE id = ?")
@Entity
@Table(name = "tb_history")
class History(
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
    @field:JoinColumn(name = "owner_id", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @field:Comment("등록처 아이디")
    var owner: Shop,

    @field:ManyToOne(fetch = FetchType.LAZY)
    @field:JoinColumn(name = "owner_id", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @field:Comment("등록처 아이디")
    var owner: ShopDepot,
) : BaseEntity()
