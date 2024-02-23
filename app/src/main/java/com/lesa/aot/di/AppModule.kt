package com.lesa.aot.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.lesa.aot.data.characters.CharacterRepositoryImpl
import com.lesa.aot.data.characters.CharactersRepository
import com.lesa.aot.data.characters.network.CharactersApiService
import com.lesa.aot.data.titans.TitansRepository
import com.lesa.aot.data.titans.TitansRepositoryImpl
import com.lesa.aot.data.titans.network.TitansApiService
import com.lesa.aot.data.titans.storage.TitanEntity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val baseUrl = "https://api.attackontitanapi.com/"

    @OptIn(ExperimentalSerializationApi::class)
    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
        explicitNulls = false
    }

    private val loggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(
            json.asConverterFactory("application/json".toMediaType())
        )
        .baseUrl(baseUrl)
        .client(client)
        .build()

    private val titansApiService: TitansApiService by lazy {
        retrofit.create(TitansApiService::class.java)
    }

    private val charactersApiService: CharactersApiService by lazy {
        retrofit.create(CharactersApiService::class.java)
    }

    private var realm: Realm = Realm.open(
        configuration = RealmConfiguration.create(
            schema = setOf(
                TitanEntity::class,
            )
        )
    )

    @Provides
    fun provideTitansRepository(
    ): TitansRepository {
        return TitansRepositoryImpl(titansApiService)
    }

    @Provides
    fun provideCharactersRepository(
    ): CharactersRepository {
        return CharacterRepositoryImpl(charactersApiService)
    }

}