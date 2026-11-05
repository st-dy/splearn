package com.okdori.splearn.application.adapter.out

import com.okdori.splearn.application.required.EmailSender
import com.okdori.splearn.domain.Email
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component

/**
 * packageName    : com.okdori.splearn.application.adapter.out
 * fileName       : DevEmailSender
 * author         : okdori
 * date           : 2026. 11. 6.
 * description    :
 */
@Component
@Profile("dev")
class DevEmailSender : EmailSender {
    override fun send(email: Email, subject: String, content: String) {
    }
}
