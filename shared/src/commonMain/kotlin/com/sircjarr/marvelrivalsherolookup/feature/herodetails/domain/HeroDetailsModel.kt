package com.sircjarr.marvelrivalsherolookup.feature.herodetails.domain

import com.sircjarr.marvelrivalsherolookup.feature_api.data_hero.HeroDetailsDto
import com.sircjarr.marvelrivalsherolookup.feature_api.data_hero.HeroStatDto
import kotlinx.serialization.Serializable

data class HeroDetailsModel(
    val name: String,
    val realName: String,
    val `class`: String,
    val description: String,
    val imageUrl: String,
    val stats: List<HeroStatModel>
)

data class HeroStatModel(
    val title: String,
    val value: String
)

fun HeroDetailsDto.toHeroDetailsModel() = HeroDetailsModel(
    title, name, tag, description, images[2], stats.map { HeroStatModel(it.title, it.value)}
)