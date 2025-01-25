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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Public
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.sircjarr.marvelrivalsherolookup.feature.herodetails.ui.HeroDetails
import com.sircjarr.marvelrivalsherolookup.feature.herodetails.ui.HeroDetailsViewState
import com.sircjarr.marvelrivalsherolookup.ui.LoadingMessage

@Composable
@Preview(showBackground = true)
fun HeroDetailsScreen(
    @PreviewParameter(provider = HeroesListViewStatePreviewParamProvider::class)
    viewState: HeroDetailsViewState,
    onGlobeIconClicked: () -> Unit = {}
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
    onGlobeIconClicked: () -> Unit
) {
    val (name, realName, `class`, description, imageUrl) = heroDetails

    Box(modifier = Modifier.fillMaxSize().padding(8.dp)) {
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

            Row(modifier = Modifier.fillMaxWidth().padding(top = 16.dp, bottom = 16.dp), verticalAlignment = Alignment.CenterVertically) {
                Column {
                    Text(text = name, style = MaterialTheme.typography.h3)
                    Text(realName, style = MaterialTheme.typography.subtitle1)
                }

                Spacer(modifier = Modifier.weight(1f))

                Icon(
                    modifier = Modifier.clickable { onGlobeIconClicked() }.size(40.dp),
                    imageVector = Icons.Default.Public,
                    contentDescription = "",
                    tint = Color.Blue
                )
            }

            Spacer(Modifier.height(24.dp))

            Column(modifier = Modifier.fillMaxSize()) {

                Box(Modifier, contentAlignment = Alignment.TopCenter) {
                    Text(text = description, style = MaterialTheme.typography.body1)
                }

                Spacer(Modifier.height(24.dp))

                Column {
                    Text("Stats", style = MaterialTheme.typography.h4)

                    Card(Modifier.fillMaxWidth().padding(4.dp)) {
                        Text("Card")
                    }
                }
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
                    description = "Armed with superior intellect and a nanotech battlesuit of his own design, Tony Stark stands alongside gods as the Invincible Iron Man. His state of the art armor turns any battlefield into his personal playground, allowing him to steal the spotlight he so desperately desires.",
                    imageUrl = ""
                )
            )
        }

    override val values: Sequence<HeroDetailsViewState>
        get() = sequenceOf(defaultState)
}