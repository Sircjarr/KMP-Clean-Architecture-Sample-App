package com.sircjarr.marvelrivalsherolookup.feature.heroeslist.domain

import com.sircjarr.marvelrivalsherolookup.feature_api.data_hero.HeroDataSource
import com.sircjarr.marvelrivalsherolookup.feature_api.data_hero.HeroListItemDto
import com.sircjarr.marvelrivalsherolookup.feature_api.data_hero.HeroRateDto
import com.sircjarr.marvelrivalsherolookup.feature_api.data_hero.HeroRatesDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.withContext

class HeroesListRepo(private val heroDataSource: HeroDataSource) {
    private val flow = MutableSharedFlow<List<HeroListItemModel>?>()

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

            return@coroutineScope withContext(Dispatchers.Default) {
                heroesList.toHeroListItemModel(heroRates.competitiveMasterAndAbove)
            }
        }

        flow.emit(res)
    }
}