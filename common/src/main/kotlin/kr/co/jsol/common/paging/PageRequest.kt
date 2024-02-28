package kr.co.jsol.common.paging

import io.swagger.v3.oas.annotations.media.Schema
import org.springdoc.api.annotations.ParameterObject
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort

@ParameterObject
data class PageRequest(
    @field:Schema(description = "페이지 번호(1부터 시작)", defaultValue = "1")
    var page: Int = 1,

    @field:Schema(description = "페이지 크기", defaultValue = "10")
    var size: Int = 10,

    @field:Schema(description = "정렬 정보", defaultValue = "id.desc")
    var sort: MutableList<String>? = null,

    @field:Schema(
        description = "페이지네이션 사용 여부, false 로 주면 모든 데이터를 가져옴",
        defaultValue = "true",
        implementation = Boolean::class,
    )
    var paged: Boolean = true,
) {

    private fun init() {
        val DEFAULT_SIZE = 10
        val MAX_SIZE = 10_000 // 메모리 문제를 방지하기 위해 최대 10,000건으로 제한한다.
        this.page = if (page < 1) 1 else page
        this.size = if (size > MAX_SIZE) DEFAULT_SIZE else size
    }

    fun of(): Pageable {
        // 기본 값 세팅
        init()

        // 사용자 정의 sort가 오지 않았을때 id desc를 기본으로 사용하기 위해 생성.
        val defaultOrder = Sort.Order.desc("id")

        val orders: List<Sort.Order> = sort?.filter {
            // name.asc 같은 형태로 와야 정렬처리 하므로 필터링한다.

            // 외래 테이블 조인해야하는 경우 table.column.asc 형태로 오기 때문에 2이상으로 필터링한다.
            it.split(".").size >= 2
        }?.map {
            // .으로 필터링된 정렬 값을 분리시킨다.
            val split = it.split(".")
            // properties의 정렬 방향을 설정한다.

            // split의 0부터 n-1까지
            val lastValueIndex = split.size - 1

            val properties = split.subList(0, lastValueIndex).joinToString(".")

            // split의 마지막 값은 항상 split.size - 1임.
            val directionValue = split[lastValueIndex].equals("asc", true)

            // direction은 asc로 왔을때만 asc로 설정한다.
            val direction = if (directionValue) Sort.Direction.ASC else Sort.Direction.DESC
            Sort.Order(direction, properties)
        } ?: listOf(defaultOrder)

        // 위에서 계산한 값으로 정렬값 생성
        val sortBy = Sort.by(orders)

        return if (paged) {
            org.springframework.data.domain.PageRequest.of(page - 1, size, sortBy)
        } else {
            Pageable.unpaged()
        }
    }

    override fun toString(): String {
        return "PageRequest(page=$page, size=$size, sort=$sort, paged=$paged)"
    }
}
