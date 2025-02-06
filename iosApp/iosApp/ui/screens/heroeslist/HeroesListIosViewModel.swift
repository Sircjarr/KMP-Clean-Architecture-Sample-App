import SwiftUI
import Shared

class HeroesListIosViewModel: ObservableObject {
    let viewModel: HeroesListViewModel
    
    @Published
    var viewState: HeroesListViewState = HeroesListViewState(isLoading: true, err: nil, list: [])
    
    init(viewModel: HeroesListViewModel) {
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
