package com.example.pokedex.data.model

import com.google.gson.annotations.SerializedName

data class PokemonList(
    val count: Int? = null,
    val previous: String? = null,
    val next: String? = null,
    @SerializedName("results")
    val list: List<PokemonData>? = null,
)