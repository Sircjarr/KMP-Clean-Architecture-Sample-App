package com.sircjarr.marvelrivalsherolookup.di

import com.sircjarr.marvelrivalsherolookup.ui.screens.heroeslist.HeroesListAndroidViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val composeAppModule = module {
    viewModel { HeroesListAndroidViewModel(get()) }
    viewModelOf(::HeroesListAndroidViewModel)
}