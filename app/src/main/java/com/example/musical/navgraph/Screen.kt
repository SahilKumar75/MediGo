package com.example.musical.navgraph

import androidx.annotation.DrawableRes
import com.example.musical.R
import com.google.rpc.Help

sealed class Screen(val title: String, val route: String) {

    sealed class BottomScreen(
        val bTitle: String, val bRoute: String, @DrawableRes val icon: Int
    ): Screen(bTitle,bRoute){
        object Home : BottomScreen("Home", "home", R.drawable.baseline_medical_services_24)

        object Fitness : BottomScreen(
            "Fitness", "fitness",
            R.drawable.baseline_fitness_center_24 // Use the appropriate icon resource
        )

        object Help : BottomScreen(
            "Help", "Help", R.drawable.baseline_local_hospital_24
        )

        object Reports: BottomScreen(
            "Reports", "Reports",
            R.drawable.baseline_menu_book_24
        )
    }

    sealed class DrawerScreen(val dTitle: String, val dRoute: String, @DrawableRes val icon: Int)
        : Screen(dTitle, dRoute){
        object Account: DrawerScreen(
            "Account",
            "account",
            R.drawable.ic_account
        )
        object Subscription: DrawerScreen(
            "Log Out",
            "subscribe",
            R.drawable.baseline_logout_24
        )

        object AddAccount: DrawerScreen(
            "Add Account",
            "add_account",
            R.drawable.baseline_person_add_alt_1_24
        )
    }
}

val screensInBottom = listOf(
    Screen.BottomScreen.Home,
    Screen.BottomScreen.Fitness, // Added the Fitness screen here
    Screen.BottomScreen.Reports,
    Screen.BottomScreen.Help
)

val screensInDrawer = listOf(
    Screen.DrawerScreen.Account,
    Screen.DrawerScreen.Subscription,
    Screen.DrawerScreen.AddAccount
)
