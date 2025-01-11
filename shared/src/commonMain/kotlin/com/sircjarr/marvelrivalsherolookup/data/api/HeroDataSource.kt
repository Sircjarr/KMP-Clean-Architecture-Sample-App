package com.sircjarr.marvelrivalsherolookup.data.api

import com.sircjarr.marvelrivalsherolookup.data.model.HeroDetailsDto
import com.sircjarr.marvelrivalsherolookup.data.model.HeroesListDto

interface HeroDataSource {
    suspend fun getHeroesList(): HeroesListDto
    suspend fun getHeroDetails(name: String): HeroDetailsDto
}