import SwiftUI
import Shared

struct HeroListScreen: View {
    @StateObject var viewModel = HeroesListIosViewModel(viewModel: KoinIosHelper.companion.heroesListViewModel)
   
    var body: some View {
        HeroListScreen2(viewState: viewModel.viewState).task {
            await viewModel.observeViewState()
        }
    }
}

struct HeroListScreen2: View {
    let viewState: HeroesListViewState
    
    var body: some View {
        if (viewState.isLoading) {
            Text("Loading")
        } else if (viewState.err != nil) {
            Text("\(viewState.err!)")
        } else {
            Text("Finished Loading")
        }
    }
}
