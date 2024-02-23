package com.lesa.aot.data.titans.network.models

import com.lesa.aot.models.Titan
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TitanApiDto(
    @SerialName("abilities")
    val abilities: List<String>,
    @SerialName("allegiance")
    val allegiance: String,
    @SerialName("current_inheritor")
    val currentInheritor: String,
    @SerialName("former_inheritors")
    val formerInheritors: List<String>,
    @SerialName("height")
    val height: String,
    @SerialName("id")
    val id: Int,
    @SerialName("img")
    val img: String,
    @SerialName("name")
    val name: String
)

fun TitanApiDto.toTitan(): Titan {
    return Titan(
        abilities = abilities,
        allegiance = allegiance,
        currentInheritorId = currentInheritor.substringAfterLast('/').toInt(),
        formerInheritorsIds = formerInheritors.map { it.substringAfterLast('/').toInt() },
        height = height,
        id = id,
        img = img,
        name = name
    )
}