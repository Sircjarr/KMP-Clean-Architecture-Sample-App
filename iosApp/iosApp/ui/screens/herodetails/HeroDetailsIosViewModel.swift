import SwiftUI
import Shared

class HeroDetailsIosViewModel: ObservableObject {
    let viewModel: HeroDetailsViewModel
    
    @Published
    var viewState: HeroDetailsViewState = HeroDetailsViewState(isLoading: true, err: nil, heroDetails: nil)
    
    init(viewModel: HeroDetailsViewModel) {
        self.viewModel = viewModel
    }
    
    @MainActor
    func observeViewState() async {
        
        // SKIE plugin - convert Flow to AsyncSequence
        for await vs in viewModel.viewState {
            viewState = vs
        }
    }
}
