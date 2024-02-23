package com.lesa.aot.ui.screens.characterlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.lesa.aot.data.characters.CharactersRepository
import com.lesa.aot.models.Item
import com.lesa.aot.models.toItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val charactersRepository: CharactersRepository
): ViewModel() {
    private val _charactersState: MutableStateFlow<PagingData<Item>> = MutableStateFlow(value = PagingData.empty())
    val characterState: StateFlow<PagingData<Item>> get() = _charactersState.asStateFlow()

    init {
        getAllCharacters()
    }

    fun getAllCharacters() {
        viewModelScope.launch {
            charactersRepository
                .getListOfCharactersFromApi()
                .distinctUntilChanged()
                .cachedIn(viewModelScope)
                .collect {
                    _charactersState.value = it.map {
                        it.toItem()
                    }
                }
        }
    }
}