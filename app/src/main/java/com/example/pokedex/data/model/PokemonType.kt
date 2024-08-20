package com.example.pokedex.data.model

import androidx.compose.ui.graphics.Color
import com.example.pokedex.ui.theme.TypeBug
import com.example.pokedex.ui.theme.TypeDark
import com.example.pokedex.ui.theme.TypeDragon
import com.example.pokedex.ui.theme.TypeElectric
import com.example.pokedex.ui.theme.TypeFairy
import com.example.pokedex.ui.theme.TypeFighting
import com.example.pokedex.ui.theme.TypeFire
import com.example.pokedex.ui.theme.TypeFlying
import com.example.pokedex.ui.theme.TypeGhost
import com.example.pokedex.ui.theme.TypeGrass
import com.example.pokedex.ui.theme.TypeGround
import com.example.pokedex.ui.theme.TypeIce
import com.example.pokedex.ui.theme.TypeNormal
import com.example.pokedex.ui.theme.TypePoison
import com.example.pokedex.ui.theme.TypePsychic
import com.example.pokedex.ui.theme.TypeRock
import com.example.pokedex.ui.theme.TypeSteel
import com.example.pokedex.ui.theme.TypeWater
import com.google.gson.annotations.SerializedName

data class PokemonType(
    val name: TypeEnum? = null,
)

enum class TypeEnum {
    @SerializedName("normal")
    Normal,
    @SerializedName("fire")
    Fire,
    @SerializedName("water")
    Water,
    @SerializedName("electric")
    Electric,
    @SerializedName("grass")
    Grass,
    @SerializedName("ice")
    Ice,
    @SerializedName("fighting")
    Fighting,
    @SerializedName("poison")
    Poison,
    @SerializedName("ground")
    Ground,
    @SerializedName("flying")
    Flying,
    @SerializedName("psychic")
    Psychic,
    @SerializedName("bug")
    Bug,
    @SerializedName("rock")
    Rock,
    @SerializedName("ghost")
    Ghost,
    @SerializedName("dragon")
    Dragon,
    @SerializedName("dark")
    Dark,
    @SerializedName("steel")
    Steel,
    @SerializedName("fairy")
    Fairy,
}

fun TypeEnum.getTypeColor(): Color {
    return when (this) {
        TypeEnum.Normal -> TypeNormal
        TypeEnum.Fire -> TypeFire
        TypeEnum.Water -> TypeWater
        TypeEnum.Electric -> TypeElectric
        TypeEnum.Grass -> TypeGrass
        TypeEnum.Ice -> TypeIce
        TypeEnum.Fighting -> TypeFighting
        TypeEnum.Poison -> TypePoison
        TypeEnum.Ground -> TypeGround
        TypeEnum.Flying -> TypeFlying
        TypeEnum.Psychic -> TypePsychic
        TypeEnum.Bug -> TypeBug
        TypeEnum.Rock -> TypeRock
        TypeEnum.Ghost -> TypeGhost
        TypeEnum.Dragon -> TypeDragon
        TypeEnum.Dark -> TypeDark
        TypeEnum.Steel -> TypeSteel
        TypeEnum.Fairy -> TypeFairy
    }
}