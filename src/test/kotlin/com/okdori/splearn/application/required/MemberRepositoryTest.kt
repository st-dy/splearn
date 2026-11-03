package com.okdori.splearn.application.required

import com.okdori.splearn.domain.Member
import com.okdori.splearn.domain.MemberFixture.createMemberRegisterRequest
import com.okdori.splearn.domain.MemberFixture.createPasswordEncoder
import io.kotest.core.extensions.ApplyExtension
import io.kotest.core.spec.style.StringSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.nulls.shouldNotBeNull
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
