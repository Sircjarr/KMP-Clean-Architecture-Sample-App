package com.sircjarr.marvelrivalsherolookup.core.ui.model

import com.sircjarr.marvelrivalsherolookup.core.domain.model.HeroDetailsModel

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