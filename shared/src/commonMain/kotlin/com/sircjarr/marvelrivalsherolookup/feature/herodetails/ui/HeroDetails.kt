package com.sircjarr.marvelrivalsherolookup.feature.herodetails.ui

import com.sircjarr.marvelrivalsherolookup.feature.herodetails.domain.HeroDetailsModel

data class HeroDetails(
    val name: String,
    val realName: String,
    val classification: String,
    val description: String,
    val imageUrl: String,
    val iconUrl: String,
    val stats: List<HeroStat>
)

data class HeroStat(
    val title: String,
    val value: String
)

fun HeroDetailsModel.toHeroDetails() = HeroDetails(
    name, realName, `class`, description, imageUrl, iconUrl, stats.map { HeroStat(it.title, it.value) }
)