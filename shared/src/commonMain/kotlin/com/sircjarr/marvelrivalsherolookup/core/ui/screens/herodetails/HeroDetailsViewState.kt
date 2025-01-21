package com.sircjarr.marvelrivalsherolookup.core.ui.screens.herodetails

import com.sircjarr.marvelrivalsherolookup.core.ui.model.HeroDetails

data class HeroDetailsViewState(
    val isLoading: Boolean = true,
    val heroDetails: HeroDetails? = null
)