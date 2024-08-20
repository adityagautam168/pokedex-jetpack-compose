package com.example.pokedex.modules.pokemon_list.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pokedex.data.model.PokemonData
import timber.log.Timber
import kotlin.random.Random

@Composable
fun PokedexEntryGrid(
    modifier: Modifier = Modifier,
    entryList: List<PokemonData>,
    navController: NavController,
    onEndReached: (() -> Unit)? = null,
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(24.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        content = {
            items(
                count = entryList.size,
                key = { index ->
                    entryList[index].name ?: ""
                },
                itemContent = { index ->
                    if (index == entryList.size - 1) {
                        onEndReached?.invoke()
                    }
                    PokedexEntry(
                        modifier = Modifier.fillMaxWidth(),
                        pokemon = entryList[index],
                        navController = navController,
                    )
                }
            )
        }
    )
}