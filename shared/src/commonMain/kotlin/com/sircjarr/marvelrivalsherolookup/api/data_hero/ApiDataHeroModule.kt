package com.sircjarr.marvelrivalsherolookup.api.data_hero

import org.koin.core.module.dsl.createdAtStart
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val apiDataHeroModule = module {
    singleOf(::HeroKtorApi) bind HeroDataSource::class // Singleton
    singleOf(::HeroKtorApi) { createdAtStart() }
}