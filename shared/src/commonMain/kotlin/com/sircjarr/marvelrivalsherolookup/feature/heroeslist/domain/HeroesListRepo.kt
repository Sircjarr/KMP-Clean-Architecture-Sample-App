package com.sircjarr.marvelrivalsherolookup.feature.heroeslist.domain

import com.sircjarr.marvelrivalsherolookup.feature_api.data_hero.HeroDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext

class HeroesListRepo(private val heroDataSource: HeroDataSource) {
    private val flow = MutableStateFlow<List<HeroListItemModel>?>(null)

    fun getFlow(): Flow<List<HeroListItemModel>?> {
        return flow
    }

    suspend fun loadHeroesList() {
        val res = coroutineScope {
            val heroesListDeferred = async {
                heroDataSource.getHeroesList()
            }

            val heroRatesDeferred = async {
                heroDataSource.getHeroRates()
            }

            val heroesList = heroesListDeferred.await()
            val heroRates = heroRatesDeferred.await()

            withContext(Dispatchers.Default) {
                heroesList.toHeroListItemModel(heroRates.competitiveMasterAndAbove)
            }
        }

        flow.emit(res)
    }
}