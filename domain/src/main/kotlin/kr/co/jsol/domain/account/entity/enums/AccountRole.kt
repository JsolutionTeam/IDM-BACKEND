package kr.co.jsol.domain.account.entity.enums

enum class AccountRole(
    val description: String,
) {
    USER("사용자"),
    MASTER("마스터")
    ;

    enum class MasterRole(
        val description: String,
    ) {
        ADMIN("관리자"),
        JSOL("JSOL"),
        MASTER("마스터")
        ;
    }

    class Response(role: AccountRole) {
        val name = role.name
        val description = role.description
    }

    companion object {
        fun of(role: String): AccountRole {
            // role이 enum MASTER Role에 해당하는 경우에는 MASTER로 반환하고 그 외에는 USER로 반환
            return if (role.contains(MasterRole.ADMIN.name) || role.contains(MasterRole.JSOL.name) || role.contains(
                    MasterRole.MASTER.name
                )
            ) {
                MASTER
            } else {
                USER
            }
        }
    }
}
