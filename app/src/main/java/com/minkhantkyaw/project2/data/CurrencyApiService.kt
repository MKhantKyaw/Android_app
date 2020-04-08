package com.minkhantkyaw.project2.data

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.minkhantkyaw.project2.data.response.ConnectivityInterceptor
import com.minkhantkyaw.project2.data.response.network.CurrencyApiResponse
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


const val API_END = "https://forex.cbm.gov.mm/api/"

interface CurrencyApiService {
    @GET("latest")
    fun getCurrency() : Deferred<CurrencyApiResponse>

    companion object{
        operator fun invoke(
            connectivityInterceptor: ConnectivityInterceptor
        ) : CurrencyApiService {
            val requestInterceptor = Interceptor{chain ->
                var url = chain.request()
                    .url()
                    .newBuilder()
                    .build()

                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()
                return@Interceptor chain.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .addInterceptor(connectivityInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(API_END)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CurrencyApiService::class.java)
        }
    }
}