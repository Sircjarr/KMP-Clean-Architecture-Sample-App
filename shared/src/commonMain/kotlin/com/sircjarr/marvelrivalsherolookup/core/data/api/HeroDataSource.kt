package com.sircjarr.marvelrivalsherolookup.core.data.api

import com.sircjarr.marvelrivalsherolookup.core.data.model.HeroDetailsDto
import com.sircjarr.marvelrivalsherolookup.core.data.model.HeroListItemDto

interface HeroDataSource {
    suspend fun getHeroesList(): List<HeroListItemDto>
    suspend fun getHeroDetails(name: String): HeroDetailsDto
}