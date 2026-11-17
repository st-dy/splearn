package com.okdori.splearn.application.provided

import com.okdori.splearn.domain.Member

/**
 * packageName    : com.okdori.splearn.application.provided
 * fileName       : MemberFinder
 * author         : okdori
 * date           : 2026. 11. 17.
 * description    :
 */
interface MemberFinder {
    fun find(memberId: Long): Member
}
