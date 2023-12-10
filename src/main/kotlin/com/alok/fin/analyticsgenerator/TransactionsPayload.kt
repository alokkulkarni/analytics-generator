@file:Suppress("RedundantConstructorKeyword")

package com.alok.fin.analyticsgenerator

import com.fasterxml.jackson.annotation.JsonProperty

data class TransactionsPayload(
    @JsonProperty("op")
    val op: String,
    @JsonProperty("after")
    val after: TransactionsModel?,
    @JsonProperty("before")
    val before: TransactionsModel?
)


data class TransactionsListner (
    @JsonProperty("payload")
    val transactionsPayload: TransactionsPayload
)

data class TransactionsModel (
    @JsonProperty("transactionId")
    val transactionId: String,
    @JsonProperty("accountId")
    val accountId: String,
    @JsonProperty("customerId")
    val customerId: String,
    @JsonProperty("transactionType")
    val transactionType: String,
    @JsonProperty("transactionDate")
    val transactionDate: String,
    @JsonProperty("transactionAmount")
    val transactionAmount: String,
    @JsonProperty("transactionCurrency")
    val transactionCurrency: String,
    @JsonProperty("transactionRefrence")
    val transactionRefrence: String,
    @JsonProperty("transactionStatus")
    val transactionStatus: String,
    @JsonProperty("transactionCategory")
    val transactionCategory: String,
    @JsonProperty("transactionSubCategory")
    val transactionSubCategory: String,
    @JsonProperty("transactionLatitude")
    val transactionLatitude: String,
    @JsonProperty("transactionLongitude")
    val transactionLongitude: String,
    @JsonProperty("createdDate")
    var createdDate: Long = 0
)
