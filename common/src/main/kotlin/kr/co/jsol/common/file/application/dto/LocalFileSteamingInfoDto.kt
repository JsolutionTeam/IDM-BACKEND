package kr.co.jsol.common.file.application.dto

import org.springframework.core.io.FileSystemResource
import org.springframework.core.io.support.ResourceRegion

data class LocalFileSteamingInfoDto(
    val path: String,
    val resource: FileSystemResource,
    val region: ResourceRegion,
)
