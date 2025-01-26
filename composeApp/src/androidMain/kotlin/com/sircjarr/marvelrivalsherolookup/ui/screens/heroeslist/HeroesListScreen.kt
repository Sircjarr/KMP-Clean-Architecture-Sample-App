package com.sircjarr.marvelrivalsherolookup.ui.screens.heroeslist

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.sircjarr.marvelrivalsherolookup.feature.heroeslist.ui.HeroListItem
import com.sircjarr.marvelrivalsherolookup.feature.heroeslist.ui.HeroesListViewState
import com.sircjarr.marvelrivalsherolookup.ui.composables.ErrorMessageWithRetry
import com.sircjarr.marvelrivalsherolookup.ui.composables.LoadingMessage

@Composable
@Preview
fun HeroesListScreen(
    @PreviewParameter(provider = HeroesListViewStatePreviewParamProvider::class)
    viewState: HeroesListViewState,
    onHeroClicked: (HeroListItem) -> Unit = {},
    onRetryButtonClicked: () -> Unit = {}
) {
    when {
        viewState.isLoading -> {
            LoadingMessage(Modifier.fillMaxSize(), "Fetching heroes list, please wait...")
        }

        viewState.err != null -> {
            ErrorMessageWithRetry(
                Modifier.fillMaxSize(),
                checkNotNull(viewState.err),
                onRetryButtonClicked
            )
        }

        else -> {
            HeroesListScaffold(viewState, onHeroClicked)
        }
    }
}

@Composable
fun HeroesListScaffold(
    viewState: HeroesListViewState,
    onHeroClicked: (HeroListItem) -> Unit = {}
) {

    val (_, _, list) = viewState

    val allClasses = remember { list.map { it.`class` }.distinct() }
    val (search, setSearch) = remember { mutableStateOf("") }
    val (blacklist, setBlacklist) = remember { mutableStateOf("") }
    val lazyListState = rememberLazyListState()

    // Map of class to list of Heroes
    val heroMap = remember(list, search, blacklist) {
        derivedStateOf {
            if (search.isBlank()) list else {
                list.filter {
                    it.name.lowercase().contains(search.lowercase())
                }
            }.filter {
                !blacklist.contains(it.`class`, ignoreCase = true)
            }.groupBy {
                it.`class`
            }.toSortedMap()
        }
    }

    Scaffold(
        topBar = {
            HeroesListTopBar(
                search,
                setSearch,
                allClasses,
                blacklist,
                setBlacklist,
                lazyListState
            )
        },
        content = { padding ->
            HeroesListContent(
                Modifier.padding(padding),
                lazyListState,
                heroMap.value,
                onHeroClicked
            )
        }
    )
}

private class HeroesListViewStatePreviewParamProvider :
    PreviewParameterProvider<HeroesListViewState> {
    private val defaultState by lazy {
        HeroesListViewState(
            isLoading = false,
            list = listOf(
                HeroListItem(
                    "1",
                    "DUELIST",
                    "Mister Fantastic",
                    "https://r.res.easebar.com/pic/20250109/65590c45-16ea-44f3-a508-c80a9f5547b9.png",
                    "",
                    33.0,
                    45.0
                ),
                HeroListItem(
                    "2",
                    "DUELIST",
                    "Black Widow",
                    "https://r.res.easebar.com/pic/20241204/f8f32a42-a17a-482c-8da0-cfe273b7da77.png",
                    "",
                    19.0,
                    2.0
                ),
                HeroListItem(
                    "3",
                    "VANGUARD",
                    "Magneto",
                    "https://www.marvelrivals.com/pc/gw/5da825b19a6a/heros/head_11.png",
                    "",
                    11.0,
                    90.0
                ),
                HeroListItem(
                    "4",
                    "STRATEGIST",
                    "LUNA SNOW",
                    "https://www.marvelrivals.com/pc/gw/5da825b19a6a/heros/head_18.png",
                    "",
                    33.2,
                    88.0
                )
            )
        )
    }

    override val values: Sequence<HeroesListViewState>
        get() = sequenceOf(defaultState)
}

