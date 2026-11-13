package com.okdori.splearn.application.provided

import com.okdori.splearn.SplearnTestConfiguration
import com.okdori.splearn.domain.DuplicateEmailException
import com.okdori.splearn.domain.MemberFixture
import com.okdori.splearn.domain.MemberRegisterRequest
import com.okdori.splearn.domain.MemberStatus
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import jakarta.validation.ConstraintViolationException
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.transaction.annotation.Transactional

/**
 * packageName    : com.okdori.splearn.application.provided
 * fileName       : MemberRegisterTest
 * author         : okdori
 * date           : 2026. 11. 13.
 * description    :
 */
@SpringBootTest
@Transactional
@Import(SplearnTestConfiguration::class)
class MemberRegisterTest(
    private val memberRegister: MemberRegister,
) : StringSpec({

    "회원 등록 시 PENDING 상태의 회원을 반환한다" {
        val member = memberRegister.register(
            MemberFixture.createMemberRegisterRequest(),
        )

        member.id.shouldNotBeNull()
        member.status shouldBe MemberStatus.PENDING
    }

    "이메일이 중복되면 등록에 실패한다" {
        memberRegister.register(MemberFixture.createMemberRegisterRequest())

        shouldThrow<DuplicateEmailException> {
            memberRegister.register(MemberFixture.createMemberRegisterRequest())
        }
    }

    "닉네임이 너무 짧으면 등록에 실패한다" {
        shouldThrow<ConstraintViolationException> {
            memberRegister.register(
                MemberRegisterRequest(
                    email = "test@splearn.app",
                    nickname = "t",
                    password = "password",
                ),
            )
        }
    }

    "닉네임이 너무 길면 등록에 실패한다" {
        shouldThrow<ConstraintViolationException> {
            memberRegister.register(
                MemberRegisterRequest(
                    email = "test@splearn.app",
                    nickname = "test---------------------------------",
                    password = "password",
                ),
            )
        }
    }

    "이메일 형식이 잘못되면 등록에 실패한다" {
        shouldThrow<ConstraintViolationException> {
            memberRegister.register(
                MemberRegisterRequest(
                    email = "testsplearn.app",
                    nickname = "test_test",
                    password = "password",
                ),
            )
        }
    }
})
