package com.okdori.splearn

import com.okdori.splearn.application.required.EmailSender
import com.okdori.splearn.domain.Email
import com.okdori.splearn.domain.MemberFixture
import com.okdori.splearn.domain.PasswordEncoder
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean

/**
 * packageName    : com.okdori.splearn
 * fileName       : SplearnTestConfiguration
 * author         : okdori
 * date           : 2026. 11. 13.
 * description    :
 */
@TestConfiguration
class SplearnTestConfiguration {

    @Bean
    fun emailSender(): EmailSender =
        object : EmailSender {
            override fun send(email: Email, subject: String, content: String) {
                println("Test send email: $email")
            }
        }

    @Bean
    fun passwordEncoder(): PasswordEncoder =
        MemberFixture.createPasswordEncoder()
}
