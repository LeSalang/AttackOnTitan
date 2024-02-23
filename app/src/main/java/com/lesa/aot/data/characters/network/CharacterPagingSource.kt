package com.lesa.aot.data.characters.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.lesa.aot.data.characters.network.models.toCharacter
import com.lesa.aot.models.Character
import retrofit2.HttpException
import java.io.IOException

class CharacterPagingSource(
    private val charactersApiService: CharactersApiService
): PagingSource<Int, Character>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        return try {
            val currentPage = params.key ?: 1
            val allCharacters = charactersApiService.getAllCharacters(
                page = currentPage
            )
            LoadResult.Page(
                data = allCharacters.characterApiDtos.map {
                    it.toCharacter()
                },
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (allCharacters.info.nextPage == null) null else currentPage + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition
    }
}