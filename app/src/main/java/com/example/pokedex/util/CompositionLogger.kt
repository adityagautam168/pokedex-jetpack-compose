package com.example.pokedex.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import timber.log.Timber

@Composable
inline fun CompositionLogger(tag: String, msg: String) {
//    if (BuildConfig) {
        val ref = remember { Ref(0) }
        SideEffect { ref.value++ }
        Timber.tag(tag).d("Compositions: $msg ${ref.value}")
//    }
}

class Ref(var value: Int)