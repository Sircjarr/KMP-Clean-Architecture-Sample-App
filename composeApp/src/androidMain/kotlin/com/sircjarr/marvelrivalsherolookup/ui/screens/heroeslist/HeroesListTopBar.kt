package com.sircjarr.marvelrivalsherolookup.ui.screens.heroeslist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sircjarr.marvelrivalsherolookup.R
import com.sircjarr.marvelrivalsherolookup.ui.res.ColorRes
import kotlinx.coroutines.launch

@Composable
fun HeroesListTopBar(
    search: String,
    setSearch: (String) -> Unit,
    allClasses: List<String>,
    blacklist: String,
    setBlacklist: (String) -> Unit,
    lazyListState: LazyListState
) {
    val (isExpanded, setExpanded) = remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                modifier = Modifier.weight(1f),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = ColorRes.marvelRed
                ),
                singleLine = true,
                leadingIcon = {
                    Icon(
                        contentDescription = "",
                        painter = painterResource(android.R.drawable.ic_menu_search)
                    )
                },
                placeholder = { Text("Search") },
                value = search,
                onValueChange = setSearch
            )

            Box {
                Icon(
                    modifier = Modifier
                        .size(30.dp)
                        .clickable {
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
                                            onCheckedChange = { onClick() },
                                            colors = CheckboxDefaults.colors(
                                                checkedColor = ColorRes.marvelRed
                                            )
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
    }
}