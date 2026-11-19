package com.okdori.splearn.adapter.security

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

/**
 * packageName    : com.okdori.splearn.adapter.security
 * fileName       : SecurePasswordEncoderTest
 * author         : okdori
 * date           : 2026. 11. 19.
 * description    :
 */
class SecurePasswordEncoderTest : StringSpec({

    "비밀번호를 해시하고 원문과 일치 여부를 확인한다" {
        val encoder = SecurePasswordEncoder()
        val hash = encoder.encode("secret")

        encoder.matches("secret", hash) shouldBe true
        encoder.matches("wrong", hash) shouldBe false
    }
})
