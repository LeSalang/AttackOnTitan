package com.lesa.aot.data.characters.network

import com.lesa.aot.data.characters.network.models.AllCharactersApiDto
import dagger.Provides
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharactersApiService {
    @GET("characters")
    suspend fun getAllCharacters(
        @Query("page") page: Int
    ): AllCharactersApiDto

    @GET("characters/{id}")
    suspend fun getCharacterById(@Path("id") id: Int): AllCharactersApiDto.CharacterApiDto
}