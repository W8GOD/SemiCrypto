package com.discover.simple.semicrypto.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = primaryMidnightBlue,
    secondary = textColorDark,
    surface = surfaceGrey,
    primaryVariant = primaryBlack,
    onPrimary = accentAmber,
    onSurface = accentAmber,
)

private val LightColorPalette = lightColors(
    primary = primarySupernova,
    secondary = textColorLight,
    surface = primaryWhite,
    primaryVariant = primaryBlack,
    onPrimary = accentAmber,
    onSurface = accentAmber,
)

@Composable
fun SemiCryptoTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = Shapes,
        content = content
    )
}