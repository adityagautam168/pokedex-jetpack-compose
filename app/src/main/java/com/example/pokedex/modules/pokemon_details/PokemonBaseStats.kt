package com.example.pokedex.modules.pokemon_details

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokedex.data.model.PokemonData
import com.example.pokedex.data.model.getStatColor

@Composable
fun PokemonBaseStats(
    modifier: Modifier = Modifier,
    pokemonData: PokemonData,
) {
    val maxStatValue = remember {
        pokemonData.stats?.fold(0) { max, element ->
            if ((element.baseStat ?: 0) > max) {
                element.baseStat ?: 0
            } else {
                max
            }
        }
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(
            text = "Base stats:",
            style = TextStyle(
                color = Color.Black,
                fontSize = 20.sp,
            )
        )
        pokemonData.stats?.forEach {
            it.stat?.let { stat ->
                PokemonBaseStat(
                    attributeName = stat.type?.displayName ?: "",
                    attributeValue = it.baseStat ?: 0,
                    attributeMaxValue = maxStatValue ?: 0,
                    attributeColor = stat.type?.getStatColor() ?: Color.Black,
                )
            }
        }
    }
}

@Composable
fun PokemonBaseStat(
    modifier: Modifier = Modifier,
    attributeName: String,
    attributeValue: Int,
    attributeMaxValue: Int,
    attributeColor: Color,
) {
    var progress by remember {
        mutableFloatStateOf(0F)
    }
    val progressAnimation by animateFloatAsState(
        label = attributeName,
        targetValue = progress,
        animationSpec = tween(
            durationMillis = 500,
            easing = FastOutSlowInEasing,
        ),
    )

    Box(
        contentAlignment = Alignment.CenterStart,
    ) {
        LinearProgressIndicator(
            progress = { progressAnimation },
            modifier = modifier
                .fillMaxWidth()
                .height(24.dp)
                .clip(RoundedCornerShape(24.dp)),
            color = attributeColor,
        )
        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = attributeName,
            style = TextStyle(
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
            )
        )
    }

    LaunchedEffect(LocalLifecycleOwner.current) {
        progress = attributeValue.toFloat() / attributeMaxValue
    }
}