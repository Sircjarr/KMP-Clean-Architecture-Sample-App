package com.sircjarr.marvelrivalsherolookup.ui.screens.heroeslist

import com.sircjarr.marvelrivalsherolookup.domain.usecase.GetHeroesListUseCase
import com.sircjarr.marvelrivalsherolookup.domain.usecase.LoadHeroesListUseCase
import kotlinx.coroutines.flow.MutableStateFlow

class HeroesListViewModel(
    private val getHeroesListUseCase: GetHeroesListUseCase,
    private val loadHeroesListUseCase: LoadHeroesListUseCase
) {
    val viewState = MutableStateFlow(HeroesListViewState())

    suspend fun init() {
        getHeroesListUseCase().collect { heroesList ->
            heroesList?.list?.let { list ->
                viewState.value = viewState.value.copy(
                    isLoading = false,
                    list = list
                )
            }
        }

        // Todo: handle errors
        loadHeroesListUseCase()
    }
}