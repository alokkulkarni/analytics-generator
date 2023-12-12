package com.alok.fin.analyticsgenerator

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface MerchantCategoryCodesRepository: JpaRepository<MerchantCategoryCodes, String>  {
}