package kr.co.jsol.common.file.application

import kr.co.jsol.common.file.application.dto.FileDto
import kr.co.jsol.common.file.application.dto.LocalFileSteamingInfoDto
import org.slf4j.LoggerFactory
import org.springframework.core.io.FileSystemResource
import org.springframework.core.io.support.ResourceRegion
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.server.ResponseStatusException
import java.io.File
import java.lang.Long.min
import java.util.UUID

class LocalFileService(
    var fileDir: String,
) : FileService {
    // 파일 업로더
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

        // fileDir/companyId/filename 으로 저장할 것임
        // 해당 폴더까지의 경로가 없으면 생성하는 함수를 작성
        checkOrInsertFolderFullPath("$fileDir/$rootDir")
        return try {
            val file = File("$fileDir/$filename")
            multipartFile.transferTo(file)
            FileDto(
                filename,
                file.length(),
            )
        } catch (e: Exception) {
            log.error("파일 업로드 실패! {}", e.message)
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "파일 업로드 실패!")
        }
    }

    // 파일 로더
    override fun getFile(filename: String): FileSystemResource {
        if (!existsFile(filename)) {
            throw RuntimeException("파일을 찾을수 없습니다.")
        }

        return FileSystemResource("$fileDir/$filename")
    }

    override fun streaming(
        filename: String,
        headers: HttpHeaders,
    ): LocalFileSteamingInfoDto {
//        val path = ClassLoader.getSystemResource("$fileDir/$companyId/$filename").file
        val path = "$fileDir/$filename"
        val resource = FileSystemResource(path)

        val chunkSize = (1024 * 1024).toLong()
        val contentLength: Long = resource.contentLength()
        var region: ResourceRegion

        try {
            val httpRange = headers.range.stream()
                .findFirst()
                .get()
            val start = httpRange.getRangeStart(contentLength)
            val end = httpRange.getRangeEnd(contentLength)
            val rangeLength = min(chunkSize, end - start + 1)
//            log.info("start === {} , end == {}", start, end)
            region = ResourceRegion(resource, start, rangeLength)
        } catch (e: Exception) {
            val rangeLength = min(chunkSize, contentLength)
            region = ResourceRegion(resource, 0, rangeLength)
        }

        return LocalFileSteamingInfoDto(
            path = path,
            resource = resource,
            region = region,
        )
    }

    override fun deleteFiles(filenames: List<String>): Boolean {
        var result = true
        for (filename in filenames) {
            if (!deleteFile(filename)) {
                result = false
            }
        }
        return result
    }

    // 파일 삭제 , 삭제할 파일이 없으면 true 삭제를 완료 했으면 true, otherwise false
    override fun deleteFile(filename: String): Boolean {
        val savedPath = "$fileDir/$filename"
        log.info("FileDeleter - filename : {} 를 삭제 시도 합니다.", savedPath)
        val file = File(savedPath)

        if (existsFile(filename)) {
            val result = file.delete()

            if (!result) {
                log.error("파일삭제 실패! filename : {}", filename)
                return false
            } else {
                log.info("파일삭제 성공! filename : {}", filename)
                return true
            }
        }
        // 삭제할 파일이 없는경우
        log.info("삭제할 파일이 없습니다. 일단 통과! filename : {}", filename)
        return true
    }

    // 디렉터리 삭제, 삭제할 폴더가 없으면 true 삭제를 완료 했으면 true, otherwise false
    override fun deleteDir(rootDir: String): Boolean {
        val savedPath = "$fileDir/$rootDir"
        log.info("FileDeleter - companyId : {} 를 삭제 시도 합니다.", savedPath)
        val file = File(savedPath)

        if (existsFilePath(savedPath)) {
            val result = file.deleteRecursively()

            if (!result) {
                log.error("폴더삭제 실패! companyId : {}", rootDir)
                return false
            } else {
                log.info("폴더삭제 성공! companyId : {}", rootDir)
                return true
            }
        }
        // 삭제할 폴더가 없는경우
        log.info("삭제할 폴더가 없습니다. 일단 통과! companyId : {}", rootDir)
        return true
    }

    // dir 사이즈 합산 바이트 크기 출력
    override fun getDirectorySize(rootDir: String): Long {
        val savedPath = "$fileDir/$rootDir"
        val file = File(savedPath)
        return file.length()
    }

    override fun flushUnusedFiles(
        rootDir: String,
        files: List<String>,
    ) {
        val savedPath = "$fileDir/$rootDir"
        val file = File(savedPath)
        val fileList = file.listFiles()
        if (fileList == null) {
            log.error("flushUnusedFiles - fileList is null")
            return
        }
        for (f in fileList) {
            if (!files.contains("$rootDir/${f.name}")) {
                f.delete()
            }
        }
    }

    // isFile
    fun existsFile(filename: String): Boolean {
        log.info("\"$fileDir/$filename\"에서 파일을 찾는 중...")
        return existsFilePath("$fileDir/$filename")
    }

    fun existsFilePath(fullPath: String): Boolean {
        return File(fullPath).isFile
    }

    fun checkOrInsertFolderFullPath(path: String) {
        try {
            val paths = path.split("/")
            var fullPath = ""
            for (i in paths.indices) {
                fullPath += paths[i] + "/"
                createFolderIfNotExists(fullPath)
            }
        } catch (e: Exception) {
            log.error("createFolderFullPath error : {}", e.message)
        }
    }

    fun createFolderIfNotExists(path: String) {
        val folder = File(path)
        if (!folder.exists()) {
            log.info("폴더가 없어서 생성합니다. path : {}", path)
            folder.mkdirs()
        }
    }

    companion object {
        private val log = LoggerFactory.getLogger(LocalFileService::class.java)
    }
}
