package com.sircjarr.marvelrivalsherolookup.feature.heroeslist.di

import com.sircjarr.marvelrivalsherolookup.feature.heroeslist.domain.GetHeroesListUseCase
import com.sircjarr.marvelrivalsherolookup.feature.heroeslist.domain.HeroesListRepo
import com.sircjarr.marvelrivalsherolookup.feature.heroeslist.domain.LoadHeroesListUseCase
import com.sircjarr.marvelrivalsherolookup.feature.heroeslist.ui.HeroesListViewModel
import org.koin.core.module.dsl.createdAtStart
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val heroesListModule = module {
    singleOf(::HeroesListRepo) { createdAtStart() }
    singleOf(::GetHeroesListUseCase) { createdAtStart() }
    singleOf(::LoadHeroesListUseCase) { createdAtStart() }
    singleOf(::HeroesListViewModel) { createdAtStart() }
}