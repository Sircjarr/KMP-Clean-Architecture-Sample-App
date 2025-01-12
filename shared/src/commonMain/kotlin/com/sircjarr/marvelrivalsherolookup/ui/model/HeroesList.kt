package com.sircjarr.marvelrivalsherolookup.ui.model

import com.sircjarr.marvelrivalsherolookup.domain.model.HeroListItemModel

data class HeroListItem(
    val id: String,
    val tag: String,
    val title: String,
    val images: List<String>,
    val url: String
)

fun List<HeroListItemModel>.toHeroesList() = map { hero ->
    val (id, tag, title, images, url) = hero
    HeroListItem(id, tag, title, images, url)
}