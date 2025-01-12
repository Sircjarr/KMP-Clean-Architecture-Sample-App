package com.sircjarr.marvelrivalsherolookup.domain.repo

import com.sircjarr.marvelrivalsherolookup.data.api.HeroDataSource
import com.sircjarr.marvelrivalsherolookup.domain.model.HeroListItemModel
import com.sircjarr.marvelrivalsherolookup.domain.model.toHeroListItemModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.map

class HeroesListRepo(private val heroDataSource: HeroDataSource) {
    private val flow = MutableSharedFlow<List<HeroListItemModel>?>()

    fun getFlow(): Flow<List<HeroListItemModel>?> {
        return flow
    }

    suspend fun loadHeroesList() {
        flow.emit(heroDataSource.getHeroesList().toHeroListItemModel())
    }
}