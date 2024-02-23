package com.lesa.aot

import android.app.Application
import com.lesa.aot.data.titans.storage.TitanEntity
import dagger.hilt.android.HiltAndroidApp
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

@HiltAndroidApp
class AotApplication: Application() {

    companion object {
        lateinit var realm: Realm
    }

    override fun onCreate() {
        super.onCreate()
        realm = Realm.open(
            configuration = RealmConfiguration.create(
                schema = setOf(
                    TitanEntity::class,
                )
            )
        )
    }

}