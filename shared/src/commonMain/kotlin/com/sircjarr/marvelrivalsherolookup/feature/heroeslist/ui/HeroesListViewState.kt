package com.sircjarr.marvelrivalsherolookup.feature.heroeslist.ui

import com.sircjarr.marvelrivalsherolookup.FakeData

data class HeroesListViewState(
    val isLoading: Boolean = true,
    val err: String? = null,
    val list: List<HeroListItem> = listOf()
)

val FakeData.Companion.heroesListViewState
    get() = HeroesListViewState(
        isLoading = false,
        err = null,
        list = listOf(
            HeroListItem(
                "1",
                "DUELIST",
                "Mister Fantastic",
                "https://r.res.easebar.com/pic/20250109/65590c45-16ea-44f3-a508-c80a9f5547b9.png",
                "",
                33.0,
                45.0
            ),
            HeroListItem(
                "2",
                "DUELIST",
                "Black Widow",
                "https://r.res.easebar.com/pic/20241204/f8f32a42-a17a-482c-8da0-cfe273b7da77.png",
                "",
                19.0,
                2.0
            ),
            HeroListItem(
                "3",
                "VANGUARD",
                "Magneto",
                "https://www.marvelrivals.com/pc/gw/5da825b19a6a/heros/head_11.png",
                "",
                11.0,
                90.0
            ),
            HeroListItem(
                "4",
                "STRATEGIST",
                "Luna Snow",
                "https://www.marvelrivals.com/pc/gw/5da825b19a6a/heros/head_18.png",
                "",
                33.2,
                88.0
            )
        )
    )