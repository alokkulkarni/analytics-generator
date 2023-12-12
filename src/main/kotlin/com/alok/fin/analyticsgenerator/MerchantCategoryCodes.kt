package com.alok.fin.analyticsgenerator

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.util.*

@Entity(name = "merchant_category_codes")
data class MerchantCategoryCodes(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String,
    val categoryCode: String,
    val categoryDescription: String
) {
    constructor() : this(UUID.randomUUID().toString(), "categoryCode", "categoryDescription")
}