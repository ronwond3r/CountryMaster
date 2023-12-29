package com.example.countrymaster.networking

data class CountryInfo(
    val officialName: String,
    val nativeName: String,
    val currency: String,
    val region: String,
    val capital: String,
    val borders: List<String>,
    val area: Double,
    val population: Long,
    val flag: String
)

