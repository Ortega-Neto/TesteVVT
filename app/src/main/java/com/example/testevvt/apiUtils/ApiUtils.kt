package com.example.testevvt.apiUtils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ApiUtils {
    companion object{
        val baseUrl = "digimon-api.vercel.app"

        fun launchDataLoad(
            onFailure: (Throwable) -> Unit,
            block: suspend () -> Unit
        ): Job {
            return CoroutineScope(Dispatchers.IO).launch {
                try {
                    block()
                } catch (error: Throwable) {
                    onFailure(error)
                }
            }
        }
    }
}