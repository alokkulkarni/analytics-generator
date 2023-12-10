package com.alok.fin.analyticsgenerator

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.kafka.core.KafkaTemplate

@SpringBootApplication
class AnalyticsGeneratorApplication {

	@Bean
	fun run(kafkaTemplate: KafkaTemplate<String, Transactions>, transactionsRepository: TransactionsRepository, merchantRepository: MerchantRepository) = CommandLineRunner {
		while (true) {
			Thread.sleep(5000)
			for (i in 1..100) {
				CreateTransactions().createTransaction(merchantRepository, transactionsRepository).apply { println("Created transaction: $this") }
			}
		}
//		KafkaProducer(kafkaTemplate, transactionsRepository, merchantRepository).producer()
	}
}

fun main(args: Array<String>) {
	runApplication<AnalyticsGeneratorApplication>(*args)
}


