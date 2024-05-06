package com.example.pokedex.data.model

import com.google.gson.annotations.SerializedName

data class PokemonData(
    var id: Int? = null,
    val name: String? = null,
    val types: List<PokemonTypeAttribute>? = null,
    val url: String? = null,
    var spriteUrl: String? = null,
    @SerializedName("height")
    val heightInDm: Int? = null,
    @SerializedName("weight")
    val weightInHg: Int? = null,
    val stats: List<PokemonStat>? = null,
)