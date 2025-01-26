package com.sircjarr.marvelrivalsherolookup.feature.herodetails.domain

import com.sircjarr.marvelrivalsherolookup.feature_api.data_hero.HeroDetailsDto

data class HeroDetailsModel(
    val name: String,
    val realName: String,
    val `class`: String,
    val description: String,
    val imageUrl: String,
    val iconUrl: String,
    val stats: List<HeroStatModel>
)

data class HeroStatModel(
    val title: String,
    val value: String
)

fun HeroDetailsDto.toHeroDetailsModel() = HeroDetailsModel(
    title, name, tag, description, images[2], images[7], stats.map { HeroStatModel(it.title, it.value)}
)