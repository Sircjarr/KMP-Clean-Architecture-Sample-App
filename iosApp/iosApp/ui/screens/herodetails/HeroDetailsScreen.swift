import SwiftUI
import Shared

struct HeroDetailsScreen: View {
    @StateObject var viewModel = HeroDetailsIosViewModel(viewModel: KoinIosHelper.companion.heroDetailsViewModel)
    
    let args: HeroDetailsArgs
    
    let name: String
    let url: String
    
    init(args: HeroDetailsArgs) {
        self.args = args
        name = args.name
        url = args.url
    }
    
    var body: some View {
        HeroDetailsScreen2(viewState: viewModel.viewState).task {
            viewModel.viewModel.doInitialize(heroName: name, webUrl: url)
            await viewModel.observeViewState()
        }
    }
}

struct HeroDetailsScreen2: View {
    let viewState: HeroDetailsViewState
    
    var body: some View {
        Text("TEMP")
    }
}


