package com.example.pokedex.modules.pokemon_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.pokedex.data.model.PokemonData
import com.example.pokedex.modules.pokemon_list.components.PokemonSpriteLoader
import com.example.pokedex.viewmodel.PokedexViewModel
import java.util.Locale

@Composable
fun PokemonDetailsScreen(
    pokemonId: Int,
    spriteUrl: String,
    dominantColorInt: Int,
    navController: NavController,
) {
    val viewModel = hiltViewModel<PokedexViewModel>()
    val pokemonData by viewModel.selectedPokemonData.collectAsState()

    LaunchedEffect(
        key1 = pokemonId,
        block = {
            viewModel.fetchPokemonDataById(
                id = pokemonId,
                coroutineScope = this,
            )
        }
    )

    Surface(
        color = Color.Black,
    ) {
        val configuration = LocalConfiguration.current

        val spriteSize = remember {
            configuration.screenWidthDp.dp.times(0.6f)
        }

        val dominantColor = remember { Color(dominantColorInt) }
        val dominantColorStop = remember {
            (spriteSize + 16.dp).div(configuration.screenHeightDp).value
        }

        val scrollState = rememberScrollState()
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colorStops = arrayOf(
                            0f to Color.Black,
                            dominantColorStop to dominantColor,
                        )
                    )
                )
                .verticalScroll(state = scrollState),
            contentAlignment = Alignment.TopCenter,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.height(spriteSize.div(2)))
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.White)
                        .padding(horizontal = 32.dp, vertical = 24.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Spacer(modifier = Modifier.height(spriteSize.div(2)))
                        pokemonData?.let {
                            PokemonDetailsList(pokemonData = it)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(32.dp))
            }
            BackButton(
                modifier = Modifier.align(Alignment.TopStart),
                navController = navController
            )
            PokemonSpriteLoader(
                modifier = Modifier.size(spriteSize),
                spriteUrl = spriteUrl,
            )
        }
    }
}

@Composable
fun BackButton(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    IconButton(
        modifier = modifier,
        content = {
            Icon(
                modifier = Modifier.size(32.dp),
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back to home",
                tint = Color.White,
            )
        },
        onClick = {
            navController.popBackStack()
        }
    )
}

@Composable
fun PokemonDetailsList(
    modifier: Modifier = Modifier,
    pokemonData: PokemonData
) {
    val name = remember {
        pokemonData.name?.replaceFirstChar {
            if (it.isLowerCase()) {
                it.titlecase(Locale.getDefault())
            } else {
                it.toString()
            }
        }
    }

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(
            text = "#${pokemonData.id} $name",
            style = TextStyle(
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
            )
        )
        PokemonTypeAttributes(pokemonData = pokemonData)
        PokemonPhysicalAttributes(pokemonData = pokemonData)
        PokemonBaseStats(pokemonData = pokemonData)
    }
}