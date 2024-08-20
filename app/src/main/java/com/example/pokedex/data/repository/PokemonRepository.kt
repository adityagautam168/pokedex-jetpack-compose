package com.example.pokedex.data.repository

import com.example.pokedex.data.model.PokemonData
import com.example.pokedex.data.model.PokemonList
import com.example.pokedex.util.Resource

abstract class PokemonRepository {
    abstract suspend fun fetchPokemonList(offset: Int, limit: Int) : Resource<PokemonList>

    abstract suspend fun fetchPokemonDataById(id: Int) : Resource<PokemonData>

    abstract suspend fun fetchPokemonDataByName(name: String) : Resource<PokemonData>
}