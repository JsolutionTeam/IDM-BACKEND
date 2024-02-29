package kr.co.jsol.common.file.application

import io.minio.ListObjectsArgs
import io.minio.MinioClient
import io.minio.PutObjectArgs
import io.minio.RemoveObjectArgs
import io.minio.RemoveObjectsArgs
import io.minio.messages.DeleteObject
import io.minio.messages.Item
import kr.co.jsol.common.file.application.dto.FileDto
import kr.co.jsol.common.file.application.dto.LocalFileSteamingInfoDto
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.io.Resource
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.server.ResponseStatusException
import java.util.UUID

class MinioFileService(
    private val defaultBucketName: String,
    private val minioClient: MinioClient,
) : FileService {
    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    override fun addFile(
        rootDir: String,
        multipartFile: MultipartFile,
    ): FileDto {
        if (multipartFile.isEmpty) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "파일이 없습니다.")
        }
        val fileOriginalName = multipartFile.originalFilename ?: throw ResponseStatusException(
            HttpStatus.BAD_REQUEST,
            "파일이 없습니다.",
        )
        if (!fileOriginalName.contains(".")) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "파일 확장자가 없습니다.")
        }
        val fileUnit = fileOriginalName.split(".")
            .last()
        if (multipartFile.size > 1024L * 1024 * 100) {
            // 파일 사이즈가 100mb 이상이면 업로드 불가
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "파일 사이즈가 너무 큽니다. 최대 100MB 까지 업로드 가능합니다.")
        }
        val filename = "$rootDir/${UUID.randomUUID()}.$fileUnit"
        return runCatching {
            minioClient.putObject(
                PutObjectArgs.builder()
                    .bucket(defaultBucketName)
                    .`object`(filename)
                    .stream(multipartFile.inputStream, multipartFile.size, -1)
                    .build(),
            )
            FileDto(
                filename,
                multipartFile.size,
            )
        }.onFailure {
            logger.error("$filename 파일 업로드 실패")
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "파일 업로드를 실패했습니다.")
        }
            .getOrThrow()
    }

    override fun getFile(filename: String): Resource {
        throw ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, "다른 방식으로 지원합니다.")
    }

    override fun streaming(
        filename: String,
        headers: HttpHeaders,
    ): LocalFileSteamingInfoDto {
        throw ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, "다른 방식으로 지원합니다.")
    }

    // 파일 삭제 , 삭제할 파일이 없으면 true 삭제를 완료 했으면 true
    override fun deleteFile(filename: String): Boolean {
        runCatching {
            minioClient.removeObject(
                RemoveObjectArgs
                    .builder()
                    .bucket(defaultBucketName)
                    .`object`(filename)
                    .build(),
            )
        }.onFailure {
            logger.error("$filename 파일 삭제 실패")
            return false
        }
        logger.error("$filename 파일 삭제 성공")
        return true
    }

    override fun deleteFiles(filenames: List<String>): Boolean {
        runCatching {
            val results = minioClient.removeObjects(
                RemoveObjectsArgs
                    .builder()
                    .bucket(defaultBucketName)
                    .objects(filenames.map { DeleteObject(it) })
                    .build(),
            )
            results.forEach {
                println(
                    it.get()
                        .objectName() + it.get()
                        .message()
                )
            }
        }.onFailure {
            logger.error("$filenames 파일 삭제 실패")
            return false
        }
        logger.error("$filenames 파일 삭제 성공")
        return true
    }

    // 디렉터리 삭제
    override fun deleteDir(rootDir: String): Boolean {
        runCatching {
            minioClient.removeObject(
                RemoveObjectArgs
                    .builder()
                    .bucket(defaultBucketName)
                    .`object`(rootDir)
                    .build(),
            )
        }.onFailure {
            logger.error("$rootDir 폴더 삭제 실패")
            return false
        }
        logger.error("$rootDir 폴더 삭제 성공")
        return true
    }

    // dir 사이즈 합산 바이트 크기 출력
    override fun getDirectorySize(rootDir: String): Long {
        val minioObjects = getObjectLists(rootDir)
        return minioObjects.sumOf { it.size() }
    }

    override fun flushUnusedFiles(
        rootDir: String,
        files: List<String>,
    ) {
        val minioObjects = getObjectLists(rootDir)
        val unusedFiles = minioObjects.filter { it.objectName() !in files }
        if (unusedFiles.isEmpty()) return
        deleteFiles(unusedFiles.map { it.objectName() })
    }

    private fun getObjectLists(companyId: String): List<Item> {
        return runCatching {
            minioClient.listObjects(
                ListObjectsArgs
                    .builder()
                    .bucket(defaultBucketName)
                    .prefix("$companyId/")
                    .build(),
            )
                .map { it.get() }
        }.onFailure {
            logger.error("파일 리스트 조회 실패")
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "파일 리스트 조회를 실패했습니다.")
        }
            .getOrThrow()
    }
}
