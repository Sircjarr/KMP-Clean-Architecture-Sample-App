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
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun MainNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.HEROES_LIST.route) {
        composable(
            route = Screen.HEROES_LIST.route
        ) {
            val viewModel = koinViewModel<HeroesListAndroidViewModel>().viewModel
            val viewState = viewModel.viewState.collectAsState().value

            HeroesListScreen(viewState, onHeroClicked = { heroListItem ->
                // Encode args with '/' that affect Compose Navigation
                val encodedUrl = URLEncoder.encode(heroListItem.webUrl, StandardCharsets.UTF_8.toString())
                navController.navigate("${Screen.HERO_DETAILS.route}/${heroListItem.name}/$encodedUrl/${heroListItem.pickRate}/${heroListItem.winRate}")
            }, onRetryButtonClicked = {
                viewModel.loadHeroesList()
            })
        }
        composable(
            route = "${Screen.HERO_DETAILS.route}/{heroName}/{webUrl}/{pickRate}/{winRate}",
            arguments = listOf(
                navArgument("heroName") {
                    type = NavType.StringType
                },
                navArgument("webUrl") {
                    type = NavType.StringType
                },
                navArgument("pickRate") {
                    type = NavType.FloatType
                },
                navArgument("winRate") {
                    type = NavType.FloatType
                }
            )
        ) { backStackEntry ->
            val viewModel = koinViewModel<HeroDetailsAndroidViewModel>().viewModel
            val viewState = viewModel.viewState.collectAsState().value
            val heroName = backStackEntry.arguments?.getString("heroName")!!
            val webUrl = backStackEntry.arguments?.getString("webUrl")!!
            val pickRate = backStackEntry.arguments?.getFloat("pickRate")!!
            val winRate = backStackEntry.arguments?.getFloat("winRate")!!

            LaunchedEffect(true) {
                viewModel.init(heroName, webUrl)
            }

            HeroDetailsScreen(viewState,
                pickRate = pickRate,
                winRate = winRate,
                onGlobeIconClicked = viewModel::launchHeroUrlInExternalBrowser,
                onRetryButtonClicked = { viewModel.loadHeroDetails(heroName) }
            )
        }
    }
}