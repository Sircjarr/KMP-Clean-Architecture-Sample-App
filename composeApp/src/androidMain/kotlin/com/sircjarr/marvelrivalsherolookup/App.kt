package com.sircjarr.marvelrivalsherolookup

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sircjarr.marvelrivalsherolookup.ui.LoadingMessage
import com.sircjarr.marvelrivalsherolookup.core.ui.model.HeroListItem
import com.sircjarr.marvelrivalsherolookup.ui.screens.heroeslist.HeroesListAndroidViewModel
import com.sircjarr.marvelrivalsherolookup.core.ui.screens.heroeslist.HeroesListViewState

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

    val (search, setSearch) = remember { mutableStateOf("") }
    val (isExpanded, setExpanded) = remember { mutableStateOf(false) }

    val (_, list) = viewState

    // Todo: Implement / apply filters
    val searchList = remember(list, search) {
        derivedStateOf {
            if (search.isBlank()) list else {
                list.filter {
                    it.name.lowercase().contains(search.lowercase())
                }
            }
        }
    }

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    modifier = Modifier.weight(1f),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent
                    ),
                    singleLine = true,
                    leadingIcon = {
                        Icon(
                            contentDescription = "",
                            painter = painterResource(android.R.drawable.ic_menu_search)
                        )},
                    placeholder = { Text("Search") },
                    value = search,
                    onValueChange = setSearch
                )

                Box {
                    Icon(
                        modifier = Modifier.size(30.dp).clickable {
                            setExpanded(!isExpanded)
                        },
                        contentDescription = "",
                        painter = painterResource(R.drawable.icon_filter)
                    )

                    DropdownMenu(
                        expanded = isExpanded,
                        onDismissRequest = { setExpanded(false) }
                    ) {
                        DropdownMenuItem(
                            onClick = {},
                            content = {}
                        )
                    }
                }
            }
        },
        content = { padding ->
            LazyColumn(Modifier.padding(padding)) {
                items(searchList.value, key = { it.id }) { item ->
                    Card(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                        val (_, _, title, imageUrl) = item
                        Row {
                            // Todo: load image
                            Text("")
                            Text(title, fontSize = 20.sp)
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
                HeroListItem("1", "DUELIST", "Mister Fantastic", "https://r.res.easebar.com/pic/20250109/65590c45-16ea-44f3-a508-c80a9f5547b9.png", ""),
                HeroListItem("2", "DUALIST", "Black Widow", "https://r.res.easebar.com/pic/20241204/f8f32a42-a17a-482c-8da0-cfe273b7da77.png", ""),
                HeroListItem("3", "VANGUARD", "Magneto", "https://www.marvelrivals.com/pc/gw/5da825b19a6a/heros/head_11.png", ""),
                HeroListItem("4", "STRATEGIST", "LUNA SNOW", "https://www.marvelrivals.com/pc/gw/5da825b19a6a/heros/head_18.png", "")
            )
        )
    }

    override val values: Sequence<HeroesListViewState>
        get() = listOf(defaultState).asSequence()
}

