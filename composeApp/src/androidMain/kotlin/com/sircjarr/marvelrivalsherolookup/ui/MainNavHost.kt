package com.sircjarr.marvelrivalsherolookup.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sircjarr.marvelrivalsherolookup.ui.screens.heroeslist.HeroesListScreen
import com.sircjarr.marvelrivalsherolookup.feature_api.ui.Screen
import com.sircjarr.marvelrivalsherolookup.ui.screens.herodetails.HeroDetailsAndroidViewModel
import com.sircjarr.marvelrivalsherolookup.ui.screens.herodetails.HeroDetailsScreen
import com.sircjarr.marvelrivalsherolookup.ui.screens.heroeslist.HeroesListAndroidViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.HEROES_LIST.route) {
        composable(
            route = Screen.HEROES_LIST.route
        ) {
            val viewModel = koinViewModel<HeroesListAndroidViewModel>().viewModel
            val viewState = viewModel.viewState.collectAsState().value

            LaunchedEffect(true) {
                viewModel.init()
            }

            HeroesListScreen(viewState, onHeroClicked = { heroListItem ->
                navController.navigate("${Screen.HERO_DETAILS.route}/${heroListItem.name}")
            })
        }
        composable(
            route = "${Screen.HERO_DETAILS.route}/{heroName}",
            arguments = listOf(navArgument("heroName") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val viewModel = koinViewModel<HeroDetailsAndroidViewModel>().viewModel
            val viewState = viewModel.viewState.collectAsState().value
            val heroName = backStackEntry.arguments?.getString("heroName")!!

            LaunchedEffect(true) {
                viewModel.init(heroName)
            }

            HeroDetailsScreen(viewState)
        }
    }
}