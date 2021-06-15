package com.example.testevvt.apiUtils

import com.example.testevvt.ListaDeDigimons
import retrofit2.Response
import retrofit2.http.POST

interface BuscarDigimons {

    @POST("/api/digimon")
    suspend fun buscarDigimons(): Response<ListaDeDigimons>
}