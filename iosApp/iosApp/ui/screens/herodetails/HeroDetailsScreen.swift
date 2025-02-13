import SwiftUI
import Shared

struct HeroDetailsScreen: View {
    @StateObject var viewModel = HeroDetailsIosViewModel(
        viewModel: KoinIosHelper.companion.heroDetailsViewModel
    )
    
    let args: HeroDetailsArgs
   
    var body: some View {
        HeroDetailsScreen2(
            viewState: FakeData.companion.heroDetailsViewState,
            onRetryButtonClicked: { viewModel.viewModel.loadHeroDetails(name: args.name) },
            args: args,
            onGlobeIconClicked: viewModel.viewModel.launchHeroUrlInExternalBrowser
        ).task {
            // viewModel.viewModel.doInitialize(heroName: name, webUrl: url)
            await viewModel.observeViewState()
        }
    }
}

struct HeroDetailsScreen2: View {
    let viewState: HeroDetailsViewState
    let onRetryButtonClicked: () -> Void
    let args: HeroDetailsArgs
    let onGlobeIconClicked: () -> Void
    
    var body: some View {
        if viewState.isLoading {
            LoadingMessage("Fetching hero details, please wait...").padding(8)
        } else if viewState.err != nil {
            ErrorMessageWithRetry(viewState.err!, onRetryButtonClicked: onRetryButtonClicked).padding(8)
        } else {
            HeroDetailsScaffold(
                hero: viewState.heroDetails!,
                args: args,
                onGlobeIconClicked: onGlobeIconClicked
            ).padding(8)
        }
    }
}

struct HeroesDetailsScreen2_Previews: PreviewProvider {
    static var previews: some View {
        HeroDetailsScreen2(
            viewState: FakeData.companion.heroDetailsViewState,
            onRetryButtonClicked: {},
            args: HeroDetailsArgs(name: "", url: "", winRate: 51.09, pickRate: 13.41),
            onGlobeIconClicked: {}
        )
    }
}
