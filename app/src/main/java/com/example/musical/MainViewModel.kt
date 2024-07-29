package com.example.musical

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.musical.navgraph.Screen

class MainViewModel : ViewModel() {

    private val _currentScreen: MutableState<Screen> = mutableStateOf(Screen.BottomScreen.Home) // Set to initial screen
    val currentScreen: MutableState<Screen>
        get() = _currentScreen

    private val _username: MutableState<String> = mutableStateOf("User")
    val username: MutableState<String>
        get() = _username

    fun setCurrentScreen(screen: Screen) {
        _currentScreen.value = screen
    }
}
