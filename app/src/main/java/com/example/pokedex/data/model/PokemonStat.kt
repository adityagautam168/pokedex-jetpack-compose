package com.example.pokedex.data.model

import androidx.compose.ui.graphics.Color
import com.example.pokedex.ui.theme.AtkColor
import com.example.pokedex.ui.theme.DefColor
import com.example.pokedex.ui.theme.HPColor
import com.example.pokedex.ui.theme.SpAtkColor
import com.example.pokedex.ui.theme.SpDefColor
import com.example.pokedex.ui.theme.SpdColor
import com.google.gson.annotations.SerializedName

data class PokemonStat(
    @SerializedName("base_stat")
    val baseStat: Int? = null,
    val stat: Stat? = null,
)

data class Stat(
    @SerializedName("name")
    val type: StatType?
)

enum class StatType(val displayName: String) {
    @SerializedName("hp")
    HP("HP"),
    @SerializedName("attack")
    Attack("Attack"),
    @SerializedName("defense")
    Defense("Defense"),
    @SerializedName("special-attack")
    SpecialAttack("Special Attack"),
    @SerializedName("special-defense")
    SpecialDefense("Special Defense"),
    @SerializedName("speed")
    Speed("Speed"),
}

fun StatType.getStatColor(): Color {
    return when (this) {
        StatType.HP -> HPColor
        StatType.Attack -> AtkColor
        StatType.Defense -> DefColor
        StatType.SpecialAttack -> SpAtkColor
        StatType.SpecialDefense -> SpDefColor
        StatType.Speed -> SpdColor
    }
}