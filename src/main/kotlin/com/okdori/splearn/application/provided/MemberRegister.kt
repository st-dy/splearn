package com.okdori.splearn.application.provided

import com.okdori.splearn.domain.Member
import com.okdori.splearn.domain.MemberRegisterRequest

/**
 * packageName    : com.okdori.splearn.application.provided
 * fileName       : MemberRegister
 * author         : okdori
 * date           : 2026. 11. 2.
 * description    :
 */
interface MemberRegister {
    fun register(memberRegisterRequest: MemberRegisterRequest): Member
}
