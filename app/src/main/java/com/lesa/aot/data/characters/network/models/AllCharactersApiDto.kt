package com.lesa.aot.data.characters.network.models

import com.lesa.aot.models.Character
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AllCharactersApiDto(
    @SerialName("info")
    val info: Info,
    @SerialName("results")
    val characterApiDtos: List<CharacterApiDto>
) {
    @Serializable
    data class Info(
        @SerialName("next_page")
        val nextPage: String?,
        @SerialName("pages")
        val pages: Int
    )

    @Serializable
    data class CharacterApiDto(
        @SerialName("age")
        val age: String,
        @SerialName("alias")
        val alias: List<String>,
        @SerialName("birthplace")
        val birthplace: String,
        @SerialName("episodes")
        val episodes: List<String>,
        @SerialName("gender")
        val gender: String,
        @SerialName("groups")
        val groups: List<Group>,
        @SerialName("height")
        val height: String,
        @SerialName("id")
        val id: Int,
        @SerialName("img")
        val img: String?,
        @SerialName("name")
        val name: String,
        @SerialName("occupation")
        val occupation: String,
        @SerialName("relatives")
        val relatives: List<Relative>,
        @SerialName("residence")
        val residence: String,
        @SerialName("roles")
        val roles: List<String>,
        @SerialName("species")
        val species: List<String>,
        @SerialName("status")
        val status: String //todo navernoe ubrat'
    ) {
        @Serializable
        data class Group(
            @SerialName("name")
            val name: String,
            @SerialName("sub_groups")
            val subGroups: List<String>
        )

        @Serializable
        data class Relative(
            @SerialName("family")
            val family: String,
            @SerialName("members")
            val members: List<String>
        )
    }
}

fun AllCharactersApiDto.CharacterApiDto.toCharacter(): Character {
    return Character(
        id = id,
        name = name,
        img = img,
        species = species
    )
}