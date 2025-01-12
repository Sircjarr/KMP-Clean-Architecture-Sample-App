package com.sircjarr.marvelrivalsherolookup.kmp.api

import com.sircjarr.marvelrivalsherolookup.data.api.HeroDataSource
import com.sircjarr.marvelrivalsherolookup.data.model.HeroDetailsDto
import com.sircjarr.marvelrivalsherolookup.data.model.HeroesListDto

class HeroApi: HeroDataSource {

    override suspend fun getHeroesList(): HeroesListDto {
        return HeroesListDto(listOf())
    }

    override suspend fun getHeroDetails(name: String): HeroDetailsDto {
        TODO("Not yet implemented")
    }
}