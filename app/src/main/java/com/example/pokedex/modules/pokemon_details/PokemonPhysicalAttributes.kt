package com.example.pokedex.modules.pokemon_details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Height
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.pokedex.R
import com.example.pokedex.data.model.PokemonData

@Composable
fun PokemonPhysicalAttributes(
    modifier: Modifier = Modifier,
    pokemonData: PokemonData
) {
    val weightInKg = remember { pokemonData.weightInHg?.div(10f) }
    val heightInM = remember { pokemonData.heightInDm?.div(10f) }

    Row(
        modifier = modifier.height(IntrinsicSize.Min)
    ) {
        PokemonPhysicalAttribute(
            modifier = Modifier.weight(1f),
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_weight),
            contentDescription = "Weight",
            attributeValue = weightInKg.toString(),
            attributeUnits = "kg",
        )
        VerticalDivider()
        PokemonPhysicalAttribute(
            modifier = Modifier.weight(1f),
            imageVector = Icons.Filled.Height,
            contentDescription = "Height",
            attributeValue = heightInM.toString(),
            attributeUnits = "m",
        )
    }
}

@Composable
fun PokemonPhysicalAttribute(
    modifier: Modifier = Modifier,
    contentDescription: String?,
    imageVector: ImageVector,
    attributeValue: String,
    attributeUnits: String,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            modifier = Modifier.size(40.dp),
            imageVector = imageVector,
            contentDescription = contentDescription,
        )
        Text(text = "$attributeValue $attributeUnits")
    }
}