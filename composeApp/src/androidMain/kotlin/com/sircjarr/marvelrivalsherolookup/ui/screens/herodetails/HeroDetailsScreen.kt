package com.sircjarr.marvelrivalsherolookup.ui.screens.herodetails

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Link
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.sircjarr.marvelrivalsherolookup.feature.herodetails.ui.HeroDetails
import com.sircjarr.marvelrivalsherolookup.feature.herodetails.ui.HeroDetailsViewState
import com.sircjarr.marvelrivalsherolookup.feature.herodetails.ui.HeroStat
import com.sircjarr.marvelrivalsherolookup.ui.ColorRes
import com.sircjarr.marvelrivalsherolookup.ui.ErrorMessageWithRetry
import com.sircjarr.marvelrivalsherolookup.ui.LoadingMessage
import com.sircjarr.marvelrivalsherolookup.ui.pickRateColor
import com.sircjarr.marvelrivalsherolookup.ui.winRateColor

@Composable
@Preview(showBackground = true)
fun HeroDetailsScreen(
    @PreviewParameter(provider = HeroesListViewStatePreviewParamProvider::class)
    viewState: HeroDetailsViewState,
    pickRate: Float = 5.5f,
    winRate: Float = 53.31f,
    onGlobeIconClicked: () -> Unit = {},
    onRetryButtonClicked: () -> Unit = {}
) {

    when {
        viewState.isLoading -> {
            LoadingMessage(
                modifier = Modifier.fillMaxSize(),
                msg = "Fetching hero details. Please wait...."
            )
        }

        viewState.err != null -> {
            ErrorMessageWithRetry(
                Modifier.fillMaxSize(),
                checkNotNull(viewState.err),
                onRetryButtonClicked
            )
        }

        else -> {
            HeroDetailsContent(
                checkNotNull(viewState.heroDetails),
                pickRate,
                winRate,
                onGlobeIconClicked = onGlobeIconClicked
            )
        }
    }
}

@Composable
fun HeroDetailsContent(
    heroDetails: HeroDetails,
    pickRate: Float = 5.5f,
    winRate: Float = 20.31f,
    onGlobeIconClicked: () -> Unit
) {
    val (name, realName, `class`, description, imageUrl, iconUrl, stats) = heroDetails
    val scrollState = rememberScrollState()

    Column(modifier = Modifier.fillMaxSize()) {
        Box {
            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                        .background(color = Color.Black)
                ) {
                    AsyncImage(
                        model = imageUrl,
                        contentScale = ContentScale.Inside,
                        contentDescription = null
                    )

                    Icon(
                        modifier = Modifier
                            .padding(8.dp)
                            .clickable { onGlobeIconClicked() }
                            .size(40.dp)
                            .align(Alignment.TopEnd),
                        imageVector = Icons.Default.Link,
                        contentDescription = "",
                        tint = Color.DarkGray
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, end = 8.dp, bottom = 8.dp, top = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = name, style = MaterialTheme.typography.h3)

                    Spacer(modifier = Modifier.weight(1f))

                    AsyncImage(
                        modifier = Modifier
                            .clickable { onGlobeIconClicked() }
                            .size(40.dp),
                        model = iconUrl,
                        contentDescription = "",
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(8.dp, top = 0.dp)
        ) {
            Box(
                Modifier
                    .background(ColorRes.gold)
                    .padding(4.dp)
            ) {
                Text(`class`, color = Color.White, fontSize = 16.sp)
            }

            Spacer(Modifier.height(14.dp))

            Column(modifier = Modifier.fillMaxSize()) {

                Box(Modifier, contentAlignment = Alignment.TopCenter) {
                    Text(text = description, style = MaterialTheme.typography.body1)
                }

                Spacer(Modifier.height(18.dp))

                Card(Modifier.fillMaxWidth()) {

                    Column {
                        Text(
                            modifier = Modifier.padding(8.dp),
                            text = "Base stats",
                            style = MaterialTheme.typography.h5
                        )
                        Divider(
                            Modifier
                                .height(1.dp)
                                .fillMaxWidth()
                                .padding(start = 2.dp, end = 2.dp)
                        )

                        stats.forEach {
                            Row(
                                Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        start = 8.dp,
                                        end = 8.dp,
                                        top = 4.dp,
                                        bottom = 4.dp
                                    )
                            ) {
                                Text(
                                    text = it.title,
                                    style = MaterialTheme.typography.body1
                                )
                                Spacer(Modifier.weight(1f))
                                Text(
                                    text = it.value,
                                    style = MaterialTheme.typography.body1
                                )
                            }
                        }
                    }
                }

                Spacer(Modifier.height(18.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Card(
                        Modifier
                            .weight(1f)
                            .aspectRatio(1f)
                    ) {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "$winRate%",
                                color = winRate.winRateColor,
                                fontSize = 32.sp
                            )
                            Text("Win rate", modifier = Modifier.padding(8.dp))
                        }
                    }

                    Spacer(Modifier.width(18.dp))

                    Card(
                        Modifier
                            .weight(1f)
                            .aspectRatio(1f)
                    ) {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "$pickRate%",
                                color = pickRate.pickRateColor,
                                fontSize = 32.sp
                            )
                            Text("Pick rate", modifier = Modifier.padding(8.dp))
                        }
                    }
                }
            }
        }
    }
}

private class HeroesListViewStatePreviewParamProvider :
    PreviewParameterProvider<HeroDetailsViewState> {

    private val defaultState by lazy {
        HeroDetailsViewState(
            isLoading = false,
            heroDetails = HeroDetails(
                name = "Iron Man",
                realName = "Anthony \"Tony\" Stark",
                `class` = "DUELIST",
                description = "Armed with superior intellect and a nanotech battlesuit of his own design, Tony Stark stands alongside gods as the Invincible Iron Man. His state of the art armor turns any battlefield into his personal playground, allowing him to steal the spotlight he so desperately desires.",
                imageUrl = "",
                iconUrl = "",
                stats = listOf(
                    HeroStat("Health", "250"),
                    HeroStat("Movement Speed", "6 m/s"),
                    HeroStat("Movement Mode", "Flight"),
                )
            )
        )
    }

    override val values: Sequence<HeroDetailsViewState>
        get() = sequenceOf(defaultState)
}