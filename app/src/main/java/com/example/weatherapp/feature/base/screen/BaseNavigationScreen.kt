package com.example.weatherapp.feature.base.screen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.R
import com.example.weatherapp.baseui.widget.DimTintedIcon

sealed class Screen(
    val route: String,
    @DrawableRes val res: Int,
) {
    object Home : Screen(route = "home", res = R.drawable.home)
    object Favourites : Screen(route = "favourites", res = R.drawable.list)
    object About : Screen(route = "About", res = R.drawable.info)

    companion object {
        val bottomNavigationList = listOf(Home, Favourites, About)
    }
}

@Composable
fun BaseNavigationScreen(
    HomeScreen: @Composable () -> Unit,
    FavouriteScreen: @Composable () -> Unit,
    AboutScreen: @Composable () -> Unit,
) {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomNavigation(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .padding(
                        bottom = 8.dp
                    )
                    .background(
                        color = colorResource(id = R.color.grey_background)
                    )
            ) {
                val navBackStackEntry = navController.currentBackStackEntryAsState()
                val currentDestination = navController.currentDestination
                Screen.bottomNavigationList.forEach { screen ->
                    BottomNavigationItem(
                        icon = {
                            DimTintedIcon(
                                modifier = Modifier.size(25.dp),
                                drawableRes = screen.res,
                                contentDescription = screen.route
                            )
                        },
                        selected = currentDestination?.hierarchy?.any {
                            it.route == screen.route
                        } == true,
                        onClick = {
                            navController.navigate(screen.route) {

                                popUpTo(
                                    navController.graph.findStartDestination().id
                                ) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(navController,
            startDestination = Screen.Home.route,
            Modifier.padding(innerPadding)) {
            composable(route = Screen.Home.route) {
                HomeScreen()
            }
            composable(route = Screen.Favourites.route) {
                FavouriteScreen()
            }
            composable(route = Screen.About.route) {
                AboutScreen()
            }
        }
    }
}