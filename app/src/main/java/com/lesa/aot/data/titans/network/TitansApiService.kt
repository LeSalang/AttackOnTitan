package com.lesa.aot.data.titans.network

import com.lesa.aot.data.titans.network.models.AllTitansApiDto
import retrofit2.http.GET

interface TitansApiService {
    @GET("titans")
    suspend fun getAllTitans(): AllTitansApiDto
}