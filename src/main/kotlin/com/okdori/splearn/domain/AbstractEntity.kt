package com.okdori.splearn.domain

import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass

/**
 * packageName    : com.okdori.splearn.domain
 * fileName       : AbstractEntity
 * author         : okdori
 * date           : 2026. 11. 19.
 * description    :
 */
@MappedSuperclass
abstract class AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
        protected set
}
