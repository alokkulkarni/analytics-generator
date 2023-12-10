package com.alok.fin.analyticsgenerator

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.apache.tomcat.util.json.JSONParser
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.*
import org.springframework.kafka.support.serializer.JsonDeserializer
import org.springframework.kafka.support.serializer.JsonSerializer


@Configuration
@EnableKafka
class KafkaConfiguration {

    @Bean
    fun kafkaConsumerFactory(): ConsumerFactory<String, TransactionsListner> {
        val props = HashMap<String, Any>()
        props[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = "localhost:9092"
        props[ConsumerConfig.GROUP_ID_CONFIG] = "transactions-group"
        props[ConsumerConfig.AUTO_OFFSET_RESET_CONFIG] = "earliest"
        props[ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG] = true
        props[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        props[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = customJsonDeserializer()
        return DefaultKafkaConsumerFactory(props, StringDeserializer(), customJsonDeserializer())
    }

    @Bean
    fun kafkaProducerFactory(): ProducerFactory<String, Transactions> {
        val props = HashMap<String, Any>()
        props[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = "localhost:9092"
        props[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        props[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = JsonSerializer::class.java
        return DefaultKafkaProducerFactory(props, StringSerializer(), JsonSerializer())
    }

    @Bean
    fun kafkaTemplate(): KafkaTemplate<String, Transactions> {
        return KafkaTemplate(kafkaProducerFactory())
    }

    @Bean
    fun kafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, TransactionsListner> {
        val factory = ConcurrentKafkaListenerContainerFactory<String, TransactionsListner>()
        factory.consumerFactory = kafkaConsumerFactory()
        return factory
    }

    private fun customJsonDeserializer(): JsonDeserializer<TransactionsListner> {
        val objectMapper = ObjectMapper()
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        val deserializer = JsonDeserializer(TransactionsListner::class.java, objectMapper)
        deserializer.setRemoveTypeHeaders(false)
        deserializer.setUseTypeHeaders(true)
        deserializer.addTrustedPackages("com.alok.fin.analyticsgenerator")
        deserializer.setUseTypeMapperForKey(true)
        return deserializer
    }

    private fun customMerchantDeserializer(): JsonDeserializer<Merchant> {
        val objectMapper = ObjectMapper()
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        val deserializer = JsonDeserializer(Merchant::class.java, objectMapper)
        deserializer.setRemoveTypeHeaders(false)
        deserializer.setUseTypeHeaders(true)
        deserializer.addTrustedPackages("com.alok.fin.analyticsgenerator")
        deserializer.setUseTypeMapperForKey(true)
        return deserializer
    }
}