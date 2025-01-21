package com.sircjarr.marvelrivalsherolookup.core.domain.repo

import com.sircjarr.marvelrivalsherolookup.core.data.api.HeroDataSource
import com.sircjarr.marvelrivalsherolookup.core.domain.model.HeroDetailsModel
import com.sircjarr.marvelrivalsherolookup.core.domain.model.HeroListItemModel
import com.sircjarr.marvelrivalsherolookup.core.domain.model.toHeroDetailsModel
import com.sircjarr.marvelrivalsherolookup.core.domain.model.toHeroListItemModel
import com.sircjarr.marvelrivalsherolookup.core.ui.model.HeroDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class HeroDetailsRepo(private val heroDataSource: HeroDataSource) {

    // Map of hero name query arg to HeroDetails
    private val heroDetailsMap: MutableMap<String, HeroDetailsModel> = mutableMapOf()

    suspend fun load(nameArg: String): HeroDetailsModel {
        val hero = heroDataSource.getHeroDetails(nameArg)
        heroDetailsMap[nameArg] = hero.toHeroDetailsModel()
        return get(nameArg)
    }

    fun get(nameArg: String): HeroDetailsModel {
        return checkNotNull(heroDetailsMap[nameArg])
    }

    fun hasCached(nameArg: String): Boolean {
        return heroDetailsMap.containsKey(nameArg)
    }
}