package com.okdori.splearn.application.provided

import com.okdori.splearn.SplearnTestConfiguration
import com.okdori.splearn.domain.*
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import jakarta.persistence.EntityManager
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
    private val entityManager: EntityManager,
) : StringSpec({
    lateinit var member: Member

    beforeTest {
        member =  memberRegister.register(
            MemberFixture.createMemberRegisterRequest(),
        )
    }

    "회원 등록 시 PENDING 상태의 회원을 반환한다" {
        member.id.shouldNotBeNull()
        member.status shouldBe MemberStatus.PENDING
    }

    "이메일이 중복되면 등록에 실패한다" {
        shouldThrow<DuplicateEmailException> {
            memberRegister.register(MemberFixture.createMemberRegisterRequest())
        }
    }

    "회원을 활성화하면 ACTIVE 상태가 된다" {
        entityManager.flush()
        entityManager.clear()

        val activatedMember = memberRegister.activate(member.id!!)
        entityManager.flush()

        activatedMember.status shouldBe MemberStatus.ACTIVE
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
