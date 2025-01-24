package com.sircjarr.marvelrivalsherolookup.feature.herodetails.domain

import com.sircjarr.marvelrivalsherolookup.api.data_hero.HeroDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class HeroDetailsRepo(private val heroDataSource: HeroDataSource) {

    // Map of hero name query arg to HeroDetails
    private val heroDetailsMap: MutableMap<String, HeroDetailsModel> = mutableMapOf()
    private val flow = MutableStateFlow<HeroDetailsModel?>(null)

    suspend fun load(nameArg: String) {
        flow.value = if (hasCached(nameArg)) {
            checkNotNull(heroDetailsMap[nameArg])
        } else {
            val hero = heroDataSource.getHeroDetails(nameArg)
            heroDetailsMap[nameArg] = hero.toHeroDetailsModel()
            checkNotNull(heroDetailsMap[nameArg])
        }
    }

    fun getFlow(nameArg: String): Flow<HeroDetailsModel?> {
        flow.value = if (hasCached(nameArg)) {
            checkNotNull(heroDetailsMap[nameArg])
        } else {
            null
        }
        return flow
    }

    fun hasCached(nameArg: String): Boolean {
        return heroDetailsMap.containsKey(nameArg)
    }
}