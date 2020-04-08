package com.minkhantkyaw.project2.data.response

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.minkhantkyaw.project2.data.CurrencyApiService
import com.minkhantkyaw.project2.data.response.network.CurrencyApiResponse
import com.minkhantkyaw.project2.utils.NoConnectivityException

class CurrencyNetworkDataSourceImpl(
    private val currencyApiService: CurrencyApiService
) : CurrencyNetworkDataSource {
    private val _downloadedCurrency = MutableLiveData<CurrencyApiResponse>()
    override val downloadedCurrency: LiveData<CurrencyApiResponse>
        get() = _downloadedCurrency

    override suspend fun fetchCurrency() {
        try {
            val fetchCurrencyApiResponse = currencyApiService
                .getCurrency()
                .await()
            _downloadedCurrency.postValue(fetchCurrencyApiResponse)
        } catch (e : NoConnectivityException) {
            Log.e("Connectivity", "No Intennet Connection")
        }
    }
}