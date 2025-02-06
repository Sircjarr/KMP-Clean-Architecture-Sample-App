import SwiftUI
import Shared

struct ContentView: View {
    
    @State var path = NavigationPath(["0"])
    
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
        if (viewModel.viewState.isLoading) {
            Text("Loading")
        } else if (viewModel.viewState.err != nil) {
            Text("\(viewModel.viewState.err!)")
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
