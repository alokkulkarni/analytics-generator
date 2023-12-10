package com.alok.fin.analyticsgenerator

import java.util.*

class CreateTransactions {

    fun createTransaction(
        merchantRepository: MerchantRepository,
        transactionsRepository: TransactionsRepository
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
        merchantRepository.save(merchant)
        val transactions = Transactions(
            UUID.randomUUID(),
            accountId = "accountId",
            customerId = "customerId - 123456789",
            transactionType = "transactionType",
            transactionDate = "transactionDate",
            transactionAmount = "transactionAmount",
            transactionCurrency = "transactionCurrency",
            transactionRefrence = "transactionRefrence",
            transactionStatus = "transactionStatus",
            transactionCategory = "transactionCategory",
            transactionSubCategory = "transactionSubCategory",
            transactionLatitude = "transactionLatitude",
            transactionLongitude = "transactionLongitude"
        )
        val save = transactionsRepository.save(transactions)
        return save.transactionId
    }
}