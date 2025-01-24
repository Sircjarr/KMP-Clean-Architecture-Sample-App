package com.sircjarr.marvelrivalsherolookup.feature.herodetails.ui

import com.sircjarr.marvelrivalsherolookup.feature.herodetails.domain.HeroDetailsModel

data class HeroDetails(
    val name: String,
    val realName: String,
    val `class`: String,
    val description: String,
    val imageUrl: String,
)

fun HeroDetailsModel.toHeroDetails() = HeroDetails(
    name, realName, `class`, description, imageUrl
)