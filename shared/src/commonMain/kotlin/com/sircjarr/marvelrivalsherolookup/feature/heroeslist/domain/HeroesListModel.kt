package com.sircjarr.marvelrivalsherolookup.feature.heroeslist.domain

import com.sircjarr.marvelrivalsherolookup.feature_api.data_hero.HeroListItemDto
import com.sircjarr.marvelrivalsherolookup.feature_api.data_hero.HeroRateDto

data class HeroListItemModel(
    val id: String,
    val `class`: String,
    val name: String,
    val imageUrl: String,
    val webUrl: String,
    val pickRate: Double,
    val winRate: Double
)

fun List<HeroListItemDto>.toHeroListItemModel(rates: List<HeroRateDto>) = map { hero ->
    val (id, tag, title, images, url) = hero

    val rate = rates.find { it.name.lowercase() == title.lowercase() }
    rate?.let {
        println("[W] rate not found for list item name: $title")
    }

    HeroListItemModel(
        id,
        tag.ifEmpty { "ROGUE" },
        title,
        images.first(),
        url,
        rate?.pickRate?.replace("%", "")?.toDouble() ?: -1.0,
        rate?.winRate?.replace("%", "")?.toDouble() ?: -1.0
    )
}