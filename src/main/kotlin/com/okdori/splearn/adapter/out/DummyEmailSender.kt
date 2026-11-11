package com.okdori.splearn.adapter.out

import com.okdori.splearn.application.required.EmailSender
import com.okdori.splearn.domain.Email
import org.springframework.stereotype.Component

/**
 * packageName    : com.okdori.splearn.adapter.out
 * fileName       : DummyEmailSender
 * author         : okdori
 * date           : 2026. 11. 13.
 * description    :
 */
@Component
class DummyEmailSender : EmailSender {
    override fun send(email: Email, subject: String, content: String) {
        println("DummyEmail send email: $email")
    }
}
