package com.okdori.splearn.application.required

import com.okdori.splearn.domain.Email

/**
 * packageName    : com.okdori.splearn.application.required
 * fileName       : EmailSender
 * author         : okdori
 * date           : 2026. 11. 2.
 * description    :
 */
interface EmailSender {
    fun send(email: Email, subject: String, content: String)
}
