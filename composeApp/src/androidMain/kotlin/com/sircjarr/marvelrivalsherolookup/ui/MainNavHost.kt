package com.sircjarr.marvelrivalsherolookup.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sircjarr.marvelrivalsherolookup.HeroesListScreen
import com.sircjarr.marvelrivalsherolookup.core.ui.screens.Screen
import com.sircjarr.marvelrivalsherolookup.ui.screens.heroeslist.HeroesListAndroidViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.HEROES_LIST.route) {
        composable(route = Screen.HEROES_LIST.route) {
            val viewModel = koinViewModel<HeroesListAndroidViewModel>().viewModel
            val viewState = viewModel.viewState.collectAsState().value

            LaunchedEffect(true) {
                viewModel.init()
            }

            HeroesListScreen(viewState)
        }
        composable(route = Screen.HERO_DETAILS.route) {

        }
    }
}