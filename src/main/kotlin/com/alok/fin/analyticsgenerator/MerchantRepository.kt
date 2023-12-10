package com.alok.fin.analyticsgenerator

import org.springframework.data.jpa.repository.JpaRepository

interface MerchantRepository: JpaRepository<Merchant, String> {
}