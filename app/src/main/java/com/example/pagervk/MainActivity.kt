package com.example.pagervk

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.pagervk.ui.theme.PagerVKTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PagerVKTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                        MainScreen()
                }
            }
        }
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen() {

    val images = remember {
        mutableStateListOf(
            "https://www.greenqueen.com.hk/wp-content/uploads/2020/12/Veganic-Farming.png",
            "https://sustainableagriculture.net/wp-content/uploads/2022/01/sweet-spot2-700x525.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/8/81/Woman_at_work%2C_Gujarat.jpg"

        )
    }

    val pagerState = rememberPagerState()

    Scaffold(modifier = Modifier.fillMaxSize()) {
        
        HorizontalPager(
            pageCount = images.size,
            state = pagerState
        ) { index ->

            val pageOffSet = (pagerState.currentPage - index) + pagerState.currentPageOffsetFraction
            val color = if (pagerState.currentPage == index) Color.DarkGray else Color.LightGray
            val imageSize by animateFloatAsState(
                targetValue = if(pageOffSet != 0.0f) 0.75f else 1f ,
                animationSpec = tween(durationMillis = 300)
            )
            AsyncImage(modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(5.dp))
                .graphicsLayer {
                               scaleX = imageSize
                               scaleY = imageSize
                }
                ,
                model = ImageRequest.Builder(LocalContext.current) .data(images[index]).build(),
                contentDescription = "images" ,
                contentScale = ContentScale.Crop
            )


        }
    }
}

