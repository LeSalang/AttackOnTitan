package com.lesa.aot.data.characters

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.lesa.aot.data.characters.network.CharacterPagingSource
import com.lesa.aot.data.characters.network.CharactersApiService
import com.lesa.aot.data.characters.network.models.toCharacter
import com.lesa.aot.models.Character
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {
    suspend fun getListOfCharactersFromApi(): Flow<PagingData<Character>>
    suspend fun getCharacterById(id: Int): Character
}

class CharacterRepositoryImpl(
    private val charactersApiService: CharactersApiService
): CharactersRepository {
    override suspend fun getListOfCharactersFromApi(): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 5
            ),
            pagingSourceFactory = {
                CharacterPagingSource(charactersApiService)
            }
        ).flow
    }

    override suspend fun getCharacterById(id: Int): Character {
        return charactersApiService.getCharacterById(id = id).toCharacter()
    }

}