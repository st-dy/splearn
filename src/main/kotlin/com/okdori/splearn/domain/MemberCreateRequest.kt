package com.okdori.splearn.domain

/**
 * packageName    : com.okdori.splearn.domain
 * fileName       : MemberCreateRequest
 * author         : okdori
 * date           : 2026. 10. 29.
 * description    :
 */
data class MemberCreateRequest(
    val email: String,
    val nickname: String,
    val password: String,
)
