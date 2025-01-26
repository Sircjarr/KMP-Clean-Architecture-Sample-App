package com.sircjarr.marvelrivalsherolookup.feature.herodetails.ui

data class HeroDetailsViewState(
    val isLoading: Boolean = true,
    val err: String? = null,
    val heroDetails: HeroDetails? = null
)