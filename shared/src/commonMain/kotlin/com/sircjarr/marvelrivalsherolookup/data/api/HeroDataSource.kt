package com.sircjarr.marvelrivalsherolookup.data.api

import com.sircjarr.marvelrivalsherolookup.data.model.HeroDetailsDto
import com.sircjarr.marvelrivalsherolookup.data.model.HeroListItemDto

interface HeroDataSource {
    suspend fun getHeroesList(): List<HeroListItemDto>
    suspend fun getHeroDetails(name: String): HeroDetailsDto
}