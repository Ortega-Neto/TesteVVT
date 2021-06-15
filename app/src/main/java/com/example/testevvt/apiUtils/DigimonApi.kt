package com.example.testevvt.apiUtils

import com.example.testevvt.ListaDeDigimons
import retrofit2.Response

class DigimonApi {
    suspend fun buscarDigimons(url: String): Response<ListaDeDigimons>{
        val retrofit = Retrofit().criarRetrofit(url)
        return retrofit!!.buscarDigimons()
    }
}