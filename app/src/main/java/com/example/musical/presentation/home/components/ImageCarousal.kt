package com.example.musical.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.musical.R
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ImageCarousel() {
    val pagerState = rememberPagerState()
    val images = listOf(
        Pair(R.drawable.img1, "Hospital 1 - New York"),
        Pair(R.drawable.img2, "Hospital 2 - Los Angeles"),
        Pair(R.drawable.img3, "Hospital 3 - Chicago"),
        Pair(R.drawable.img4, "Hospital 4 - Houston")
    )
    LaunchedEffect(pagerState) {
        while (true) {
            yield()
            delay(3000) // 3 seconds delay
            pagerState.animateScrollToPage((pagerState.currentPage + 1) % images.size)
        }
    }
    HorizontalPager(
        count = images.size,
        state = pagerState,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    ) { page ->
        Card(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
                .clickable { /* Handle click action */ },
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            ),
        ) {
            Box {
                Image(
                    painter = painterResource(id = images[page].first),
                    contentDescription = "Image ${page + 1}",
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = images[page].second,
                    color = Color.White,
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .background(Color.Black.copy(alpha = 0.5f))
                        .padding(8.dp)
                )
            }
        }
    }
}
