package com.example.testevvt

import com.example.testevvt.apiUtils.ApiUtils
import com.example.testevvt.apiUtils.DigimonApi
import retrofit2.Response

class MainController() {
    var _erro: String = ""
    lateinit var resposta: Response<ListaDeDigimons>
    var _respostaSalva: Boolean = false

    fun onBuscarDigimonError(e: Throwable){
        _erro = e.message.toString()
        _respostaSalva = true
    }

    fun buscarDigimons(url: String){
        ApiUtils.launchDataLoad(::onBuscarDigimonError){
            resposta = DigimonApi().buscarDigimons(url)
            _respostaSalva = true
        }
    }

}