package com.okdori.splearn.adapter.integration

import com.okdori.splearn.domain.Email
import io.kotest.core.spec.style.FunSpec
import io.kotest.extensions.system.captureStandardOut
import io.kotest.matchers.shouldBe


/**
 * packageName    : com.okdori.splearn.adapter.integration
 * fileName       : DummyEmailSenderTest
 * author         : okdori
 * date           : 2026. 11. 18.
 * description    :
 */
class DummyEmailSenderTest : FunSpec({

    test("이메일 전송 내용을 출력한다") {
        val output = captureStandardOut {
            DummyEmailSender().send(
                Email("test@splearn.app"),
                "test subject",
                "test content",
            )
        }

        output.trim() shouldBe "DummyEmail send email: Email(address=test@splearn.app)"
    }
})
