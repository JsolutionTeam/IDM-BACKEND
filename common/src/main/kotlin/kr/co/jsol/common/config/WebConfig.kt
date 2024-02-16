package kr.co.jsol.common.config

import org.springframework.beans.factory.annotation.Value

@Configuration
class WebConfig : WebMvcConfigurer {
    fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOriginPatterns("*")
            .allowCredentials(true)
            .allowedMethods("OPTIONS", "GET", "POST", "PUT", "DELETE")
            .exposedHeaders("Authorization")
    }

    @Value("\${media.dir}")
    private val DOWNLOAD_PATH: String? = null

    //파일을 직접 전달하는게 아닌, 저장 되어 있는 파일에 접근 가능 하게 하는 설정이다.
    //http://localhost:8080/widget/file/726f377f-8cb2-4ad1-9ce1-eef0636651c1.jpeg 요청시 저장된 정적 파일을 바로 로드 할수 있다. controller 불필요, filelorder 불필요.
    //외부 리소스를 어디서 찾으면 되는지 정의
    fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler("/file/**") // media로 오는 데이터를
            // DOWNLOAD_PATH 에서 찾는다.
            // media/abc.mp4  -> DOWNLOAD_PATH/abc.mp4
            // .addResourceLocations("file:///C:/Users/Administrator/Desktop/view/TOMCAT9/webapps/media/")
            .addResourceLocations("file:///$DOWNLOAD_PATH")
            .setCachePeriod(20)
    }
}
