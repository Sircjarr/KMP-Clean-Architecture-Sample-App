package com.sircjarr.marvelrivalsherolookup.domain.repo

import com.sircjarr.marvelrivalsherolookup.data.api.HeroDataSource
import com.sircjarr.marvelrivalsherolookup.data.model.HeroesListDto
import com.sircjarr.marvelrivalsherolookup.domain.model.HeroesListModel
import com.sircjarr.marvelrivalsherolookup.domain.model.toHeroesListModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.map

class HeroesListRepo(private val heroDataSource: HeroDataSource) {
    private val flow = MutableSharedFlow<HeroesListDto?>()

    fun getFlow(): Flow<HeroesListModel?> {
        return flow.map {
            it?.toHeroesListModel()
        }
    }

    suspend fun loadHeroesList() {
        flow.emit(heroDataSource.getHeroesList())
    }
}