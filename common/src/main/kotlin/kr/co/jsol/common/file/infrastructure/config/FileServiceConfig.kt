package kr.co.jsol.common.file.infrastructure.config

import io.minio.MinioClient
import kr.co.jsol.common.file.application.FileService
import kr.co.jsol.common.file.application.LocalFileService
import kr.co.jsol.common.file.application.MinioFileService
import kr.co.jsol.common.utils.requireNotEmpty
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FileServiceConfig(
    @Value("\${file.mode:minio}")
    private val fileMode: String,

    @Value("\${file.dir:}")
    private val fileDir: String,

    @Value("\${spring.minio.access-key:}")
    private val minioAccessKey: String,
    @Value("\${spring.minio.secret-key:}")
    private val minioSecretKey: String,
    @Value("\${spring.minio.url:}")
    private val minioServiceEndpoint: String,
    @Value("\${spring.minio.bucket:}")
    private val defaultBucketName: String,
) {
    @Bean
    fun fileService(): FileService {
        when (fileMode) {
            "local" -> {
                // local
                requireNotEmpty(fileDir) { "Could not resolve placeholder 'file.dir' in value \"\${file.dir}\"" }
                return LocalFileService(fileDir)
            }

            else -> {
                // minio
                requireNotEmpty(minioAccessKey) {
                    "Could not resolve placeholder 'spring.minio.access-key' in value \"\${spring.minio.access-key}\""
                }
                requireNotEmpty(minioSecretKey) {
                    "Could not resolve placeholder 'spring.minio.secret-key' in value \"\${spring.minio.secret-key}\""
                }
                requireNotEmpty(minioServiceEndpoint) {
                    "Could not resolve placeholder 'spring.minio.url' in value \"\${spring.minio.url}\""
                }
                requireNotEmpty(defaultBucketName) {
                    "Could not resolve placeholder 'spring.minio.bucket' in value \"\${spring.minio.bucket}\""
                }
                val minioClient = MinioClient.builder()
                    .endpoint(minioServiceEndpoint)
                    .credentials(minioAccessKey, minioSecretKey)
                    .build()
                return MinioFileService(defaultBucketName, minioClient)
            }
        }
    }
}
