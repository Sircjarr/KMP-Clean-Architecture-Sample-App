package com.sircjarr.marvelrivalsherolookup.kmp.di

import com.sircjarr.marvelrivalsherolookup.data.api.HeroDataSource
import com.sircjarr.marvelrivalsherolookup.domain.repo.HeroDetailsRepo
import com.sircjarr.marvelrivalsherolookup.domain.repo.HeroesListRepo
import com.sircjarr.marvelrivalsherolookup.domain.usecase.GetHeroDetailsUseCase
import com.sircjarr.marvelrivalsherolookup.domain.usecase.GetHeroesListUseCase
import com.sircjarr.marvelrivalsherolookup.domain.usecase.LoadHeroesListUseCase
import com.sircjarr.marvelrivalsherolookup.kmp.api.HeroApi
import com.sircjarr.marvelrivalsherolookup.ui.screens.herodetails.HeroDetailsViewModel
import com.sircjarr.marvelrivalsherolookup.ui.screens.heroeslist.HeroesListViewModel
import org.koin.core.module.dsl.createdAtStart
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val sharedModule = module {

    // Singletons
    singleOf(::HeroApi) bind HeroDataSource::class
    singleOf(::HeroApi) { createdAtStart() }
    singleOf(::HeroesListRepo) { createdAtStart() }
    singleOf(::HeroDetailsRepo) { createdAtStart() }
    singleOf(::GetHeroesListUseCase) { createdAtStart() }
    singleOf(::LoadHeroesListUseCase) { createdAtStart() }
    singleOf(::HeroesListViewModel) { createdAtStart() }

    // Create new objects on every injection
    factoryOf(::GetHeroDetailsUseCase)
    factoryOf(::HeroDetailsViewModel)
}