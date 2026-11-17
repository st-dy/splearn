package com.okdori.splearn

import io.kotest.core.spec.style.FunSpec
import org.mockito.Mockito
import org.springframework.boot.SpringApplication
import org.springframework.context.ConfigurableApplicationContext
import kotlin.emptyArray

/**
 * packageName    : com.okdori.splearn
 * fileName       : MainTest
 * author         : okdori
 * date           : 2026. 11. 18.
 * description    :
 */
class SplearnApplicationTest : FunSpec({

    test("main에서 SpringApplication.run을 호출한다") {
        Mockito.mockStatic(SpringApplication::class.java).use { mocked ->
            val context = Mockito.mock(ConfigurableApplicationContext::class.java)

            mocked.`when`<ConfigurableApplicationContext> {
                SpringApplication.run(
                    SplearnApplication::class.java,
                    *emptyArray(),
                )
            }.thenReturn(context)

            main(emptyArray())

            mocked.verify {
                SpringApplication.run(
                    SplearnApplication::class.java,
                    *emptyArray(),
                )
            }
        }
    }
})
