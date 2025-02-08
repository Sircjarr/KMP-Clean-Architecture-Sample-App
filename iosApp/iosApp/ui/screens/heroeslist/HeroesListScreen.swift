import SwiftUI
import Shared

struct HeroesListScreen: View {
    @StateObject var viewModel = HeroesListIosViewModel(viewModel: KoinIosHelper.companion.heroesListViewModel)
   
    var body: some View {
        HeroesListScreen2(viewState: viewModel.viewState).task {
            await viewModel.observeViewState()
        }
    }
}

struct HeroesListScreen2: View {
    let viewState: HeroesListViewState
    
    var body: some View {
        if (viewState.isLoading) {
            Text("Loading")
        } else if (viewState.err != nil) {
            Text("\(viewState.err!)")
        } else {
            HeroesListContent()
        }
    }
}

struct HeroesListScreen2_Previews: PreviewProvider {
    static var previews: some View {
        HeroesListScreen2(viewState: FakeData.companion.heroesListViewState)
    }
}
