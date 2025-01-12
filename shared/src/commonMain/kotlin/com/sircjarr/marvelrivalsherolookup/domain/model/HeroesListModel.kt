package com.sircjarr.marvelrivalsherolookup.domain.model

import com.sircjarr.marvelrivalsherolookup.data.model.HeroListItemDto

data class HeroListItemModel(
    val id: String,
    val tag: String,
    val title: String,
    val imageUrl: String,
    val url: String
)

fun List<HeroListItemDto>.toHeroListItemModel() = map { hero ->
    val (id, tag, title, images, url) = hero
    HeroListItemModel(id, tag, title, images.first(), url)
}