package com.example.weatherapp.feature.base.screen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import com.example.weatherapp.baseui.theme.GreyBackground
import com.example.weatherapp.baseui.widget.DimText
import com.example.weatherapp.baseui.widget.DimTintedIcon

sealed class Screen(
    val route: String,
    @DrawableRes val res: Int,
) {
    object Home : Screen(route = "Home", res = R.drawable.home)
    object Favourites : Screen(route = "Favourites", res = R.drawable.heart)
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
        modifier = Modifier
            .fillMaxSize(),
        bottomBar = {
            BottomNavigation(
                modifier = Modifier
                    .fillMaxWidth(),
                backgroundColor = colorResource(id = R.color.grey_background),
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                Screen.bottomNavigationList.forEach { screen ->
                    BottomNavigationItem(
                        modifier = Modifier.background(GreyBackground),
                        icon = {
                            DimTintedIcon(
                                modifier = Modifier
                                    .size(25.dp)
                                    .padding(bottom = 4.dp),
                                drawableRes = screen.res,
                                contentDescription = screen.route
                            )
                        },
                        label = {
                            DimText(text = screen.route)
                        },
                        alwaysShowLabel = false,
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