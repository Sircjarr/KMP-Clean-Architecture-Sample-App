package com.sircjarr.marvelrivalsherolookup.ui.screens.herodetails

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.sircjarr.marvelrivalsherolookup.feature.herodetails.ui.HeroDetails
import com.sircjarr.marvelrivalsherolookup.feature.herodetails.ui.HeroDetailsViewState
import com.sircjarr.marvelrivalsherolookup.feature.herodetails.ui.HeroStat
import com.sircjarr.marvelrivalsherolookup.ui.composables.ErrorMessageWithRetry
import com.sircjarr.marvelrivalsherolookup.ui.composables.LoadingMessage
import com.sircjarr.marvelrivalsherolookup.ui.util.pascalCase

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
            HeroDetailsContentRoot(
                checkNotNull(viewState.heroDetails),
                pickRate,
                winRate,
                onGlobeIconClicked = onGlobeIconClicked
            )
        }
    }
}

@Composable
fun HeroDetailsContentRoot(
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
                HeroImageHeader(
                    Modifier
                        .fillMaxWidth()
                        .height(220.dp),
                    imageUrl,
                    onGlobeIconClicked
                )
                HeroNameHeader(
                    Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, end = 8.dp, bottom = 8.dp, top = 8.dp),
                    name.pascalCase(),
                    iconUrl
                )
            }
        }

        HeroDetailsContent(
            Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(8.dp, top = 0.dp),
            stats, `class`, description, winRate, pickRate
        )
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