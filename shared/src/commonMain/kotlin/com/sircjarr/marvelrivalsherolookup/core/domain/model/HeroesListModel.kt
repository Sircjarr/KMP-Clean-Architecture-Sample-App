package com.sircjarr.marvelrivalsherolookup.core.domain.model

import com.sircjarr.marvelrivalsherolookup.core.data.model.HeroListItemDto

data class HeroListItemModel(
    val id: String,
    val `class`: String,
    val name: String,
    val imageUrl: String,
    val webUrl: String
)

fun List<HeroListItemDto>.toHeroListItemModel() = map { hero ->
    val (id, tag, title, images, url) = hero
    HeroListItemModel(id, tag, title, images.first(), url)
}