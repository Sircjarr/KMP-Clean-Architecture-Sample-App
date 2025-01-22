package com.sircjarr.marvelrivalsherolookup.ui.screens.herodetails

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.sircjarr.marvelrivalsherolookup.core.ui.model.HeroDetails
import com.sircjarr.marvelrivalsherolookup.core.ui.screens.herodetails.HeroDetailsViewState
import com.sircjarr.marvelrivalsherolookup.ui.LoadingMessage

@Composable
fun HeroDetailsScreen(
    @PreviewParameter(provider = HeroesListViewStatePreviewParamProvider::class)
    viewState: HeroDetailsViewState
) {
    if (viewState.isLoading) {
        LoadingMessage(modifier = Modifier.fillMaxSize(), msg = "Fetching hero details. Please wait....")
    } else {
        HeroDetailsContent(checkNotNull(viewState.heroDetails))
    }
}

@Composable
fun HeroDetailsContent(
    heroDetails: HeroDetails
) {
    Text(heroDetails.toString())
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