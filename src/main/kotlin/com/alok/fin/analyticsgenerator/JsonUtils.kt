package com.alok.fin.analyticsgenerator

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.io.IOException
import java.lang.RuntimeException

class JsonUtils {
    companion object {
        val mapper: ObjectMapper = jacksonObjectMapper().registerModule(com.fasterxml.jackson.datatype.jsr310.JavaTimeModule())
            .registerModule(com.fasterxml.jackson.datatype.jdk8.Jdk8Module())
            .registerModule(com.fasterxml.jackson.module.paramnames.ParameterNamesModule())
            .configure(SerializationFeature.INDENT_OUTPUT, true)
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)

        inline fun <reified T> fromJson(json: String, clazz: Class<T>): T {
            return try {
                mapper.readValue(json, T::class.java)
            } catch (e: IOException) {
                throw RuntimeException(
                    java.lang.String.format(
                        "Unable to parse json value into java object of type '%s' using jackson ObjectMapper",
                        T::class.java.getName()), e)
            }
        }

        fun toJson(obj: Any): String {
            return try {
                mapper.writeValueAsString(obj)
            } catch (e: JsonProcessingException) {
                throw RuntimeException(java.lang.String.format(
                    "Unable to convert Java object of type '%s' to json using jackson ObjectMapper",
                    obj.javaClass.getName()), e)
            }
        }
    }
}