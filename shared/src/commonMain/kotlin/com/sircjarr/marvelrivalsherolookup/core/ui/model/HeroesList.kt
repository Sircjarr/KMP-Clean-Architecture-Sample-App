package com.sircjarr.marvelrivalsherolookup.core.ui.model

import com.sircjarr.marvelrivalsherolookup.core.domain.model.HeroListItemModel

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