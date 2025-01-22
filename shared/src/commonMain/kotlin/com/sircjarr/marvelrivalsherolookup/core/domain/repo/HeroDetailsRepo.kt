package com.sircjarr.marvelrivalsherolookup.core.domain.repo

import com.sircjarr.marvelrivalsherolookup.core.data.api.HeroDataSource
import com.sircjarr.marvelrivalsherolookup.core.domain.model.HeroDetailsModel
import com.sircjarr.marvelrivalsherolookup.core.domain.model.HeroListItemModel
import com.sircjarr.marvelrivalsherolookup.core.domain.model.toHeroDetailsModel
import com.sircjarr.marvelrivalsherolookup.core.domain.model.toHeroListItemModel
import com.sircjarr.marvelrivalsherolookup.core.ui.model.HeroDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

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