package com.okdori.splearn.domain

/**
 * packageName    : com.okdori.splearn.domain
 * fileName       : MemberFixture
 * author         : okdori
 * date           : 2026. 11. 3.
 * description    :
 */
object MemberFixture {
    fun createMemberRegisterRequest(email: String): MemberRegisterRequest =
        MemberRegisterRequest(email, "test", "password")

    fun createMemberRegisterRequest(): MemberRegisterRequest =
        createMemberRegisterRequest("test@splearn.app")

    fun createPasswordEncoder(): PasswordEncoder =
        object : PasswordEncoder {
            override fun encode(password: String) = password.uppercase()

            override fun matches(password: String, passwordHash: String): Boolean =
                encode(password) == passwordHash
        }
}
