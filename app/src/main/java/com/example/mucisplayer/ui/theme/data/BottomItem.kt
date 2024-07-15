package com.example.mucisplayer.ui.theme.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val icon: ImageVector, val label: String) {
    object Home : BottomNavItem("home", Icons.Default.Home, "Home")
    object Search : BottomNavItem("search", Icons.Default.PlayArrow, "Search")
    object Profile : BottomNavItem("profile", Icons.Default.AccountCircle, "Profile")

    companion object {
        fun getAllIteams(): List<BottomNavItem> = listOf(Home, Search, Profile)
    }

}