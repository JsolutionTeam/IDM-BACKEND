package kr.co.jsol.domain.entity.masteroption

import lombok.AccessLevel
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
class MasterOption(masterOptionDto: MasterOptionDto) : TimeStamp() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private var idx: Long? = null

    private var name: String

    private var isShow: Boolean

    init {
        this.name = masterOptionDto.getName()
        this.isShow = masterOptionDto.getIsShow()
    }

    fun update(masterOptionDto: MasterOptionDto) {
        this.name = masterOptionDto.getName()
        this.isShow = masterOptionDto.getIsShow()
    }
}
