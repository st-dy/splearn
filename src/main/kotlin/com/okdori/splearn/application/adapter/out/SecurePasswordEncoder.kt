package com.okdori.splearn.application.adapter.out

import com.okdori.splearn.domain.PasswordEncoder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component

/**
 * packageName    : com.okdori.splearn.application.adapter.out
 * fileName       : SecurePasswordEncoder
 * author         : okdori
 * date           : 2026. 11. 6.
 * description    :
 */
@Component
class SecurePasswordEncoder : PasswordEncoder {
    private val bCryptPasswordEncoder = BCryptPasswordEncoder()

    override fun encode(password: String): String =
        checkNotNull(bCryptPasswordEncoder.encode(password))

    override fun matches(password: String, passwordHash: String): Boolean =
        bCryptPasswordEncoder.matches(password, passwordHash)
}
