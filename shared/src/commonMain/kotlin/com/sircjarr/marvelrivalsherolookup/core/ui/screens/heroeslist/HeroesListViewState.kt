package com.sircjarr.marvelrivalsherolookup.core.ui.screens.heroeslist

import com.sircjarr.marvelrivalsherolookup.core.ui.model.HeroListItem

data class HeroesListViewState(
    val isLoading: Boolean = true,
    val list: List<HeroListItem> = listOf()
)