package com.example.pokedex.modules.pokemon_list.components

import android.graphics.drawable.Drawable
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.SubcomposeAsyncImage
import com.example.pokedex.data.model.PokemonData
import java.util.Locale


@Composable
fun PokemonSpriteLoader(
    modifier: Modifier = Modifier,
    spriteUrl: String,
    contentDescription: String? = null,
    onLoadSuccess: ((Drawable) -> Unit)? = null,
) {
    SubcomposeAsyncImage(
        modifier = modifier,
        model = spriteUrl,
        contentDescription = contentDescription,
        loading = {
            CircularProgressIndicator()
        },
        onSuccess = {
            onLoadSuccess?.invoke(it.result.drawable)
        },
    )
}

@Composable
fun PokemonSpriteLoader(
    modifier: Modifier = Modifier,
    pokemonData: PokemonData,
    onLoadSuccess: ((Drawable) -> Unit)? = null,
) {
    val name = pokemonData.name?.replaceFirstChar {
        if (it.isLowerCase()) {
            it.titlecase(Locale.getDefault())
        } else {
            it.toString()
        }
    }

    PokemonSpriteLoader(
        modifier = modifier,
        spriteUrl = pokemonData.spriteUrl ?: "",
        contentDescription = name,
        onLoadSuccess = onLoadSuccess,
    )
}