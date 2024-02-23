package com.lesa.aot.ui.screens.titan

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lesa.aot.AotApplication.Companion.realm
import com.lesa.aot.data.titans.storage.TitanEntity
import com.lesa.aot.data.titans.storage.toTitan
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class TitanViewModel(
    titanName: String
): ViewModel() {
    val titan = realm.query<TitanEntity>(
            "name == $0", titanName
        )
        .first()
        .asFlow()
        .map { change ->
            change.obj?.toTitan()
        }.stateIn(
            viewModelScope, SharingStarted.WhileSubscribed(), null
        )


}

