package com.example.cantor.data

data class Data(
    val base: String,
    val date: String,
    val historical: Boolean,
    val rates: Rates,
    val success: Boolean,
    val timestamp: Int
)