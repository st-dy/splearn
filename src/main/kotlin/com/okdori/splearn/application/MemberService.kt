package com.okdori.splearn.application

import com.okdori.splearn.application.provided.MemberRegister
import com.okdori.splearn.application.required.EmailSender
import com.okdori.splearn.application.required.MemberRepository
import com.okdori.splearn.domain.DuplicateEmailException
import com.okdori.splearn.domain.Email
import com.okdori.splearn.domain.Member
import com.okdori.splearn.domain.MemberRegisterRequest
import com.okdori.splearn.domain.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.validation.annotation.Validated

/**
 * packageName    : com.okdori.splearn.application
 * fileName       : MemberService
 * author         : okdori
 * date           : 2026. 11. 5.
 * description    :
 */
@Service
@Transactional
@Validated
class MemberService(
    private val memberRepository: MemberRepository,
    private val emailSender: EmailSender,
    private val passwordEncoder: PasswordEncoder,
) : MemberRegister {

    override fun register(memberRegisterRequest: MemberRegisterRequest): Member {
        checkDuplicateEmail(Email(memberRegisterRequest.email))

        val member = Member.register(memberRegisterRequest, passwordEncoder)

        memberRepository.save(member)

        sendWelcomeEmail(member)

        return member
    }

    private fun sendWelcomeEmail(member: Member) {
        emailSender.send(
            member.email,
            "등록을 완료해주세요",
            "아래 링크를 클릭해서 등록을 완료해주세요",
        )
    }

    private fun checkDuplicateEmail(email: Email) {
        memberRepository.findByEmail(email)?.let {
            throw DuplicateEmailException(
                "이미 사용 중인 이메일입니다: ${email.address}",
            )
        }
    }
}
