import SwiftUI
import Shared

struct ContentView: View {
    
    @State var path = NavigationPath()
    
    var body: some View {
        NavigationStack(path: $path) {
            VStack {
                HeroesListScreen()
            }.navigationDestination(for: HeroDetailsArgs.self) { args in
                HeroDetailsScreen(args: args)
            }
        }
    }
}
