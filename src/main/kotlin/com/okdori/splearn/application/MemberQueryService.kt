package com.okdori.splearn.application

import com.okdori.splearn.application.provided.MemberFinder
import com.okdori.splearn.application.required.MemberRepository
import com.okdori.splearn.domain.Member
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.validation.annotation.Validated

/**
 * packageName    : com.okdori.splearn.application
 * fileName       : MemberQueryService
 * author         : okdori
 * date           : 2026. 11. 17.
 * description    :
 */
@Service
@Transactional
@Validated
class MemberQueryService(
    private val memberRepository: MemberRepository,
) : MemberFinder {

    override fun find(memberId: Long): Member {
        return memberRepository.findById(memberId)
            ?: throw IllegalArgumentException("존재하지 않는 회원입니다: $memberId")
    }
}
