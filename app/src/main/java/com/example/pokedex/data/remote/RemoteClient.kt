package com.example.pokedex.data.remote

import com.example.pokedex.data.model.PokemonData
import com.example.pokedex.data.model.PokemonList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RemoteClient {

    @GET("pokemon")
    suspend fun fetchPokemonList(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): PokemonList

    @GET("pokemon/{id}")
    suspend fun fetchPokemonDataById(
        @Path("id") id: Int
    ): PokemonData

    @GET("pokemon/{name}")
    suspend fun fetchPokemonDataByName(
        @Path("name") name: String
    ): PokemonData
}