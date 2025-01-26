package com.sircjarr.marvelrivalsherolookup.ui.screens.heroeslist

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.sircjarr.marvelrivalsherolookup.feature.heroeslist.ui.HeroListItem
import com.sircjarr.marvelrivalsherolookup.ui.res.pickRateColor
import com.sircjarr.marvelrivalsherolookup.ui.res.winRateColor
import com.sircjarr.marvelrivalsherolookup.ui.util.pascalCase

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HeroesListContent(
    modifier: Modifier,
    lazyListState: LazyListState,
    heroMap: Map<String, List<HeroListItem>>,
    onHeroClicked: (HeroListItem) -> Unit
) {

    val itemSize = remember(heroMap) {
        mutableIntStateOf(heroMap.values.flatten().size)
    }

    LazyColumn(modifier, state = lazyListState) {

        heroMap.forEach {
            val (`class`, items) = it

            stickyHeader(key = `class`) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(8.dp)
                ) {
                    Text(
                        text = `class`,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            items(items, key = { e -> e.id }) { item ->
               HeroListItemRow(item, onHeroClicked)
            }
        }

        item {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.padding(top = 16.dp),
                    text = "${itemSize.intValue}",
                    fontSize = 16.sp
                )
                Spacer(Modifier.height(250.dp))
            }
        }
    }
}

@Composable
fun HeroListItemRow(item: HeroListItem, onHeroClicked: (HeroListItem) -> Unit) {
    val (_, _, name, imageUrl, _, pickRate, winRate) = item
    Row(
        modifier = Modifier
            .height(IntrinsicSize.Min)
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp)
            .clickable { onHeroClicked(item) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            modifier = Modifier.size(70.dp),
            model = imageUrl,
            contentDescription = null
        )
        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = name.pascalCase(),
            fontSize = 16.sp
        )

        Spacer(Modifier.weight(1f))
        Divider(
            modifier = Modifier
                .fillMaxHeight()
                .width(1.dp)
                .padding(top = 12.dp, bottom = 12.dp)
        )

        Column(
            modifier = Modifier.width(75.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier,
                text = "${winRate}%",
                fontSize = 18.sp,
                textAlign = TextAlign.End,
                color = winRate.winRateColor
            )
            Text(text = "Win rate", fontSize = 14.sp, color = Color.Gray)
        }

        Divider(
            modifier = Modifier
                .fillMaxHeight()
                .width(1.dp)
                .padding(top = 12.dp, bottom = 12.dp)
        )

        Column(
            modifier = Modifier.width(75.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier,
                text = "${pickRate}%",
                fontSize = 18.sp,
                textAlign = TextAlign.End,
                color = pickRate.pickRateColor
            )
            Text("Pick rate", fontSize = 14.sp, color = Color.Gray)
        }
    }
}