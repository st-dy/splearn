package com.okdori.splearn.application.provided

import com.okdori.splearn.SplearnTestConfiguration
import com.okdori.splearn.domain.Member
import com.okdori.splearn.domain.MemberFixture
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import jakarta.persistence.EntityManager
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.transaction.annotation.Transactional

/**
 * packageName    : com.okdori.splearn.application.provided
 * fileName       : MemberFinderTest
 * author         : okdori
 * date           : 2026. 11. 17.
 * description    :
 */
@SpringBootTest
@Transactional
@Import(SplearnTestConfiguration::class)
class MemberFinderTest(
    private val memberFinder: MemberFinder,
    private val memberRegister: MemberRegister,
    private val entityManager: EntityManager,
) : StringSpec({
    lateinit var member: Member

    beforeTest {
        member =  memberRegister.register(
            MemberFixture.createMemberRegisterRequest(),
        )
    }

    "회원 조회 시 등록된 회원을 반환한다" {
        entityManager.flush()
        entityManager.clear()

        val foundMember = memberFinder.find(member.id!!)

        foundMember.id shouldBe member.id
    }

    "회원 조회 시 존재하지 않는 회원이면 예외를 발생시킨다" {
        val nonExistentMemberId = 999L

        shouldThrow<IllegalArgumentException> {
            memberFinder.find(nonExistentMemberId)
        }
    }
})
