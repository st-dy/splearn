package com.okdori.splearn.application.required

import com.okdori.splearn.domain.Member
import com.okdori.splearn.domain.MemberFixture.createMemberRegisterRequest
import com.okdori.splearn.domain.MemberFixture.createPasswordEncoder
import io.kotest.core.extensions.ApplyExtension
import io.kotest.core.spec.style.StringSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import jakarta.persistence.EntityManager
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest

/**
 * packageName    : com.okdori.splearn.application.required
 * fileName       : MemberRepositoryTest
 * author         : okdori
 * date           : 2026. 11. 3.
 * description    :
 */
@DataJpaTest
@ApplyExtension(SpringExtension::class)
class MemberRepositoryTest(
    private val memberRepository: MemberRepository,
    private val entityManager: EntityManager,
) : StringSpec({
    "Data Class Email의 val 변수를 DB에서 읽어 복원한다" {
        val originalEmail = "test@splearn.app"
        val member = Member.register(createMemberRegisterRequest(originalEmail), createPasswordEncoder())

        entityManager.persist(member)
        entityManager.flush()

        val memberId = member.id
        entityManager.clear()

        val restored = entityManager.find(Member::class.java, memberId)

        restored.email.address shouldBe originalEmail
    }

    "회원 등록 시 ID가 생성된다" {
        val member = Member.register(
            createMemberRegisterRequest(),
            createPasswordEncoder(),
        )

        member.id.shouldBeNull()

        memberRepository.save(member)
        entityManager.flush()

        member.id.shouldNotBeNull()
    }
})
