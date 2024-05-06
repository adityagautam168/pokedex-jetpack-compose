package com.example.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pokedex.modules.pokemon_details.PokemonDetailsScreen
import com.example.pokedex.modules.pokemon_list.presentation.PokemonListScreen
import com.example.pokedex.ui.theme.PokedexTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokedexTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "pokemon_list_screen") {
                    composable("pokemon_list_screen") {
                        PokemonListScreen(navController = navController)
                    }

                    composable(
                        route = "pokemon_details_screen?id={id}&spriteUrl={spriteUrl}&dominantColor={dominantColor}",
                        arguments = listOf(
                            navArgument("id") {
                                defaultValue = 0
                                type = NavType.IntType
                            },
                            navArgument("spriteUrl") {
                                defaultValue = ""
                                type = NavType.StringType
                            },
                            navArgument("dominantColor") {
                                defaultValue = 0
                                type = NavType.IntType
                            },
                        )
                    ) {
                        val pokemonId = it.arguments?.getInt("id") ?: 0
                        val spriteUrl = it.arguments?.getString("spriteUrl") ?: ""
                        val dominantColorInt = it.arguments?.getInt("dominantColor") ?: 0

                        PokemonDetailsScreen(
                            pokemonId = pokemonId,
                            spriteUrl = spriteUrl,
                            dominantColorInt = dominantColorInt,
                            navController = navController,
                        )
                    }
                }
            }
        }
    }
}

//@Preview
//@Composable
//fun Preview() {
//    return PokemonListScreen()
//}
