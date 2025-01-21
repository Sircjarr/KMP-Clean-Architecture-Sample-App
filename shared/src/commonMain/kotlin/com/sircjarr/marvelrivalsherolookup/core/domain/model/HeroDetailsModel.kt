package com.sircjarr.marvelrivalsherolookup.core.domain.model

import com.sircjarr.marvelrivalsherolookup.core.data.model.HeroDetailsDto

data class HeroDetailsModel(
    val name: String,
    val realName: String,
    val `class`: String,
    val description: String,
    val imageUrl: String,
)

fun HeroDetailsDto.toHeroDetailsModel() = HeroDetailsModel(
    title, name, tag, description, images[3]
)