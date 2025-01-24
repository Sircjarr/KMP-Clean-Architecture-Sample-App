package com.sircjarr.marvelrivalsherolookup.ui.screens.heroeslist

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
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
import coil3.compose.AsyncImage
import com.sircjarr.marvelrivalsherolookup.R
import com.sircjarr.marvelrivalsherolookup.feature.heroeslist.ui.HeroListItem
import com.sircjarr.marvelrivalsherolookup.feature.heroeslist.ui.HeroesListViewState
import com.sircjarr.marvelrivalsherolookup.ui.LoadingMessage
import kotlinx.coroutines.launch

@Composable
@Preview
fun HeroesListScreen(
    @PreviewParameter(provider = HeroesListViewStatePreviewParamProvider::class)
    viewState: HeroesListViewState,
    onHeroClicked: (HeroListItem) -> Unit = {}
) {
    if (viewState.isLoading) {
        LoadingMessage(Modifier.fillMaxSize(), "Fetching heroes list, please wait...")
    } else {
        HeroesListContent(viewState, onHeroClicked)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HeroesListContent(
    viewState: HeroesListViewState,
    onHeroClicked: (HeroListItem) -> Unit = {}
) {

    val (_, list) = viewState

    val allClasses = remember { list.map { it.`class` }.distinct() }
    val (search, setSearch) = remember { mutableStateOf("") }
    val (isExpanded, setExpanded) = remember { mutableStateOf(false) }
    val (blacklist, setBlacklist) = remember { mutableStateOf("") }
    val lazyListState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    // Map of class to Hero
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

    val itemSize = remember(heroMap) {
        mutableIntStateOf(heroMap.value.values.flatten().size)
    }

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier.fillMaxWidth().padding(8.dp),
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
                        Column {
                            allClasses.forEach { `class` ->
                                fun onClick() {
                                    if (blacklist.contains(`class`)) {
                                        setBlacklist(blacklist.replace(`class`, ""))
                                    } else {
                                        setBlacklist(blacklist.plus(`class`))
                                    }
                                    scope.launch {
                                        lazyListState.animateScrollToItem(0)
                                    }
                                }

                                DropdownMenuItem(
                                    onClick = ::onClick,
                                    content = {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Checkbox(
                                                checked = !blacklist.contains(`class`),
                                                onCheckedChange = { onClick() }
                                            )
                                            Text(`class`)
                                        }
                                    }
                                )
                            }
                        }
                    }
                }
            }
        },
        content = { padding ->
            LazyColumn(Modifier.padding(padding), state = lazyListState) {

                heroMap.value.forEach {
                    val (`class`, items) = it

                    stickyHeader(key = `class`) {
                        Box(modifier = Modifier.fillMaxWidth().background(Color.White)) {
                            Text(
                                modifier = Modifier.padding(8.dp),
                                text = `class`, fontSize = 16.sp
                            )
                        }
                    }

                    items(items, key = { e -> e.id }) { item ->
                        Card(modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 4.dp, end = 4.dp)
                            .clickable { onHeroClicked(item) }
                        ) {
                            val (_, _, name, imageUrl) = item
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                AsyncImage(
                                    modifier = Modifier.size(70.dp),
                                    model = imageUrl,
                                    contentDescription = null
                                )
                                Text(
                                    modifier = Modifier.padding(start = 8.dp),
                                    text = name.lowercase().split(" ").map { it.replaceFirstChar { it.uppercase() }}.joinToString(" "),
                                    fontSize = 20.sp
                                )
                            }
                        }
                    }
                }

                item {
                    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(modifier = Modifier.padding(top = 16.dp), text = "${itemSize.intValue}", fontSize = 16.sp)
                        Spacer(Modifier.height(250.dp))
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
                HeroListItem("2", "DUELIST", "Black Widow", "https://r.res.easebar.com/pic/20241204/f8f32a42-a17a-482c-8da0-cfe273b7da77.png", ""),
                HeroListItem("3", "VANGUARD", "Magneto", "https://www.marvelrivals.com/pc/gw/5da825b19a6a/heros/head_11.png", ""),
                HeroListItem("4", "STRATEGIST", "LUNA SNOW", "https://www.marvelrivals.com/pc/gw/5da825b19a6a/heros/head_18.png", "")
            )
        )
    }

    override val values: Sequence<HeroesListViewState>
        get() = sequenceOf(defaultState)
}

