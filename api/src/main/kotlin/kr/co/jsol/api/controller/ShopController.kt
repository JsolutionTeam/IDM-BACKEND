package kr.co.jsol.api.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import kr.co.jsol.common.domain.Authority
import kr.co.jsol.domain.shop.application.ShopService
import kr.co.jsol.domain.shop.application.dto.CreateShopDto
import kr.co.jsol.domain.shop.infrastructure.dto.ShopDto
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/shops")
class ShopController(
    private val shopService: ShopService,
) {

    @Operation(summary = "업체 등록 - 최종 관리자만 가능")
    @PreAuthorize(Authority.ROLECHECK.HasAdminRole)
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    fun createShop(
        @Valid
        @RequestBody
        createShopDto: CreateShopDto,
    ): ShopDto {
        return shopService.create(createShopDto)
    }
}
