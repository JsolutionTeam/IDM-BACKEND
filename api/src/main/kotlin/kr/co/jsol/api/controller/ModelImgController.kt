//package kr.co.jsol.api.controller
//
//import io.swagger.annotations.Api
//import org.springframework.core.io.Resource
//import org.springframework.http.HttpStatus
//import org.springframework.security.core.annotation.AuthenticationPrincipal
//import java.io.IOException
//import javax.servlet.http.HttpServletRequest
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/api/v1/modelImg")
//@Api(description = "이미지등록 API")
//class ModelImgController {
//    private val modelImgService: ModelImgService? = null
//
//    @ApiOperation("파일등록 = 마스터롤만 가능")
//    @PostMapping("/file")
//    @Throws(IOException::class)
//    fun addFile(
//        @RequestPart
//        file: MultipartFile?,
//        @RequestParam
//        modelNumber: Long?,
//        @RequestParam
//        modelColor: String?,
//        @AuthenticationPrincipal
//        account: Account
//    ): ResponseEntity<ResponseDto> {
//        val role: String = account.getRole()
//
//        val result: Long = modelImgService.addFile(file, modelNumber, modelColor, role)
//
//        return ResponseEntity<ResponseDto>(ResponseDto(result, true, "파일 등록에 성공 하였습니다."), HttpStatus.OK)
//    }
//
//    @ApiOperation("파일수정 = 마스터롤만 가능")
//    @PutMapping("/file/{modelImgIdx}")
//    @Throws(IOException::class)
//    fun updateFile(
//        @RequestPart
//        file: MultipartFile?,
//        @PathVariable
//        modelImgIdx: Long?,
//        @AuthenticationPrincipal
//        account: Account
//    ): ResponseEntity<ResponseDto> {
//        val role: String = account.getRole()
//
//        val result: Long = modelImgService.updateFile(file, modelImgIdx, role)
//
//        return ResponseEntity<ResponseDto>(ResponseDto(result, true, "파일 수정에 성공하였습니다."), HttpStatus.OK)
//    }
//
//    @get:GetMapping("")
//    @get:ApiOperation("모든 이미지 조회 = 모든롤 가능")
//    val allModelImg: ResponseEntity<List<ModelImg>>
//        get() {
//            val result: List<ModelImg> = modelImgService.getAllModelImg()
//
//            return ResponseEntity<List<ModelImg>>(result, HttpStatus.OK)
//        }
//
//    @ApiOperation("회사별 정책에 연결된 모델이미지 조회 첫번째 모델이미지만 리턴 = 모든롤 가능")
//    @GetMapping("/company")
//    fun getModelImgByCompany(
//        @AuthenticationPrincipal
//        account: Account?,
//        @AuthenticationPrincipal
//        userAccount: UserAccount?
//    ): ResponseEntity<List<ModelImgResDto>> {
//        // 어카운트가 null 이면 UserAccount 에서 찾는다
//
//        if (account == null) {
//            val companyName: String = userAccount.getCompanyName()
//
//            val result: List<ModelImgResDto> = modelImgService.getModelImgByCompany(companyName)
//
//            return ResponseEntity<List<ModelImgResDto>>(result, HttpStatus.OK)
//        }
//        // 유저 어카운트가 null 이면 Account 에서 찾는다
//        if (userAccount == null) {
//            val companyName: String = account.getCompanyName()
//
//            val result: List<ModelImgResDto> = modelImgService.getModelImgByCompany(companyName)
//
//            return ResponseEntity<List<ModelImgResDto>>(result, HttpStatus.OK)
//        }
//
//        throw RuntimeException("로그인 정보가 없습니다.")
//    }
//
//    @ApiOperation("상세페이지용 모델이미지 조회 = 모든롤 가능")
//    @GetMapping("/{modelIdx}")
//    fun getModelImg(
//        @PathVariable
//        modelIdx: Long?
//    ): ResponseEntity<List<ModelImgResDto>> {
//        val result: List<ModelImgResDto> = modelImgService.getModelImg(modelIdx)
//
//        return ResponseEntity<List<ModelImgResDto>>(result, HttpStatus.OK)
//    }
//
//    @ApiOperation("이미지 조회 = 모든롤 가능")
//    @GetMapping("/file/{fileName}")
//    @Throws(IOException::class)
//    fun getModelImgFile(
//        @PathVariable
//        fileName: String?,
//        request: HttpServletRequest?
//    ): ResponseEntity<Resource> {
//        return modelImgService.getModelImgFile(fileName, request)
//    }
//
//    @ApiOperation("삭제 = 마스터롤만 가능")
//    @DeleteMapping("/file/{modelImgIdx}")
//    fun deleteModelImg(
//        @PathVariable
//        modelImgIdx: Long?,
//        @AuthenticationPrincipal
//        account: Account
//    ): ResponseEntity<ResponseDto> {
//        val role: String = account.getRole()
//
//        val result: Boolean = modelImgService.deleteModelImg(modelImgIdx, role)
//
//        return ResponseEntity<ResponseDto>(ResponseDto(result, "모델 이미지 삭제에 성공 하였습니다."), HttpStatus.OK)
//    }
//}
