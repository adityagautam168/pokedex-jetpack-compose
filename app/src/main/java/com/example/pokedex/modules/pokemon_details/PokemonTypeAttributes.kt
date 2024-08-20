package com.example.pokedex.modules.pokemon_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokedex.data.model.PokemonData
import com.example.pokedex.data.model.TypeEnum
import com.example.pokedex.data.model.getTypeColor

@Composable
fun PokemonTypeAttributes(
    modifier: Modifier = Modifier,
    pokemonData: PokemonData
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        val typeList = pokemonData.types?.map {
            it.type?.name
        } ?: emptyList()
        for (i in typeList.indices) {
            typeList[i]?.let {
                PokemonTypeChip(
                    modifier = Modifier.weight(1f),
                    type = it,
                )
            }
            if (i != (pokemonData.types?.size ?: 0) - 1) {
                Spacer(modifier = Modifier.width(16.dp))
            }
        }
    }
}

@Composable
fun PokemonTypeChip(
    modifier: Modifier = Modifier,
    type: TypeEnum
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(32.dp))
            .background(type.getTypeColor())
            .padding(vertical = 8.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = type.name,
            style = TextStyle(
                color = Color.White,
                fontSize = 24.sp,
            )
        )
    }
}