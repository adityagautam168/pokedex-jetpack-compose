package com.example.pokedex.modules.pokemon_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.pokedex.data.model.PokemonData
import com.example.pokedex.viewmodel.PokedexViewModel
import java.util.Locale

@Composable
fun PokedexEntry(
    modifier: Modifier = Modifier,
    pokemon: PokemonData,
    navController: NavController,
) {
    val viewModel = hiltViewModel<PokedexViewModel>()

    val defaultColor = MaterialTheme.colorScheme.surface
    val dominantColor = remember {
        mutableStateOf(defaultColor)
    }

    val name = remember {
        pokemon.name?.replaceFirstChar {
            if (it.isLowerCase()) {
                it.titlecase(Locale.getDefault())
            } else {
                it.toString()
            }
        }
    }

    Box(
        modifier = modifier
            .aspectRatio(1f)
            .clip(RoundedCornerShape(16.dp))
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        dominantColor.value,
                        defaultColor,
                    )
                )
            )
            .clickable {
                navController.navigate(
                    "pokemon_details_screen?id={id}&spriteUrl={spriteUrl}&dominantColor={dominantColor}"
                        .replace(oldValue = "{id}", newValue = (pokemon.id?.toString() ?: ""))
                        .replace(oldValue = "{spriteUrl}", newValue = (pokemon.spriteUrl ?: ""))
                        .replace(
                            oldValue = "{dominantColor}",
                            newValue = (dominantColor.value
                                .toArgb()
                                .toString())
                        )
                )
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            PokemonSpriteLoader(
                modifier = Modifier.weight(1f),
                pokemonData = pokemon,
                onLoadSuccess = {
                    dominantColor.value = Color(
                        viewModel.getDominantColor(
                            drawable = it,
                            defaultColorInt = defaultColor.toArgb(),
                        ),
                    )
                },
            )
            Text(text = name ?: "")
        }
    }
}