package com.alok.fin.analyticsgenerator

import org.springframework.kafka.core.KafkaTemplate

class KafkaProducer(private val kafkaTemplate: KafkaTemplate<String, Transactions>, private val transactionsRepository: TransactionsRepository, private val merchantRepository: MerchantRepository) {

	fun producer() {
        val createTransaction = CreateTransactions().createTransaction(merchantRepository, transactionsRepository)
        val transactions = transactionsRepository.findById(createTransaction).get()
        kafkaTemplate.send("transactions_db.transactions_db.transactions", transactions)
	}
}