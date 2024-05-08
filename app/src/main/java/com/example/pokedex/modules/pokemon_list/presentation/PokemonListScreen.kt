package com.example.pokedex.modules.pokemon_list.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.pokedex.R
import com.example.pokedex.modules.pokemon_list.components.PokedexEntryGrid
import com.example.pokedex.modules.pokemon_list.components.SearchBar
import com.example.pokedex.viewmodel.PokedexViewModel

@Composable
fun PokemonListScreen(navController: NavController) {
    val viewModel = hiltViewModel<PokedexViewModel>()

    val pokemonList by viewModel.pokemonList.collectAsState()
    if (pokemonList.isEmpty()) {
        viewModel.fetchPokemonList()
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column {
            Spacer(modifier = Modifier.height(32.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_pokemon_logo),
                contentDescription = "Pokemon logo",
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(24.dp))
            SearchBar(
                modifier = Modifier.padding(horizontal = 24.dp),
                hint = "Search..."
            )
            Spacer(modifier = Modifier.height(24.dp))
            PokedexEntryGrid(
                entryList = pokemonList,
                navController = navController,
                onEndReached = { viewModel.fetchPokemonList() }
            )
        }
    }
}