package com.sircjarr.marvelrivalsherolookup.kmp.di

import com.sircjarr.marvelrivalsherolookup.core.data.api.HeroDataSource
import com.sircjarr.marvelrivalsherolookup.core.domain.repo.HeroDetailsRepo
import com.sircjarr.marvelrivalsherolookup.core.domain.repo.HeroesListRepo
import com.sircjarr.marvelrivalsherolookup.core.domain.usecase.GetHeroDetailsUseCase
import com.sircjarr.marvelrivalsherolookup.core.domain.usecase.GetHeroesListUseCase
import com.sircjarr.marvelrivalsherolookup.core.domain.usecase.LoadHeroDetailsUseCase
import com.sircjarr.marvelrivalsherolookup.core.domain.usecase.LoadHeroesListUseCase
import com.sircjarr.marvelrivalsherolookup.kmp.api.HeroApi
import com.sircjarr.marvelrivalsherolookup.core.ui.screens.herodetails.HeroDetailsViewModel
import com.sircjarr.marvelrivalsherolookup.core.ui.screens.heroeslist.HeroesListViewModel
import org.koin.core.module.dsl.createdAtStart
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val sharedModule = module {

    singleOf(::HeroApi) bind HeroDataSource::class // Singleton
    singleOf(::HeroApi) { createdAtStart() }

    // Heroes List screen
    singleOf(::HeroesListRepo) { createdAtStart() }
    singleOf(::GetHeroesListUseCase) { createdAtStart() }
    singleOf(::LoadHeroesListUseCase) { createdAtStart() }
    singleOf(::HeroesListViewModel) { createdAtStart() }

    // Hero Details screen
    singleOf(::HeroDetailsRepo) { createdAtStart() }
    factoryOf(::GetHeroDetailsUseCase) // Inject on-demand
    factoryOf(::LoadHeroDetailsUseCase)
    factoryOf(::HeroDetailsViewModel)
}