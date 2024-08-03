package com.example.musical.navgraph

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.musical.MainViewModel
import com.example.musical.common.utils.*
import com.example.musical.presentation.accounts.AccountView
import com.example.musical.presentation.accounts.LogOut
import com.example.musical.presentation.chat.ChatHomeScreen
import com.example.musical.presentation.chat.ChatScreen
import com.example.musical.presentation.chat.components.MedChatHomeScreen
import com.example.musical.presentation.chat.components.MedChatScreen
import com.example.musical.presentation.help.Help
import com.example.musical.presentation.home.PatientHomeScreen
import com.example.musical.presentation.home.DoctorHomeScreen
import com.example.musical.ui.theme.*


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation(navController: NavController, viewModel: MainViewModel, pd: PaddingValues) {
    NavHost(
        navController = navController as NavHostController,
        startDestination = "onboarding",
        modifier = Modifier.padding(pd)
    ) {
        composable("onboarding") {
            OnboardingScreen(navController)
        }
        composable("loginSignup") {
            LoginSignupScreen(navController)
        }
        composable("doctorLogin") {
            DoctorLoginScreen(navController)
        }
        composable("patientLogin") {
            PatientLoginScreen(navController)
        }
        composable("signup") {
            SignupScreen(navController)
        }
        composable("doctorSignup") {
            DoctorSignupScreen(navController)
        }
        composable("patientSignup") {
            PatientSignupScreen(navController)
        }
        composable("patientHome") {
            PatientHomeScreen(navController, pd)
        }
        composable("doctorHome") {
            DoctorHomeScreen(navController, pd)
        }
        composable(Screen.BottomScreen.Home.bRoute) {
            PatientHomeScreen(navController, pd)
        }
        composable(Screen.BottomScreen.Reports.bRoute) {
            Report()
        }
        composable(Screen.BottomScreen.Help.bRoute) {
            Help()
        }
        composable(Screen.BottomScreen.Fitness.bRoute) {
            FitnessScreen(navController)
        }
        composable(Screen.BottomScreen.Referral.bRoute) {
            ReferralScreen(navController)
        }
        composable(Screen.BottomScreen.DoctorReports.bRoute) {
            DoctorReport(navController)
        }
        composable(Screen.BottomScreen.DoctorHomeScreen.bRoute) {
            DoctorHomeScreen(navController, pd)
        }
        composable(Screen.DrawerScreen.Account.route) {
            AccountView()
        }
        composable(Screen.DrawerScreen.Subscription.route) {
            LogOut()
        }
        composable("chatRoute") {
            ChatHomeScreen(navController = navController)
        }
        composable("medChatRoute") {
            MedChatHomeScreen(navController = navController)
        }
        composable("doctor"){
            DoctorReport(navController)
        }
        composable("patientReportForm"){
            PatientReportForm(navController)
        }
        composable(
            route = "chat_screen/{chatItemJson}",
            arguments = listOf(navArgument("chatItemJson") { type = NavType.StringType })
        ) { backStackEntry ->
            val chatItemJson = backStackEntry.arguments?.getString("chatItemJson") ?: ""
            ChatScreen(navController = navController, chatItemJson = chatItemJson)
        }
        composable(
            route = "medChatScreen/{chatId}",
            arguments = listOf(navArgument("chatId") { type = NavType.IntType })
        ) { backStackEntry ->
            val chatId = backStackEntry.arguments?.getInt("chatId") ?: 0
            MedChatScreen(navController = navController, chatId = chatId)
        }
        composable("patientDiagnoseScreen") {
            PatientDiagnoseScreen(navController)
        }
        composable("sessionUpdate") {
            SessionUpdateScreen(navController)
        }
    }
}
