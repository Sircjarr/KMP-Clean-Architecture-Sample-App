package com.sircjarr.marvelrivalsherolookup.feature_api.data_hero

interface HeroDataSource {
    suspend fun getHeroesList(): List<HeroListItemDto>
    suspend fun getHeroDetails(name: String): HeroDetailsDto
    suspend fun getHeroRates(): HeroRatesDto
}