package com.sircjarr.marvelrivalsherolookup.ui.model

import com.sircjarr.marvelrivalsherolookup.domain.model.HeroListItemModel

data class HeroListItem(
    val id: String,
    val tag: String,
    val title: String,
    val imageUrl: String,
    val url: String
)

fun List<HeroListItemModel>.toHeroesList() = map { hero ->
    val (id, tag, title, imageUrl, url) = hero
    HeroListItem(id, tag, title, imageUrl, url)
}