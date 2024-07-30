package com.example.musical.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF2196F3), // Blue
    primaryContainer = Color(0xFF0D47A1), // Dark Blue
    secondary = Color(0xFF4CAF50), // Green
    secondaryContainer = Color(0xFF388E3C), // Dark Green
    background = Color(0xFF121212),
    surface = Color(0xFF121212),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White,
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF2196F3), // Blue
    primaryContainer = Color(0xFFBBDEFB), // Light Blue
    secondary = Color(0xFF4CAF50), // Green
    secondaryContainer = Color(0xFFC8E6C9), // Light Green
    background = Color(0xFFF1F8E9), // Light background
    surface = Color(0xFFFFFFFF), // White surface
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color.Black,
    onSurface = Color.Black,
)

@Composable
fun MusicalTheme(
    darkTheme: Boolean = false, // Choose your theme dynamically if needed
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}
