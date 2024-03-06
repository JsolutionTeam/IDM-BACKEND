package kr.co.jsol.domain.account.application

import kr.co.jsol.common.exception.CustomException
import kr.co.jsol.common.exception.GeneralServerException
import kr.co.jsol.domain.account.application.dto.CreateAccountDto
import kr.co.jsol.domain.account.infrastructure.dto.AccountDto
import kr.co.jsol.domain.account.infrastructure.query.AccountQueryRepository
import kr.co.jsol.domain.account.infrastructure.repository.AccountRepository
import kr.co.jsol.domain.auth.application.AuthService
import kr.co.jsol.domain.shop.infrastructure.query.ShopQueryRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.client.HttpClientErrorException

@Service
class AccountService(
    private val repository: AccountRepository,
    private val query: AccountQueryRepository,
    private val authService: AuthService,
    private val shopQueryRepository: ShopQueryRepository,
) {

    private final val log = LoggerFactory.getLogger(this.javaClass)

    @Transactional
    fun create(createAccountDto: CreateAccountDto): AccountDto {
        if (createAccountDto.shopId == null) {
            log.error("createAccountDto의 shopId가 null입니다.\n$createAccountDto")
            throw GeneralServerException.InternalServerException("업체 생성 중 에러가 발생했습니다. 개발자에게 연락주세요.")
        }

        // shopId로 업체 정보를 조회한다
        val shop = shopQueryRepository.getById(createAccountDto.shopId!!)

        return try {
            // auth 서버에 먼저 계정 정보를 등록한다
            val response = authService.postUsers(createAccountDto.toAuthDto())
            println("response = $response")
            // auth 서버에 계정 정보 등록 성공시, DB에 계정 정보를 등록한다
            AccountDto(repository.save(createAccountDto.toEntity(shop)))
            // 계정이 등록 후 삭제되는지 확인하기 위해 일부로 throw exception
        } catch (e: HttpClientErrorException) {
            val localizedMessage = e.localizedMessage
            var message = "계정 정보 등록에 실패했습니다."

            // 숫자 : ~~~ 형식이라면 숫자 : 뒤에 있는 문자열을 가져온다
            if (localizedMessage?.contains(":") == true) {
                message = localizedMessage.split(":")[1]
                    .replace("\"", "")
                    .trim()
            }

            if (e.statusCode.is4xxClientError)
                throw CustomException(
                    "AUTH-0010",
                    message,
                    e.statusCode,
                    e,
                )
            else {
                log.error("auth 서버에 계정 정보 등록 중 에러가 발생했습니다.", e)
                throw CustomException(
                    "AUTH-0011",
                    message,
                    e.statusCode,
                    e,
                )
            }
        } catch (e: Exception) {
            // 기타 예외 발생시 auth 서버에 등록된 계정 정보를 삭제한다
            try {
                authService.deleteAuthById(createAccountDto.id)
            } catch (_: Exception) {
            }
            throw GeneralServerException.InternalServerException()
        }
    }

    @Transactional
    fun patch() {
        // TODO 작성
    }

    @Transactional(readOnly = true)
    fun getById(id: String): AccountDto {
        return AccountDto(query.getById(id))
    }
}
