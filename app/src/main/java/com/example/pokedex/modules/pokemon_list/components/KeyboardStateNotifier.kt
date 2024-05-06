package com.example.pokedex.modules.pokemon_list.components

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.ime
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.platform.LocalDensity

@Composable
private fun keyboardStateNotifier(): State<Boolean> {
    val isKeyboardVisible =  WindowInsets.ime.getBottom(LocalDensity.current) > 0
    return rememberUpdatedState(newValue = isKeyboardVisible)
}