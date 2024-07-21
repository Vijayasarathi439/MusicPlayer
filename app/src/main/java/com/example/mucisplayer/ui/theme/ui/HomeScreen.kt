package com.example.mucisplayer.ui.theme.ui

import android.widget.GridView
import android.widget.ScrollView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mucisplayer.ui.theme.components.TopBannerCarousel
import com.example.mucisplayer.ui.theme.data.MockData
import com.example.mucisplayer.ui.theme.data.VideoModel
import com.seiko.imageloader.model.ImageRequest
import com.seiko.imageloader.rememberImageAction
import com.seiko.imageloader.rememberImageActionPainter
import network.chaintech.sdpcomposemultiplatform.sdp


val roundedCornerShape = RoundedCornerShape(7.dp)
val hotData = MockData().hotRightNow()
val tamilMovies = MockData().tamilMovies()
val cartonMovies = MockData().popularInKids()
val sports = MockData().sports()

@Composable
fun HomeTopScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 11.sdp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
    ) {
        val items = MockData().topBannerData()
        TopBannerCarousel(pageCount = items.size, content = { index ->
            val item = items[index]
            TopBannerView(item = item, onClickEvent = {
            })
        })
    }
}

@Composable
fun HomeView(navController: NavController, paddingValues: PaddingValues) {
    val scrollState = rememberScrollState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(paddingValues)
            .scrollable(scrollState, orientation = Orientation.Vertical)
    ) {
        item {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Black)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(top = 10.dp)
                            .fillMaxSize()
                    ) {
                        Text(
                            text = "Movie Trailer",
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            ),
                            modifier = Modifier.padding(top = 10.dp, start = 10.dp)
                        )
                        HomeTopScreen()
                        Text(
                            text = "Hot Movies",
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            ),
                            modifier = Modifier.padding(top = 20.dp, start = 10.dp)
                        )
                        LazyRow(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(start = 10.dp, top = 10.dp),
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            items(hotData.chunked(1)) { rowItems ->
                                Column {
                                    for (movieUrl in rowItems) {
                                        HotMovies(movieUrl)
                                    }
                                }
                            }
                        }

                        Text(
                            text = "Tamil Movies",
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                fontSize = 18.sp
                            ),
                            modifier = Modifier.padding(top = 20.dp, start = 10.dp)
                        )
                        LazyRow(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(start = 10.dp, top = 10.dp),
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            items(tamilMovies.chunked(1)) { rowItems ->
                                Column {
                                    for (movieUrl in rowItems) {
                                        TamilMovies(movieUrl)
                                    }
                                }
                            }
                        }
                        Text(
                            text = "Carton Movies",
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                fontSize = 18.sp
                            ),
                            modifier = Modifier.padding(top = 20.dp, start = 10.dp)
                        )
                        LazyRow(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(start = 10.dp, top = 10.dp),
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        )
                        {
                            items(cartonMovies.chunked(1)) { rowIteams ->
                                for (carton in rowIteams) {
                                    CartonMovies(carton)
                                }
                            }
                        }
                        Text(
                            text = "Sports ",
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                fontSize = 18.sp
                            ),
                            modifier = Modifier.padding(top = 20.dp, start = 10.dp)
                        )
                        LazyRow(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(start = 10.dp, top = 10.dp, bottom = 30.dp),
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        )
                        {
                            items(sports.chunked(1)) { iteams ->
                                for (data in iteams) {
                                    Sports(data)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Sports(sports: String) {
    Box(
        modifier =
        Modifier
            .width(160.dp)
            .height(180.dp)
            .background(color = Color.White, shape = roundedCornerShape)
            .border(shape = roundedCornerShape, color = Color.Gray, width = 1.dp)
    )
    {
        FromRemote(
            painterResource = sports,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Transparent, shape = roundedCornerShape)
                .clip(roundedCornerShape)
        )
    }
}

@Composable
fun CartonMovies(movies: String) {
    Box(
        modifier =
        Modifier
            .width(160.dp)
            .height(180.dp)
            .background(color = Color.White, shape = roundedCornerShape)
            .border(shape = roundedCornerShape, color = Color.Gray, width = 1.dp)
    )
    {
        FromRemote(
            painterResource = movies,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Transparent, shape = roundedCornerShape)
                .clip(roundedCornerShape)
        )
    }
}

@Composable
fun TamilMovies(movies: String) {

    Box(
        modifier =
        Modifier
            .width(160.dp)
            .height(180.dp)
            .background(color = Color.White, shape = roundedCornerShape)
            .border(shape = roundedCornerShape, color = Color.Gray, width = 1.dp)
    )
    {
        FromRemote(
            painterResource = movies,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Transparent, shape = roundedCornerShape)
                .clip(roundedCornerShape)
        )
    }
}

@Composable
fun TopBannerView(item: VideoModel, onClickEvent: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(450.dp)
            .background(
                color = Color.Transparent,
                shape = roundedCornerShape
            )
            .border(
                width = 1.dp,
                color = Color.Gray,
                shape = roundedCornerShape
            )
            .clip(RoundedCornerShape(14.dp))
            .pointerInput(Unit) {
                detectTapGestures { _ ->
                    onClickEvent()
                }
            }
    ) {
        FromRemote(
            painterResource = item.thumb,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Transparent, shape = roundedCornerShape)
                .clip(roundedCornerShape)
        )
    }

}

@Composable
fun FromRemote(painterResource: String, contentScale: ContentScale, modifier: Modifier) {
    Image(
        data = painterResource,
        modifier = modifier,
        contentScale = contentScale
    )
}

@Composable
fun Image(
    modifier: Modifier,
    data: Any,
    contentScale: ContentScale = ContentScale.Crop,
) {
    Box(modifier, Alignment.Center) {
        val request = remember(data, 0, true) {
            ImageRequest {
                data(data)
//                addInterceptor(NullDataInterceptor)
                options {
                    playAnimate = true
                }
            }
        }
        val action by rememberImageAction(request)
        val painter = rememberImageActionPainter(action)
        Image(
            painter = painter,
            contentDescription = null,
            contentScale = contentScale,
            modifier = Modifier.fillMaxSize(),
        )
    }
}

@Composable
fun HotMovies(movies: String) {
    Box(
        modifier =
        Modifier
            .width(160.dp)
            .height(180.dp)
            .background(color = Color.White, shape = roundedCornerShape)
            .border(shape = roundedCornerShape, color = Color.Gray, width = 1.dp)
    )
    {
        FromRemote(
            painterResource = movies,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Transparent, shape = roundedCornerShape)
                .clip(roundedCornerShape)
        )
    }

}