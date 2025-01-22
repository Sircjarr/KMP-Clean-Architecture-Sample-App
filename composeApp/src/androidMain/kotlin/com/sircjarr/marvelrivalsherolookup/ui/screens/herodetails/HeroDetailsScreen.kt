package com.sircjarr.marvelrivalsherolookup.ui.screens.herodetails

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Public
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.sircjarr.marvelrivalsherolookup.core.ui.model.HeroDetails
import com.sircjarr.marvelrivalsherolookup.core.ui.screens.herodetails.HeroDetailsViewState
import com.sircjarr.marvelrivalsherolookup.ui.LoadingMessage

@Composable
fun HeroDetailsScreen(
    @PreviewParameter(provider = HeroesListViewStatePreviewParamProvider::class)
    viewState: HeroDetailsViewState,
    onGlobeIconClicked: (HeroDetails) -> Unit = {}
) {
    if (viewState.isLoading) {
        LoadingMessage(modifier = Modifier.fillMaxSize(), msg = "Fetching hero details. Please wait....")
    } else {
        HeroDetailsContent(checkNotNull(viewState.heroDetails), onGlobeIconClicked = onGlobeIconClicked)
    }
}

@Composable
fun HeroDetailsContent(
    heroDetails: HeroDetails,
    onGlobeIconClicked: (HeroDetails) -> Unit
) {
    val (name, realName, `class`, description, imageUrl) = heroDetails

    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            Box (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(color = Color.DarkGray)
            ) {
                AsyncImage(
                    model = imageUrl,
                    contentScale = ContentScale.Inside,
                    contentDescription = null
                )
            }

            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Column {
                    Text(name)
                    Text(realName)
                }

                Spacer(modifier = Modifier.fillMaxWidth())

                Icon(
                    modifier = Modifier.clickable { onGlobeIconClicked(heroDetails) },
                    imageVector = Icons.Default.Public,
                    contentDescription = ""
                )
            }

            Text(description)

            Card {
                Text(`class`)
            }
        }
    }
}

private class HeroesListViewStatePreviewParamProvider:
    PreviewParameterProvider<HeroDetailsViewState> {

        private val defaultState by lazy {
            HeroDetailsViewState(
                isLoading = false,
                heroDetails = HeroDetails(
                    name = "Iron Man",
                    realName = "Anthony \"Tony\" Stark",
                    `class` = "DUELIST",
                    description = "Armed with superious intellect and a nanotech battlesuit of his own design",
                    imageUrl = ""
                )
            )
        }

    override val values: Sequence<HeroDetailsViewState>
        get() = sequenceOf(defaultState)
}