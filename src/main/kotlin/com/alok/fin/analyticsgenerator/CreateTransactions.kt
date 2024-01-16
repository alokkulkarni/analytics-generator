@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "UNUSED_EXPRESSION")

package com.alok.fin.analyticsgenerator

import java.math.BigDecimal
import java.time.Instant
import java.util.*
import kotlin.random.asKotlinRandom

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
            accountId = arrayOf("20245687665544", "20224599443322" ).random().toString(),
            customerId = arrayOf("7654321892", "8765434678" ).random().toString(),
            transactionType = arrayOf("debit", "credit" ).random().toString(),
            transactionDate = Instant.now().toString(),
            transactionAmount = BigDecimal.valueOf(Random().asKotlinRandom().nextDouble(100.0, 1000.0)).toString(),
            transactionCurrency = "GBP",
            transactionRefrence = arrayOf("transactionReference1", "transactionReference2" ).random().toString(),
            transactionStatus = arrayOf( "posted", "pending", "Declined" ).random().toString(),
            transactionCategory = arrayOf( "Food & Dining", "Entertainment","Education", "Shopping","Personal Care","Health & Fitness" ).random().toString(),
            transactionSubCategory = arrayOf( "Catering", "Coffee shops","Delivery", "Charity","Movies & DVDs","Games" ).random().toString(),
            transactionLatitude = arrayOf( "51.5072° N", "40.4168° N" ).random().toString(),
            transactionLongitude = arrayOf( "0.1275° W", "3.7038° W" ).random().toString(),
            merchantId = merchantCategoryCodes?.id
        )
        val save = transactionsRepository.save(transactions)
        return save.transactionId
    }
}