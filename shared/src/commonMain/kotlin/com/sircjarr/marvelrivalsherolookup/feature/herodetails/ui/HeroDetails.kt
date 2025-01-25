package com.sircjarr.marvelrivalsherolookup.feature.herodetails.ui

import com.sircjarr.marvelrivalsherolookup.feature.herodetails.domain.HeroDetailsModel
import com.sircjarr.marvelrivalsherolookup.feature.herodetails.domain.HeroStatModel
import kotlinx.serialization.Serializable

data class HeroDetails(
    val name: String,
    val realName: String,
    val `class`: String,
    val description: String,
    val imageUrl: String,
    val stats: List<HeroStat>
)

data class HeroStat(
    val title: String,
    val value: String
)

fun HeroDetailsModel.toHeroDetails() = HeroDetails(
    name, realName, `class`, description, imageUrl, stats.map { HeroStat(it.title, it.value) }
)