package com.sircjarr.marvelrivalsherolookup.feature_api.data_hero

import kotlinx.serialization.Serializable

@Serializable
data class HeroRatesDto(
    val competitiveMasterAndAbove: List<HeroRateDto>
)

@Serializable
data class HeroRateDto(
    val name: String,
    val role: String,
    val pickRate: String,
    val winRate: String
)