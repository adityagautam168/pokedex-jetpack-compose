package com.example.pokedex.domain

import com.example.pokedex.data.repository.PokemonRepository
import javax.inject.Inject

class PokemonDataUseCase @Inject constructor(private val pokemonRepository: PokemonRepository) {

    suspend fun fetchPokemonDataById(id: Int) = pokemonRepository.fetchPokemonDataById(id)

    suspend fun fetchPokemonDataByName(name: String) = pokemonRepository.fetchPokemonDataByName(name)
}