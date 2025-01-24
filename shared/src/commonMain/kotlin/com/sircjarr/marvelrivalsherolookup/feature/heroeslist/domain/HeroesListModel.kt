package com.sircjarr.marvelrivalsherolookup.feature.heroeslist.domain

import com.sircjarr.marvelrivalsherolookup.api.data_hero.HeroListItemDto

data class HeroListItemModel(
    val id: String,
    val `class`: String,
    val name: String,
    val imageUrl: String,
    val webUrl: String
)

fun List<HeroListItemDto>.toHeroListItemModel() = map { hero ->
    val (id, tag, title, images, url) = hero
    HeroListItemModel(id, tag.ifEmpty { "ROGUE" }, title, images.first(), url)
}