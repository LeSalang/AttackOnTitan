package com.lesa.aot

import com.lesa.aot.data.titans.storage.TitanEntity
import com.lesa.aot.models.Item
import com.lesa.aot.models.Titan
import com.lesa.aot.models.toItem
import com.lesa.aot.models.toTitanEntity
import io.realm.kotlin.ext.realmListOf
import org.junit.Assert
import org.junit.Test

class TitanModelTests {
    @Test
    fun fromTitanToTitanEntity() {
        val titan = Titan(
            abilities = listOf(),
            allegiance = "omittantur",
            currentInheritorId = 2027,
            formerInheritorsIds = listOf(),
            height = "tristique",
            id = 2060,
            img = "sem",
            name = "Brigitte Bird"
        )
        val titanEntity = TitanEntity(
            abilities = realmListOf(),
            allegiance = "omittantur",
            currentInheritorId = 2027,
            formerInheritorsIds = realmListOf(),
            height = "tristique",
            id = 2060,
            img = "sem",
            name = "Brigitte Bird"
        )
        Assert.assertEquals(
            titanEntity,
            titan.toTitanEntity()
        )
    }

     @Test
    fun fromTitanToItem() {
        val titan = Titan(
            abilities = listOf(),
            allegiance = "omittantur",
            currentInheritorId = 2027,
            formerInheritorsIds = listOf(),
            height = "tristique",
            id = 2060,
            img = "sem",
            name = "Brigitte Bird"
        )
        val item = Item(
            id = 2060,
            img = "sem",
            name = "Brigitte Bird"
        )
        Assert.assertEquals(
            item,
            titan.toItem()
        )
    }


}