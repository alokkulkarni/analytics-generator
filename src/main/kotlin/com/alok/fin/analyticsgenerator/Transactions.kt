package com.alok.fin.analyticsgenerator

import jakarta.persistence.*
import java.time.Instant
import java.util.*


@Entity(name = "transactions")
data class Transactions(
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	val transactionId: UUID,
	val accountId: String,
	val customerId: String,
	val transactionType: String,
	val transactionDate: String,
	val transactionAmount: String,
	val transactionCurrency: String,
	val transactionRefrence: String,
	val transactionStatus: String,
	val transactionCategory: String,
	val transactionSubCategory: String,
	val transactionLatitude: String,
	val transactionLongitude: String,
	var createdDate: Instant = Instant.now(),
	val merchantId: String? = null
) {
	constructor() : this(
		UUID.randomUUID(),
		"accountId",
		"customerId",
		"transactionType",
		"transactionDate",
		"transactionAmount",
		"transactionCurrency",
		"transactionRefrence",
		"transactionStatus",
		"transactionCategory",
		"transactionSubCategory",
		"transactionLatitude",
		"transactionLongitude",
		Instant.now(),
		UUID.randomUUID().toString()
	)

	@PrePersist
	private fun prePersist() {
		createdDate = Instant.now()
	}
}

