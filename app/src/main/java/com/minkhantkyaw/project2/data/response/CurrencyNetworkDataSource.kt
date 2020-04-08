package com.minkhantkyaw.project2.data.response

import androidx.lifecycle.LiveData
import com.minkhantkyaw.project2.data.response.network.CurrencyApiResponse

interface CurrencyNetworkDataSource  {
    val downloadedCurrency : LiveData<CurrencyApiResponse>

    suspend fun fetchCurrency()
}