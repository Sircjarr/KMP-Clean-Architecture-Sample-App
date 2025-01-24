package com.sircjarr.marvelrivalsherolookup.api.data_hero

interface HeroDataSource {
    suspend fun getHeroesList(): List<HeroListItemDto>
    suspend fun getHeroDetails(name: String): HeroDetailsDto
}