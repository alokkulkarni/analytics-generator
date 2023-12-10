package com.alok.fin.analyticsgenerator

import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.support.GenericMessage
import org.springframework.stereotype.Component

@Component
class KafkaConsumer {

	@KafkaListener(topics = ["transactions_db.transactions_db.transactions"], containerFactory = "kafkaListenerContainerFactory", groupId = "transactions-group")
	fun consumer(@Payload(required = false) message: TransactionsListner) {
//		println("Received Message in group transactions: ${message}")
		if (message.transactionsPayload.op == "c")
			println("Received Message in group transactions with op c: ${message.transactionsPayload.after}")
		else if (message.transactionsPayload.op == "u"){
			println("Received Message in group transactions b op u: ${message.transactionsPayload.after}")
			println("Received Message in group transactions a op u: ${message.transactionsPayload.before}")
		}
		else if (message.transactionsPayload.op == "d"){
			println("Received Message in group transactions b op d: ${message.transactionsPayload.before}")
		}
	}
}