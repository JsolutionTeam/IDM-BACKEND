package kr.co.jsol.common.config.db

import org.hibernate.boot.model.naming.Identifier
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment
import org.springframework.stereotype.Component

@Component
class MultiSchemaPhysicalNamingStrategy(
// schema 값 별로 다른 catalog 값을 가져오기 위한 클래스
    private val schemaConfiguration: ProjectDbSchemaProperties,
) : PhysicalNamingStrategyStandardImpl() {
    override fun toPhysicalCatalogName(
        name: Identifier?,
        jdbcEnvironment: JdbcEnvironment,
    ): Identifier? {
        println("name = $name")
        if (name != null) {
            val identifier = Identifier
                .toIdentifier(
                    schemaConfiguration.config?.get(name.text) ?: name.text,
                    name.isQuoted
                )
            return super.toPhysicalSchemaName(identifier, jdbcEnvironment)
        }
        return null
    }
}
