package com.okdori.splearn.domain

import jakarta.persistence.*
import org.hibernate.annotations.NaturalId

/**
 * packageName    : com.okdori.splearn.domain
 * fileName       : Member
 * author         : okdori
 * date           : 2026. 10. 28.
 * description    :
 */
@Entity
class Member private constructor(
    email: Email,
    nickname: String,
    passwordHash: String,
    status: MemberStatus,
) : AbstractEntity() {
    @Embedded
    @NaturalId
    var email: Email = email
        protected set

    var nickname: String = nickname
        protected set

    var passwordHash: String = passwordHash
        protected set

    var status: MemberStatus = status
        protected set

    fun activate() {
        check(status == MemberStatus.PENDING) {
            "회원이 PENDING 상태가 아닙니다"
        }
        this.status = MemberStatus.ACTIVE
    }

    fun deactivate() {
        check(status == MemberStatus.ACTIVE) {
            "회원이 ACTIVE 상태가 아닙니다"
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

    fun isActivate(): Boolean = this.status == MemberStatus.ACTIVE

    companion object {
        fun register(
            memberCreateRequest: MemberRegisterRequest,
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

@Embeddable
data class Email(val address: String) {
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
