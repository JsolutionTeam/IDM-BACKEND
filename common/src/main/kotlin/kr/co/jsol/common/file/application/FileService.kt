package kr.co.jsol.common.file.application

import kr.co.jsol.common.file.application.dto.FileDto
import kr.co.jsol.common.file.application.dto.LocalFileSteamingInfoDto
import org.springframework.core.io.Resource
import org.springframework.http.HttpHeaders
import org.springframework.web.multipart.MultipartFile

interface FileService {
    fun addFile(
        rootDir: String,
        multipartFile: MultipartFile,
    ): FileDto

    fun getFile(filename: String): Resource
    fun deleteFiles(filenames: List<String>): Boolean
    fun deleteFile(filename: String): Boolean
    fun deleteDir(rootDir: String): Boolean
    fun getDirectorySize(rootDir: String): Long
    fun flushUnusedFiles(
        rootDir: String,
        files: List<String>,
    )

    fun streaming(
        filename: String,
        headers: HttpHeaders,
    ): LocalFileSteamingInfoDto
}
