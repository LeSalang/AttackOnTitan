package com.lesa.aot.data.titans.storage

import com.lesa.aot.models.Titan
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class TitanEntity(): RealmObject {
    @PrimaryKey
    var id: Int = 0
    var abilities: RealmList<String> = realmListOf()
    var allegiance: String = ""
    var currentInheritorId: Int = 0
    var formerInheritorsIds: RealmList<Int> = realmListOf()
    var height: String = ""
    var img: String = ""
    var name: String = ""
    constructor(
        id: Int,
        abilities: RealmList<String>,
        allegiance: String,
        currentInheritorId: Int,
        formerInheritorsIds: RealmList<Int>,
        height: String,
        img: String,
        name: String
    ): this() {
        this.id = id
        this.abilities = abilities
        this.allegiance = allegiance
        this.currentInheritorId = currentInheritorId
        this.formerInheritorsIds = formerInheritorsIds
        this.height = height
        this.img = img
        this.name = name
    }

}

fun TitanEntity.toTitan(): Titan {
    return Titan(
        abilities = abilities,
        allegiance = allegiance,
        currentInheritorId = currentInheritorId,
        formerInheritorsIds = formerInheritorsIds,
        height = height,
        id = id,
        img = img,
        name = name
    )
}