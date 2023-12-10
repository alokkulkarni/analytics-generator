package com.alok.fin.analyticsgenerator

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.util.*


@Entity(name = "merchant")
data class Merchant(
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	val merchantId: String,
	val merchantName: String,
	val merchantLogo: String,
	val merchantCity: String,
	val merchantCountry: String,
	val merchantCategory: String,
	val merchantCategoryCode: String,
	val merchantLatitude: String,
	val merchantLongitude: String
){
	constructor(): this(
		UUID.randomUUID().toString(),
		"merchantName",
		"merchantLogo",
		"merchantCity",
		"merchantCountry",
		"merchantCategory",
		"merchantCategoryCode",
		"merchantLatitude",
		"merchantLongitude")
}