package com.lesa.aot.data.titans.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AllTitansApiDto(
    @SerialName("results")
    val titans: List<TitanApiDto>
)
