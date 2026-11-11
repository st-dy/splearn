package io.kotest.provided

import io.kotest.core.config.AbstractProjectConfig
import io.kotest.extensions.spring.SpringExtension

/**
 * packageName    : io.kotest.provided
 * fileName       : ProjectConfig
 * author         : okdori
 * date           : 2026. 11. 13.
 * description    :
 */
class ProjectConfig : AbstractProjectConfig() {
    override val extensions = listOf(SpringExtension())
}
