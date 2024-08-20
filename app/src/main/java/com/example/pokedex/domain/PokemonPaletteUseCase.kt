package com.example.pokedex.domain

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.palette.graphics.Palette
import javax.inject.Inject


class PokemonPaletteUseCase @Inject constructor() {

    fun getPaletteFromSpriteDrawable(drawable: Drawable): Palette {
        val bitmap = (drawable as BitmapDrawable).bitmap.copy(
            /* config = */ Bitmap.Config.ARGB_8888,
            /* isMutable = */ false
        )
        return Palette.from(bitmap).generate()
    }
}