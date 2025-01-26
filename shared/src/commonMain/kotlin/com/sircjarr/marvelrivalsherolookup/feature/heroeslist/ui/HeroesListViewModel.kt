package com.sircjarr.marvelrivalsherolookup.feature.heroeslist.ui

import com.sircjarr.marvelrivalsherolookup.feature.heroeslist.domain.GetHeroesListUseCase
import com.sircjarr.marvelrivalsherolookup.feature.heroeslist.domain.LoadHeroesListUseCase
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
    private val scope = CoroutineScope(Dispatchers.IO)

    init {
        scope.launch {
            getHeroesListUseCase().collect { heroesList ->
                heroesList?.let { list ->
                    withContext(Dispatchers.Main) {
                        viewState.value = viewState.value.copy(
                            isLoading = false,
                            err = null,
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

        scope.launch {
            loadHeroesListUseCase().onFailure { ex ->
                withContext(Dispatchers.Main) {
                    viewState.value = viewState.value.copy(
                        isLoading = false,
                        err = "Failed loading heroes list. Check your connection and try again."
                    )
                }
            }
        }
    }
}