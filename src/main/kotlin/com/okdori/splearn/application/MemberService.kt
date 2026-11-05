package com.okdori.splearn.application

import com.okdori.splearn.application.provided.MemberRegister
import com.okdori.splearn.application.required.EmailSender
import com.okdori.splearn.application.required.MemberRepository
import com.okdori.splearn.domain.Member
import com.okdori.splearn.domain.MemberRegisterRequest
import com.okdori.splearn.domain.PasswordEncoder
import org.springframework.stereotype.Service

/**
 * packageName    : com.okdori.splearn.application
 * fileName       : MemberService
 * author         : okdori
 * date           : 2026. 11. 5.
 * description    :
 */
@Service
class MemberService(
    private val memberRepository: MemberRepository,
    private val emailSender: EmailSender,
    private val passwordEncoder: PasswordEncoder,
) : MemberRegister {

    override fun register(memberRegisterRequest: MemberRegisterRequest): Member {
        // duplicate check

        val member = Member.register(memberRegisterRequest, passwordEncoder)

        memberRepository.save(member)

        emailSender.send(
            member.email,
            "등록을 완료해주세요",
            "아래 링크를 클릭해서 등록을 완료해주세요",
        )

        return member
    }
}
