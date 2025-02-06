import SwiftUI
import Shared

struct ContentView: View {
    
    @State var path = NavigationPath()
    
    var body: some View {
        NavigationStack(path: $path) {
            VStack {
                HeroListScreen()
            }.navigationDestination(for: HeroDetailsArgs.self) { heroDetails in
                // SKIE plugin - no default case required
                let name = heroDetails.name
                let url = heroDetails.url
            }
        }
    }
}


struct HeroDetailsScreen: View {
    var body: some View {
        Text("FAKE")
    }
}

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

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
