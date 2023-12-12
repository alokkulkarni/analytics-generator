@file:Suppress("unused")

package com.alok.fin.analyticsgenerator

import java.io.File
import java.util.UUID


class LoadCSVFile(private val merchantCategoryCodesRepository: MerchantCategoryCodesRepository) {
    fun loadCSV() {
        try {
            merchantCategoryCodesRepository.deleteAll()
            val csvFile = "src/main/resources/mccCodes.csv"
            val bufferedReader = File(csvFile).bufferedReader()
            bufferedReader.useLines { lines -> lines.forEach {
                merchantCategoryCodesRepository.save(
                    MerchantCategoryCodes(id = UUID.randomUUID().toString(),
                        categoryCode = it.split(",")[0],
                        categoryDescription = it.split(",")[1]
                    )
                )
            }}
        } catch (e: Exception) {
            println("Error deleting all records from merchant_category_codes table")
        }
    }
}