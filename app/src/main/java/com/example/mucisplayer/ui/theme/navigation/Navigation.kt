package com.example.mucisplayer.ui.theme.navigation

import androidx.compose.runtime.compositionLocalOf
import com.example.mucisplayer.ui.theme.data.VideoModel

interface Navigation {
    fun back(): Boolean
    fun goRoot(): Boolean
    fun videoPlayerScreen(videoModel: VideoModel): Boolean
}

val LocalNavigation = compositionLocalOf { error("Navigation error") }