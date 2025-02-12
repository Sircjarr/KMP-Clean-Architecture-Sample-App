import SwiftUI
import Shared

struct HeroesListScreen: View {
    @StateObject var viewModel = HeroesListIosViewModel(
        viewModel: KoinIosHelper.companion.heroesListViewModel
    )
    let onHeroClicked: (HeroListItem) -> Void
   
    var body: some View {
        HeroesListScreen2(
            viewState: viewModel.viewState,
            onHeroClicked: onHeroClicked,
            onRetryButtonClicked: viewModel.viewModel.loadHeroesList
        ).task {
            await viewModel.observeViewState()
        }
    }
}

struct HeroesListScreen2: View {
    let viewState: HeroesListViewState
    let onHeroClicked: (HeroListItem) -> Void
    let onRetryButtonClicked: () -> Void
    
    var body: some View {
        if (viewState.isLoading) {
            LoadingMessage("Fetching heroes list, please wait...").padding(8)
        } else if (viewState.err != nil) {
            ErrorMessageWithRetry(viewState.err!, onRetryButtonClicked: onRetryButtonClicked).padding(8)
        } else {
            HeroesListScaffold(
                viewState: viewState,
                onHeroClicked: onHeroClicked
            ).padding(8)
        }
    }
}

struct HeroesListScreen2_Previews: PreviewProvider {
    static var previews: some View {
        HeroesListScreen2(viewState: FakeData.companion.heroesListViewState, onHeroClicked: { hero in }, onRetryButtonClicked: {} )
    }
}
