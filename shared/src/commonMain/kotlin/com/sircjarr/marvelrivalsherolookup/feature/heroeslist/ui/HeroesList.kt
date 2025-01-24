package com.sircjarr.marvelrivalsherolookup.feature.heroeslist.ui

import com.sircjarr.marvelrivalsherolookup.feature.heroeslist.domain.HeroListItemModel

data class HeroListItem(
    val id: String,
    val `class`: String,
    val name: String,
    val imageUrl: String,
    val webUrl: String
)

fun List<HeroListItemModel>.toHeroesList() = map { hero ->
    val (id, `class`, name, imageUrl, webUrl) = hero
    HeroListItem(id, `class`, name, imageUrl, webUrl)
}