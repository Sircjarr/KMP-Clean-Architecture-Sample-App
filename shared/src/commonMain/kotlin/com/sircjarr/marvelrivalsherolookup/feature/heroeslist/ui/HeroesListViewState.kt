package com.sircjarr.marvelrivalsherolookup.feature.heroeslist.ui

data class HeroesListViewState(
    val isLoading: Boolean = true,
    val err: String? = null,
    val list: List<HeroListItem> = listOf()
)