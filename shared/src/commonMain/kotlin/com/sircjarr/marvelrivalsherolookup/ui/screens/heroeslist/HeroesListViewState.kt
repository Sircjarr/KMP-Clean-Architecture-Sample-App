package com.sircjarr.marvelrivalsherolookup.ui.screens.heroeslist

import com.sircjarr.marvelrivalsherolookup.ui.model.HeroListItem

data class HeroesListViewState(
    val isLoading: Boolean = true,
    val list: List<HeroListItem> = listOf()
)