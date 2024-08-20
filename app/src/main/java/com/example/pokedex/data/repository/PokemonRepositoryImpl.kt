package com.example.pokedex.data.repository

import com.example.pokedex.data.model.PokemonData
import com.example.pokedex.data.model.PokemonList
import com.example.pokedex.data.remote.RemoteClient
import com.example.pokedex.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class PokemonRepositoryImpl @Inject constructor(
    private val remoteClient: RemoteClient
) : PokemonRepository() {

    override suspend fun fetchPokemonList(offset: Int, limit: Int): Resource<PokemonList> {
        val response = try {
            val list = remoteClient.fetchPokemonList(offset, limit)
            Resource.Success(data = list)
        } catch (e: Exception) {
            Resource.Error(message = e.message ?: "An unknown error occurred")
        }
        return response
    }

    override suspend fun fetchPokemonDataById(id: Int): Resource<PokemonData> {
        val response = try {
            Resource.Success(data = remoteClient.fetchPokemonDataById(id))
        } catch (e: Exception) {
            Resource.Error(message = e.message ?: "An unknown error occurred")
        }
        return response
    }

    override suspend fun fetchPokemonDataByName(name: String): Resource<PokemonData> {
        val response = try {
            Resource.Success(data = remoteClient.fetchPokemonDataByName(name))
        } catch (e: Exception) {
            Resource.Error(message = e.message ?: "An unknown error occurred")
        }
        return response
    }
}