package com.sircjarr.marvelrivalsherolookup

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.sircjarr.marvelrivalsherolookup.ui.LoadingMessage
import com.sircjarr.marvelrivalsherolookup.ui.model.HeroListItem
import com.sircjarr.marvelrivalsherolookup.ui.screens.heroeslist.HeroesListAndroidViewModel
import com.sircjarr.marvelrivalsherolookup.ui.screens.heroeslist.HeroesListViewState

import org.koin.androidx.compose.koinViewModel

@Composable
fun App() {
    MaterialTheme {
        val viewModel = koinViewModel<HeroesListAndroidViewModel>().viewModel
        val viewState = viewModel.viewState.collectAsState().value

        LaunchedEffect(true) {
            viewModel.init()
        }

        if (viewState.isLoading) {
            LoadingMessage(Modifier.fillMaxSize(), "Fetching heroes list, please wait...")
        } else {
            HeroListScreen(viewState)
        }
    }
}

@Composable
@Preview
fun HeroListScreen(
    @PreviewParameter(provider = HeroesListViewStatePreviewParamProvider::class)
    viewState: HeroesListViewState
) {

    val (_, list) = viewState

    Scaffold(
        topBar = {

        },
        content = { padding ->
            LazyColumn(Modifier.padding(padding)) {
                items(list, key = { it.id }) { item ->
                    Card(modifier = Modifier.fillMaxWidth()) {
                        Row {
                            Text(item.tag)
                            Text(item.title)
                        }
                    }
                }
            }
        }
    )
}

private class HeroesListViewStatePreviewParamProvider:
    PreviewParameterProvider<HeroesListViewState>
{
     private val defaultState by lazy {
        HeroesListViewState(
            isLoading = false,
            list = listOf(
                HeroListItem("1", "DUELIST", "Mister Fantastic", "", ""),
                HeroListItem("1", "DUALIST", "Black Widow", "", ""),
                HeroListItem("1", "VANGUARD", "Magneto", "", ""),
                HeroListItem("1", "STRATEGIST", "LUNA SNOW", "", "")
            )
        )
    }

    override val values: Sequence<HeroesListViewState>
        get() = listOf(defaultState).asSequence()
}

