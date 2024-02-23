package com.lesa.aot.models

data class Character(
    val id: Int,
    val name: String,
    val img: String?,
    val species: List<String>,

)

fun Character.toItem(): Item {
    return Item(
        id = id,
        name = name,
        img = img
    )
}
