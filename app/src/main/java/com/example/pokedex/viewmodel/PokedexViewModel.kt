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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.DurationUnit
import kotlin.time.toDuration

@HiltViewModel
class PokedexViewModel @Inject constructor(
  private val pokemonListUseCase: PokemonListUseCase,
  private val pokemonDataUseCase: PokemonDataUseCase,
  private val pokemonPaletteUseCase: PokemonPaletteUseCase,
) : ViewModel() {
    private val _pokemonList = MutableStateFlow<List<PokemonData>>(emptyList())

    private var searchJob: Job? = null
    private val searchDebouncePeriod = 300.toDuration(DurationUnit.MILLISECONDS)
    private val _pokemonSearchFilteredList = MutableStateFlow<List<PokemonData>?>(null)

    val pokemonList = combine(
        _pokemonList,
        _pokemonSearchFilteredList
    ) { list: List<PokemonData>, filteredList: List<PokemonData>? ->
        if (filteredList != null) {
            return@combine filteredList
        } else {
            return@combine list
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val pageItemCount = 20
    private var currentPageIndex = 0
    private var nextPageExists = true

    private val _selectedPokemonData = MutableStateFlow<PokemonData?>(null)
    val selectedPokemonData = _selectedPokemonData.asStateFlow()

    fun fetchPokemonList(coroutineScope: CoroutineScope = viewModelScope) {
        if (nextPageExists) {
            coroutineScope.launch {
                when (val response = pokemonListUseCase.fetchPokemonList(
                    offset = pageItemCount * currentPageIndex,
                    limit = pageItemCount,
                )) {
                    is Resource.Loading -> {

                    }
                    is Resource.Success -> {
                        response.data?.list?.let { data ->
                            _pokemonList.update { it + data }
                            currentPageIndex++
                            nextPageExists = response.data.next != null
                        }
                    }
                    is Resource.Error -> {

                    }
                }
            }
        }
    }

    fun fetchPokemonDataById(id: Int, coroutineScope: CoroutineScope = viewModelScope) {
        coroutineScope.launch {
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

    fun searchPokemon(searchQuery: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            if (searchQuery.isBlank()) {
                _pokemonSearchFilteredList.emit(null)
            } else {
                _pokemonSearchFilteredList.emit(
                    value = _pokemonList.value.filter { data ->
                        data.name?.contains(searchQuery.trim(), ignoreCase = true) ?: false
                    }
                )
            }
        }
    }

    @OptIn(FlowPreview::class)
    fun searchPokemon(queryFlow: Flow<String>) {
        viewModelScope.launch {
            queryFlow.debounce(searchDebouncePeriod).collectLatest { searchQuery ->
                searchPokemon(searchQuery)
            }
        }
    }
}