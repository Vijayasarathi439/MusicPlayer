package com.example.mucisplayer

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mucisplayer.ui.theme.MucisPlayerTheme
import com.example.mucisplayer.ui.theme.data.BottomNavItem
import com.example.mucisplayer.ui.theme.ui.HomeView
import com.example.mucisplayer.ui.theme.ui.MusicPlayerScreen
import com.example.mucisplayer.ui.theme.ui.StoriesScreen

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MucisPlayerTheme {
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = { BottomNavigationBar(navController) }
                ) { innerPadding ->
                    NavHost(navController = navController, startDestination = "home") {
                        composable("home") { HomeView(navController,innerPadding) }
                        composable("search") { MusicPlayerScreen() }
                        composable("profile") { StoriesScreen() }
                    }
                }

            }
        }
    }


    @Composable
    fun BottomNavigationBar(navController: NavController) {
        BottomNavigation {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            BottomNavItem.getAllIteams().forEach { item ->
                BottomNavigationItem(
                    selected = currentRoute == item.route,
                    modifier = Modifier.background(Color.Gray),
                    onClick = {
                        navController.navigate(item.route)
                    },
                    icon = {
                        Icon(imageVector = item.icon, contentDescription = "")
                    })

            }
        }
    }
}