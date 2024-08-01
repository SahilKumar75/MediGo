package com.example.musical.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.musical.R
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingScreen(navController: NavController) {
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        HorizontalPager(
            count = 3,
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->
            OnboardingPage(page)
        }

        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier.padding(16.dp)
        )

        Button(onClick = {
            scope.launch {
                if (pagerState.currentPage < 2) {
                    pagerState.scrollToPage(pagerState.currentPage + 1)
                } else {
                    navController.navigate("loginSignup")
                }
            }
        }) {
            Text(text = if (pagerState.currentPage < 2) "Next" else "Get Started")
        }
    }
}

@Composable
fun OnboardingPage(page: Int) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when (page) {
            0 -> {
                Image(painter = painterResource(id = R.drawable.feature1), contentDescription = null)
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Feature 1", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Text(text = "Description of feature 1", fontSize = 16.sp, color = Color.Gray)
            }
            1 -> {
                Image(painter = painterResource(id = R.drawable.feature2), contentDescription = null)
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Feature 2", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Text(text = "Description of feature 2", fontSize = 16.sp, color = Color.Gray)
            }
            2 -> {
                Image(painter = painterResource(id = R.drawable.feature3), contentDescription = null)
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Feature 3", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Text(text = "Description of feature 3", fontSize = 16.sp, color = Color.Gray)
            }
        }
    }
}
