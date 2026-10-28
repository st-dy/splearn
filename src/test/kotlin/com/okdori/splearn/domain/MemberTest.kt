package com.okdori.splearn.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe


/**
 * packageName    : com.okdori.splearn.domain
 * fileName       : MemberTest
 * author         : okdori
 * date           : 2026. 10. 28.
 * description    :
 */
class MemberTest : StringSpec({
    lateinit var member: Member
    lateinit var passwordEncoder: PasswordEncoder

    beforeTest {
        passwordEncoder = object : PasswordEncoder {
            override fun encode(password: String) = password.uppercase()

            override fun matches(password: String, passwordHash: String) =
                passwordHash == password.uppercase()
        }

        member = Member.create(
            MemberCreateRequest(
                email = "test@splearn.app",
                nickname = "test",
                password = "password",
            ),
            passwordEncoder = passwordEncoder,
        )
    }

    "회원 생성 시 상태는 PENDING 이어야 한다" {
        member.status shouldBe MemberStatus.PENDING
    }

    "회원 활성화 시 상태는 ACTIVATE 이어야 한다" {
        member.activate()

        member.status shouldBe MemberStatus.ACTIVATE
    }

    "회원 가입완료 후 다시 활성화 시도 시 예외가 발생해야 한다" {
        member.activate()

        shouldThrow<IllegalStateException> {
            member.activate()
        }
    }

    "회원 비활성화 시 상태는 DEACTIVATED 이어야 한다" {
        member.activate()
        member.deactivate()

        member.status shouldBe MemberStatus.DEACTIVATED
    }

    "비밀번호 검증 시 올바른 비밀번호를 입력하면 true, 잘못된 비밀번호를 입력하면 false 를 반환해야 한다" {
        member.activate()

        member.verifyPassword("password", passwordEncoder) shouldBe true
        member.verifyPassword("helloworld", passwordEncoder) shouldBe false
    }

    "회원 닉네임 변경 시 닉네임이 변경되어야 한다" {
        member.changeNickname("new_nickname")

        member.nickname shouldBe "new_nickname"
    }

    "회원 비밀번호 변경 시 비밀번호가 변경되어야 한다" {
        member.changePassword("new_password", passwordEncoder)

        member.verifyPassword("new_password", passwordEncoder) shouldBe true
        member.verifyPassword("password", passwordEncoder) shouldBe false
    }

    "회원이 활성화 상태인지 확인하는 메서드 isActivate() 는 회원이 활성화 상태이면 true, 아니면 false 를 반환해야 한다" {
        member.isActivate() shouldBe false

        member.activate()

        member.isActivate() shouldBe true
    }

    "유효하지 않은 이메일로 회원 생성 시 예외가 발생해야 한다" {
        shouldThrow<IllegalArgumentException> {
            Member.create(
                MemberCreateRequest(
                    email = "",
                    nickname = "test",
                    password = "password",
                ),
                passwordEncoder = passwordEncoder,
            )
        }
    }
})
