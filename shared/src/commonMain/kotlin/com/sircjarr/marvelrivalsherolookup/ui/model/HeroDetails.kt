package com.sircjarr.marvelrivalsherolookup.ui.model

import com.sircjarr.marvelrivalsherolookup.domain.model.HeroDetailsModel

data class HeroDetails(
    val title: String,
    val name: String,
    val tag: String,
    val description: String,
    val lore: String,
)

fun HeroDetailsModel.toHeroDetails() = HeroDetails(
    title, name, tag, description, lore
)