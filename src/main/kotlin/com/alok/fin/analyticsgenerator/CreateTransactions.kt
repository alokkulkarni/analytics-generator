@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER")

package com.alok.fin.analyticsgenerator

import java.util.*

class CreateTransactions {

    fun createTransaction(
        merchantRepository: MerchantRepository,
        transactionsRepository: TransactionsRepository,
        merchantCategoryCodesRepository: MerchantCategoryCodesRepository
    ): UUID {
        val id = UUID.randomUUID()
        val merchant = Merchant(
            id.toString(),
            "merchantName",
            "merchantLogo",
            "merchantCity",
            "merchantCountry",
            "merchantCategory",
            "merchantCatrgoryCode",
            "merchantLatitude",
            "merchantLongitude"
        )
        val merchantCategoryCodes = merchantCategoryCodesRepository.findAll().randomOrNull()
        print (merchantCategoryCodes)

        val transactions = Transactions(
            UUID.randomUUID(),
            accountId = "UUID.randomUUID().toString()",
            customerId = UUID.randomUUID().toString(),
            transactionType = "transactionType",
            transactionDate = "transactionDate",
            transactionAmount = "transactionAmount",
            transactionCurrency = "transactionCurrency",
            transactionRefrence = "transactionRefrence" + Random().nextInt().toString(),
            transactionStatus = "transactionStatus",
            transactionCategory = merchantCategoryCodes?.categoryDescription ?: "transactionCategory",
            transactionSubCategory = "transactionSubCategory",
            transactionLatitude = "transactionLatitude",
            transactionLongitude = "transactionLongitude",
            merchantId = merchantCategoryCodes?.id
        )
        val save = transactionsRepository.save(transactions)
        return save.transactionId
    }
}