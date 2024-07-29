package com.example.musical.presentation.home.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.musical.R
import com.example.musical.presentation.home.Appointment
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield


@OptIn(ExperimentalPagerApi::class)
@Composable
fun AppointmentsCarousel(navController: NavController) {
    val pagerState = rememberPagerState()
    val appointments = listOf(
        Appointment("Dr. Daksh Dhaka", "Heart Surgeon", R.drawable.your_image1),
        Appointment("Dr. Jane Smith", "Pediatrician", R.drawable.your_image2),
        Appointment("Dr. John Doe", "Dermatologist", R.drawable.your_image3),
        Appointment("Dr. Alice Brown", "Neurologist", R.drawable.your_image4)
    )

    LaunchedEffect(pagerState) {
        while (true) {
            yield()
            delay(3000) // 3 seconds delay
            pagerState.animateScrollToPage((pagerState.currentPage + 1) % appointments.size)
        }
    }

    HorizontalPager(
        count = appointments.size,
        state = pagerState,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    ) { page ->
        com.example.musical.presentation.home.components.AppointmentCard(
            navController = navController,
            appointment = appointments[page]
        )
    }
}





