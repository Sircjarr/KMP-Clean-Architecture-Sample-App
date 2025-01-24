package com.sircjarr.marvelrivalsherolookup.feature_api.data_hero

import org.koin.core.module.dsl.createdAtStart
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val apiDataHeroModule = module {
    singleOf(::HeroDataSourceKtor) bind HeroDataSource::class // Singleton
    singleOf(::HeroDataSourceKtor) { createdAtStart() }
}