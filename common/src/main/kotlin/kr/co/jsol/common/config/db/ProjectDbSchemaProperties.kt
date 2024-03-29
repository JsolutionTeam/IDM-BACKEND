package kr.co.jsol.common.config.db

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties("catalog") // application.properties 혹은 .yaml 파일에서 catalog.config.AAA=BBB 형태로 값을 넣어주면
// Entity 파일에서 catalog = "AAA" 형태로 사용할 수 있음 => BBB 값이 schema 값으로 들어감
class ProjectDbSchemaProperties {
    var config: Map<String?, String?>? = null
}
