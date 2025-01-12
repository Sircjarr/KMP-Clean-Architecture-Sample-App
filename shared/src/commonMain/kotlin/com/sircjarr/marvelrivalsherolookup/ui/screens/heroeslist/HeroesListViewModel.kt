package com.sircjarr.marvelrivalsherolookup.ui.screens.heroeslist

import com.sircjarr.marvelrivalsherolookup.domain.usecase.GetHeroesListUseCase
import com.sircjarr.marvelrivalsherolookup.domain.usecase.LoadHeroesListUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HeroesListViewModel(
    private val getHeroesListUseCase: GetHeroesListUseCase,
    private val loadHeroesListUseCase: LoadHeroesListUseCase
) {
    val viewState = MutableStateFlow(HeroesListViewState())

    fun init() {
        // Todo: scope to viewModelScope
        CoroutineScope(Dispatchers.IO).launch {
            getHeroesListUseCase().collect { heroesList ->
                heroesList?.list?.let { list ->
                    withContext(Dispatchers.Main) {
                        viewState.value = viewState.value.copy(
                            isLoading = false,
                            list = list
                        )
                    }
                }
            }
        }

        loadHeroesList()
    }

    fun loadHeroesList() {
        viewState.value = viewState.value.copy(
            isLoading = true
        )

        // Todo: handle errors, scope to viewmodel
        CoroutineScope(Dispatchers.IO).launch {
            loadHeroesListUseCase()
            println("LOADED LIST!")
        }
    }
}