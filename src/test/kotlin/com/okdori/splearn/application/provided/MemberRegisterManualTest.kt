/**
package com.okdori.splearn.application.provided

import com.okdori.splearn.application.MemberService
import com.okdori.splearn.application.required.EmailSender
import com.okdori.splearn.application.required.MemberRepository
import com.okdori.splearn.domain.Email
import com.okdori.splearn.domain.Member
import com.okdori.splearn.domain.MemberFixture
import com.okdori.splearn.domain.MemberStatus
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import org.mockito.Mockito
import org.mockito.kotlin.any
import org.mockito.kotlin.eq
import org.springframework.test.util.ReflectionTestUtils

/**
 * packageName    : com.okdori.splearn.application.provided
 * fileName       : MemberRegisterTest
 * author         : okdori
 * date           : 2026. 11. 9.
 * description    :
 */
class MemberRegisterManualTest : StringSpec({
    "Stub으로 회원을 등록한다" {
        val register = MemberService(
            MemberRepositoryStub(),
            EmailSenderStub(),
            MemberFixture.createPasswordEncoder(),
        )

        val member = register.register(MemberFixture.createMemberRegisterRequest())

        member.id.shouldNotBeNull()
        member.status shouldBe MemberStatus.PENDING
    }

    "Mock으로 발송된 이메일을 확인한다" {
        val emailSenderMock = EmailSenderMock()
        val register = MemberService(
            MemberRepositoryStub(),
            emailSenderMock,
            MemberFixture.createPasswordEncoder(),
        )

        val member = register.register(MemberFixture.createMemberRegisterRequest())

        member.id.shouldNotBeNull()
        member.status shouldBe MemberStatus.PENDING
        emailSenderMock.tos shouldHaveSize 1
        emailSenderMock.tos.first() shouldBe member.email
    }

    "Mockito로 이메일 발송을 검증한다" {
        val emailSenderMock = Mockito.mock(EmailSender::class.java)
        val register = MemberService(
            MemberRepositoryStub(),
            emailSenderMock,
            MemberFixture.createPasswordEncoder(),
        )

        val member = register.register(MemberFixture.createMemberRegisterRequest())

        member.id.shouldNotBeNull()
        member.status shouldBe MemberStatus.PENDING

        Mockito.verify(emailSenderMock)
            .send(eq(member.email), any(), any())
    }
})

private class MemberRepositoryStub : MemberRepository {
    override fun save(member: Member): Member {
        ReflectionTestUtils.setField(member, "id", 1L)
        return member
    }

    override fun findByEmail(email: Email): Member? {
        return null
    }
}

private class EmailSenderStub : EmailSender {
    override fun send(email: Email, subject: String, content: String) {
    }
}

private class EmailSenderMock : EmailSender {
    val tos = mutableListOf<Email>()

    override fun send(email: Email, subject: String, content: String) {
        tos.add(email)
    }
}
**/
