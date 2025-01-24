package com.sircjarr.marvelrivalsherolookup.di

import com.sircjarr.marvelrivalsherolookup.api.data_hero.apiDataHeroModule
import com.sircjarr.marvelrivalsherolookup.feature.herodetails.di.heroDetailsModule
import com.sircjarr.marvelrivalsherolookup.feature.heroeslist.di.heroesListModule
import org.koin.dsl.module

val commonDiModule = module {
    includes(apiDataHeroModule, heroesListModule, heroDetailsModule)
}