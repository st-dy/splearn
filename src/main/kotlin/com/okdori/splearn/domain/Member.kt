package com.okdori.splearn.domain

/**
 * packageName    : com.okdori.splearn.domain
 * fileName       : Member
 * author         : okdori
 * date           : 2026. 10. 28.
 * description    :
 */
class Member private constructor(
    val email: Email,
    var nickname: String,
    var passwordHash: String,
    var status: MemberStatus,
) {
    fun activate() {
        check(status == MemberStatus.PENDING) {
            "회원이 PENDING 상태가 아닙니다"
        }
        this.status = MemberStatus.ACTIVATE
    }

    fun deactivate() {
        check(status == MemberStatus.ACTIVATE) {
            "회원이 ACTIVATE 상태가 아닙니다"
        }
        this.status = MemberStatus.DEACTIVATED
    }

    fun verifyPassword(
        password: String,
        passwordEncoder: PasswordEncoder,
    ): Boolean = passwordEncoder.matches(password, this.passwordHash)

    fun changeNickname(nickname: String) {
        this.nickname = nickname
    }

    fun changePassword(
        password: String,
        passwordEncoder: PasswordEncoder,
    ) {
        this.passwordHash = passwordEncoder.encode(password)
    }

    fun isActivate(): Boolean = this.status == MemberStatus.ACTIVATE

    companion object {
        fun create(
            memberCreateRequest: MemberCreateRequest,
            passwordEncoder: PasswordEncoder,
        ): Member =
            Member(
                email = Email(memberCreateRequest.email),
                nickname = memberCreateRequest.nickname,
                passwordHash = passwordEncoder.encode(memberCreateRequest.password),
                status = MemberStatus.PENDING,
            )
    }
}

@JvmInline
value class Email(val address: String) {
    init {
        require(EMAIL_REGEX.matches(address)) {
            "올바른 이메일 형식이 아닙니다: $address"
        }
    }

    companion object {
        private val EMAIL_REGEX =
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()
    }
}
