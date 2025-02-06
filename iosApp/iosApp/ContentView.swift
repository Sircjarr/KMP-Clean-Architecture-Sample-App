import SwiftUI
import Shared

struct ContentView: View {
    
    @State var path = NavigationPath()
    
    var body: some View {
        NavigationStack(path: $path) {
            VStack {
                HeroListScreen()
            }.navigationDestination(for: HeroDetailsArgs.self) { args in
                HeroDetailsScreen(args: args)
            }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
