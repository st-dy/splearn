package com.okdori.splearn.application.required

import com.okdori.splearn.domain.Email
import com.okdori.splearn.domain.Member
import org.springframework.data.repository.Repository

/**
 * packageName    : com.okdori.splearn.application.required
 * fileName       : MemberRepository
 * author         : okdori
 * date           : 2026. 11. 2.
 * description    :
 */
interface MemberRepository: Repository<Email, Long> {
    fun save(member: Member): Member
}
