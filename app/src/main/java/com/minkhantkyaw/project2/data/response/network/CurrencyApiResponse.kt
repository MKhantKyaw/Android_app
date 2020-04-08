package com.minkhantkyaw.project2.data.response.network

import com.minkhantkyaw.project2.data.response.network.Rates


data class CurrencyApiResponse(
    val description: String,
    val info: String,
    val rates: Rates,
    val timestamp: String
)