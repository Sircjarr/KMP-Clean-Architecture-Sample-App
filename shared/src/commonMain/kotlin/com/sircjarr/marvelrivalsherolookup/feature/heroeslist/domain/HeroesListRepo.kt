package com.sircjarr.marvelrivalsherolookup.feature.heroeslist.domain

import com.sircjarr.marvelrivalsherolookup.api.data_hero.HeroDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class HeroesListRepo(private val heroDataSource: HeroDataSource) {
    private val flow = MutableSharedFlow<List<HeroListItemModel>?>()

    fun getFlow(): Flow<List<HeroListItemModel>?> {
        return flow
    }

    suspend fun loadHeroesList() {
        flow.emit(heroDataSource.getHeroesList().toHeroListItemModel())
    }
}