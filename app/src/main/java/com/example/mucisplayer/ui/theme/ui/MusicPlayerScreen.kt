package com.example.mucisplayer.ui.theme.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import chaintech.videoplayer.model.AudioPlayerConfig
import chaintech.videoplayer.model.PlayerConfig
import chaintech.videoplayer.ui.audio.AudioPlayerView
import chaintech.videoplayer.ui.video.VideoPlayerView
import com.example.mucisplayer.ui.theme.data.MockData
import network.chaintech.sdpcomposemultiplatform.sdp

@Composable
fun MusicPlayerScreen() {
    AudioPlayerView(
        modifier = Modifier.fillMaxSize(),
        audios = MockData().audioFilesArray,
    )
}