package com.lesa.aot.ui.screens.titans

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lesa.aot.AotApplication.Companion.realm
import com.lesa.aot.data.titans.TitansRepository
import com.lesa.aot.data.titans.storage.TitanEntity
import com.lesa.aot.data.titans.storage.toTitan
import com.lesa.aot.models.Item
import com.lesa.aot.models.Titan
import com.lesa.aot.models.toItem
import com.lesa.aot.models.toTitanEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class TitansUiState {
    class Error(val message: String = "error") : TitansUiState()
    class Loading(val message: String = "loading"): TitansUiState()
    class Success(val listOfTitanItems: List<Item>): TitansUiState()
    class Local(val listOfTitanItems: StateFlow<List<Item>>): TitansUiState()
}

@HiltViewModel
class TitansViewModel @Inject constructor(
    private val titansRepository: TitansRepository
): ViewModel() {
    var titansUiState: TitansUiState by mutableStateOf(TitansUiState.Loading())
        private set
    //private val realm = AotApplication.realm

    val localTitanItems = realm
        .query<TitanEntity>()
        .asFlow()
        .map { results ->
            results.list.toList().map {
                it.toTitan().toItem()
            }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            emptyList()
        )

    init {
        getAllTitansFromApi()
    }

    private fun getAllTitansFromApi() {
        viewModelScope.launch {
            titansUiState = TitansUiState.Loading()
            titansUiState = try {
                val listOfTitans = titansRepository.getListOfTitansFromApi()
                refreshTitansLocalDatabase(listOfTitans)
                TitansUiState.Success(listOfTitanItems = listOfTitans.map { it.toItem() })
            } catch (e: Exception) {
                try {
                    TitansUiState.Local(listOfTitanItems = localTitanItems)
                } catch (e: Exception) {
                    TitansUiState.Error()
                }
            }
        }
    }

    private fun refreshTitansLocalDatabase(listOfTitans: List<Titan>) {
        viewModelScope.launch {
            realm.write {
                listOfTitans.forEachIndexed { index: Int, titan: Titan ->
                    val titanEntity = titan.toTitanEntity()
                    copyToRealm(instance = titanEntity, updatePolicy = UpdatePolicy.ALL)
                }
            }
        }
    }
}