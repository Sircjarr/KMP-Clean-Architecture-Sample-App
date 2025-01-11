package com.sircjarr.marvelrivalsherolookup.ui.screens.heroeslist

import com.sircjarr.marvelrivalsherolookup.ui.model.HeroDetail

data class HeroesListViewState(
    val isLoading: Boolean = true,
    val list: List<HeroDetail> = listOf()
)