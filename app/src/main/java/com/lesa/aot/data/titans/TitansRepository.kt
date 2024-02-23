package com.lesa.aot.data.titans

import com.lesa.aot.data.titans.network.TitansApiService
import com.lesa.aot.data.titans.network.models.toTitan
import com.lesa.aot.models.Titan

interface TitansRepository {
    suspend fun getListOfTitansFromApi(): List<Titan>
}

class TitansRepositoryImpl(
    private val titansApiService: TitansApiService,
): TitansRepository {

    override suspend fun getListOfTitansFromApi(): List<Titan> {
        return titansApiService.getAllTitans().titans.map {
            it.toTitan()
        }
    }

}