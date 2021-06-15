package com.example.testevvt.apiUtils

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class Retrofit {
    fun criarRetrofit(url: String): BuscarDigimons?{
        return getRetrofitInstance(url).create(BuscarDigimons::class.java)
    }

    fun getRetrofitInstance(path: String): Retrofit {
        return Retrofit.Builder()
                .baseUrl(path)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttp.build())
                .build()
    }

    private val okHttp = OkHttpClient.Builder()
            .connectTimeout(60000, TimeUnit.MILLISECONDS)
            .callTimeout(60000, TimeUnit.MILLISECONDS)
            .writeTimeout(60000, TimeUnit.MILLISECONDS)
            .readTimeout(60000, TimeUnit.MILLISECONDS)
}