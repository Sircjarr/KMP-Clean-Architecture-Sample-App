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
import com.sircjarr.marvelrivalsherolookup.FakeData
import com.sircjarr.marvelrivalsherolookup.feature.heroeslist.ui.HeroListItem
import com.sircjarr.marvelrivalsherolookup.feature.heroeslist.ui.HeroesListViewState
import com.sircjarr.marvelrivalsherolookup.feature.heroeslist.ui.heroesListViewState
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

    val allClasses = remember { list.map { it.classification }.distinct() }
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
                !blacklist.contains(it.classification, ignoreCase = true)
            }.groupBy {
                it.classification
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
        FakeData.heroesListViewState
    }

    override val values: Sequence<HeroesListViewState>
        get() = sequenceOf(defaultState)
}

