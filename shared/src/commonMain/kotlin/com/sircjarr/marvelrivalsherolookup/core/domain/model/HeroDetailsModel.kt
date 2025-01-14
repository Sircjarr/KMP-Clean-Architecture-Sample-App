package com.sircjarr.marvelrivalsherolookup.core.domain.model

import com.sircjarr.marvelrivalsherolookup.core.data.model.HeroDetailsDto

data class HeroDetailsModel(
    val title: String,
    val name: String,
    val tag: String,
    val description: String,
    val lore: String,
)

fun HeroDetailsDto.toHeroDetailsModel() = HeroDetailsModel(
    title, name, tag, description, lore
)