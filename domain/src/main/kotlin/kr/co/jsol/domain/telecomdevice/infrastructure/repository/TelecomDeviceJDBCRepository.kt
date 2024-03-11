package kr.co.jsol.domain.telecomdevice.infrastructure.repository

import kr.co.jsol.domain.telecomdevice.entity.TelecomDevice
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class TelecomDeviceJDBCRepository(
    private val jdbcTemplate: JdbcTemplate, // JdbcTemplate 의존성 주입
) {

    fun batchUpdateDisplayOrder(
        batchSize: Int = 50,
        subItems: List<TelecomDevice>,
    ): Array<IntArray> {
        return jdbcTemplate.batchUpdate(
            // bulk insert에 사용할 기본 쿼리(sql)
            " UPDATE tb_telecom_device SET " +
                    "display_order = ?" +
                    " WHERE idx = ?",
            subItems, // insert할 모델
            batchSize // 1번의 batch로 함께 insert할 batch 사이즈
        ) { ps, argument ->
            ps.setInt(1, argument.displayOrder) // 쿼리의 ?의 순서대로 1번으로 할당되며 해당 쿼리 ? 대신 치환
            ps.setLong(2, argument.id)
        }
    }
}
