package com.example.musical.ui.theme

import androidx.annotation.DrawableRes
import com.example.musical.R

sealed class Screen(val title: String, val route: String) {

    sealed class BottomScreen(
        val bTitle: String, val bRoute: String, @DrawableRes val icon: Int
    ):Screen(bTitle,bRoute){
        object Home : BottomScreen("Home", "home", R.drawable.baseline_medical_services_24)

        object Library : BottomScreen(
            "Help", "library", R.drawable.baseline_local_hospital_24
        )
        object Browse: BottomScreen(
            "Reports", "browse",
            R.drawable.baseline_menu_book_24
        )
    }



    sealed class  DrawerScreen(val dTitle: String, val dRoute: String, @DrawableRes val icon: Int)
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
    Screen.BottomScreen.Browse,
    Screen.BottomScreen.Library
)

val screensInDrawer = listOf(
    Screen.DrawerScreen.Account,
    Screen.DrawerScreen.Subscription,
    Screen.DrawerScreen.AddAccount
)