package com.lesa.aot.models

import com.lesa.aot.data.titans.storage.TitanEntity
import io.realm.kotlin.ext.toRealmList

data class Titan(
    val abilities: List<String>,
    val allegiance: String,
    val currentInheritorId: Int,
    val formerInheritorsIds: List<Int>,
    val height: String,
    val id: Int,
    val img: String,
    val name: String
)

fun Titan.toTitanEntity(): TitanEntity {
    return TitanEntity(
        id = id,
        abilities = abilities.toRealmList(),
        allegiance = allegiance,
        currentInheritorId = currentInheritorId,
        formerInheritorsIds = formerInheritorsIds.toRealmList(),
        height = height,
        img = img,
        name = name
    )
}

fun Titan.toItem(): Item {
    return Item(
        id = id,
        name = name,
        img = img
    )
}