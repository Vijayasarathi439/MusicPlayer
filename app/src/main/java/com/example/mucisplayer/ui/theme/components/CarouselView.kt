package com.example.mucisplayer.ui.theme.components

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

const val CAROUSEL_AUTO_SCROLL_TIMER: Long = 3000L
const val ANIM_TIME_LONG: Int = 800


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TopBannerCarousel(
    pageCount: Int,
    autoScrollEnable: Boolean = true,
    autoScrollDuration: Long = CAROUSEL_AUTO_SCROLL_TIMER,
    content: @Composable ColumnScope.(index: Int) -> Unit,
) {
    val pagerState: PagerState = rememberPagerState(pageCount = { pageCount })

    val isDragged by pagerState.interactionSource.collectIsDraggedAsState()

    if (autoScrollEnable) {
        if (isDragged.not()) {
            with(pagerState) {
                if (pageCount > 0) {
                    var currentPageKey by remember { mutableIntStateOf(0) }
                    LaunchedEffect(key1 = currentPageKey) {
                        launch {
                            delay(timeMillis = autoScrollDuration)
                            val nextPage = (currentPage + 1).mod(pageCount)
                            animateScrollToPage(
                                page = nextPage,
                                animationSpec = tween(
                                    durationMillis = ANIM_TIME_LONG
                                )
                            )
                            currentPageKey = nextPage
                        }
                    }
                }
            }
        }
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box {
            var currentPageKey by remember { mutableIntStateOf(0) }
            HorizontalPager(
                state = pagerState,
                contentPadding = PaddingValues(
                    horizontal = 12.dp
                ),
                pageSpacing = 7.dp,
            ) { page: Int ->
                currentPageKey = page
                this@Column.content(page)
            }
        }

    }
}