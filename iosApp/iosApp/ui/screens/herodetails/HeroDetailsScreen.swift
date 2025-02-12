import SwiftUI
import Shared

struct HeroDetailsScreen: View {
    @StateObject var viewModel = HeroDetailsIosViewModel(
        viewModel: KoinIosHelper.companion.heroDetailsViewModel
    )
    
    let args: HeroDetailsArgs
    
    let name: String
    let url: String
    
    init(args: HeroDetailsArgs) {
        self.args = args
        name = args.name
        url = args.url
    }
    
    var body: some View {
        HeroDetailsScreen2(
            viewState: viewModel.viewState,
            onRetryButtonClicked: { viewModel.viewModel.loadHeroDetails(name: name) }
        ).task {
            viewModel.viewModel.doInitialize(heroName: name, webUrl: url)
            await viewModel.observeViewState()
        }
    }
}

struct HeroDetailsScreen2: View {
    let viewState: HeroDetailsViewState
    let onRetryButtonClicked: () -> Void
    
    var body: some View {
        if viewState.isLoading {
            LoadingMessage("Fetching hero details, please wait...").padding(8)
        } else if viewState.err != nil {
            ErrorMessageWithRetry(viewState.err!, onRetryButtonClicked: onRetryButtonClicked).padding(8)
        } else {
            Text("TODO").padding(8)
        }
    }
}

struct HeroesDetailsScreen2_Previews: PreviewProvider {
    static var previews: some View {
        HeroDetailsScreen2(viewState: FakeData.companion.heroDetailsViewState, onRetryButtonClicked: {})
    }
}
