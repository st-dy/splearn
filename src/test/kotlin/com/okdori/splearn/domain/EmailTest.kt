package com.okdori.splearn.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

/**
 * packageName    : com.okdori.splearn.domain
 * fileName       : EmailTest
 * author         : okdori
 * date           : 2026. 10. 29.
 * description    :
 */
class EmailTest : StringSpec({
    "이메일 동일성 검증" {
        val email1 = Email("test@example.com")
        val email2 = Email("test@example.com")
        email1 shouldBe email2
    }
})
