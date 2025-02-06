import SwiftUI
import Shared

@main
struct iOSApp: App {
    
    init() {
        KoinIosHelper.companion.startKoin()
    }
    
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}

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
