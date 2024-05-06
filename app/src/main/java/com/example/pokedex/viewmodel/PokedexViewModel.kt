package com.example.pokedex.viewmodel

import android.graphics.drawable.Drawable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.data.model.PokemonData
import com.example.pokedex.domain.PokemonDataUseCase
import com.example.pokedex.domain.PokemonListUseCase
import com.example.pokedex.domain.PokemonPaletteUseCase
import com.example.pokedex.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class PokedexViewModel @Inject constructor(
  private val pokemonListUseCase: PokemonListUseCase,
  private val pokemonDataUseCase: PokemonDataUseCase,
  private val pokemonPaletteUseCase: PokemonPaletteUseCase,
) : ViewModel() {
    private val _pokemonList = MutableStateFlow<List<PokemonData>>(emptyList())
    val pokemonList = _pokemonList.asStateFlow()

    private val _selectedPokemonData = MutableStateFlow<PokemonData?>(null)
    val selectedPokemonData = _selectedPokemonData.asStateFlow()

    fun fetchPokemonList(offset: Int = 0) {
        viewModelScope.launch {
            when (val response = pokemonListUseCase.fetchPokemonList(offset = offset, limit = 20)) {
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    Timber.tag("MyTest").d("FetchPokemonList Success")
                    response.data?.list?.let { data ->
                        _pokemonList.update { it + data }
                    }
                }
                is Resource.Error -> {

                }
            }
        }
    }

    fun getPokemonData(id: Int) {
        viewModelScope.launch {
            when (val response = pokemonDataUseCase.fetchPokemonDataById(id)) {
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    _selectedPokemonData.value = response.data
                }
                is Resource.Error -> {

                }
            }
        }
    }

    fun getDominantColor(drawable: Drawable, defaultColorInt: Int): Int {
        return pokemonPaletteUseCase
            .getPaletteFromSpriteDrawable(drawable)
            .getDominantColor(defaultColorInt)
    }
}