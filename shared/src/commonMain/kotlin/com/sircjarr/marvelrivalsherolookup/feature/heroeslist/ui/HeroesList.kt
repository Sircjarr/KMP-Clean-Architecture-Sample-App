package com.sircjarr.marvelrivalsherolookup.feature.heroeslist.ui

import com.sircjarr.marvelrivalsherolookup.feature.heroeslist.domain.HeroListItemModel

data class HeroListItem(
    val id: String,
    val `class`: String,
    val name: String,
    val imageUrl: String,
    val webUrl: String,
    val pickRate: Double,
    val winRate: Double
)

fun List<HeroListItemModel>.toHeroesList() = map { hero ->
    val (id, `class`, name, imageUrl, webUrl, pickRate, winRate) = hero
    HeroListItem(id, `class`, name, imageUrl, webUrl, pickRate, winRate)
}