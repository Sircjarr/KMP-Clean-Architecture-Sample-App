package com.sircjarr.marvelrivalsherolookup.feature.herodetails.domain

import com.sircjarr.marvelrivalsherolookup.api.data_hero.HeroDetailsDto

data class HeroDetailsModel(
    val name: String,
    val realName: String,
    val `class`: String,
    val description: String,
    val imageUrl: String,
)

fun HeroDetailsDto.toHeroDetailsModel() = HeroDetailsModel(
    title, name, tag, description, images[2]
)