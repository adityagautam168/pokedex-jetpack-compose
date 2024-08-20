package com.example.pokedex.domain

import com.example.pokedex.data.model.PokemonList
import com.example.pokedex.data.repository.PokemonRepository
import com.example.pokedex.util.Constants
import com.example.pokedex.util.Resource
import javax.inject.Inject

class PokemonListUseCase @Inject constructor(private val pokemonRepository: PokemonRepository) {

    suspend fun fetchPokemonList(
        offset: Int,
        limit: Int
    ) : Resource<PokemonList> {
        val resource = pokemonRepository.fetchPokemonList(offset, limit)
        if (resource is Resource.Success) {
            resource.data?.list?.forEach {
                it.id = parsePokemonIdFromUrl(it.url)
                it.spriteUrl = "${Constants.POKEMON_SPRITE_BASE_URL}${it.id}.png"
            }
        }
        return resource
    }

    private fun parsePokemonIdFromUrl(url: String?): Int {
        val partitions = url?.split("/") ?: emptyList()
        val id = partitions.last {
            it.toIntOrNull() != null
        }
        return id.toInt()
    }
}