package com.okdori.splearn.domain

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Size

/**
 * packageName    : com.okdori.splearn.domain
 * fileName       : MemberCreateRequest
 * author         : okdori
 * date           : 2026. 10. 29.
 * description    :
 */
data class MemberRegisterRequest(
    @field:Email
    val email: String,

    @field:Size(min = 4, max = 20)
    val nickname: String,

    @field:Size(min = 8, max = 100)
    val password: String,
)
