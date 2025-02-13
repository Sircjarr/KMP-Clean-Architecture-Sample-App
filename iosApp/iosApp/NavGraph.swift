import SwiftUI
import Shared

struct NavGraph: View {
    
    @State var path = NavigationPath()
    
    var body: some View {
        NavigationStack(path: $path) {
            VStack {
                HeroesListScreen { hero in
                    path.append(HeroDetailsArgs(
                        name: hero.name,
                        url: hero.webUrl,
                        winRate: hero.winRate,
                        pickRate: hero.pickRate)
                    )
                }
            }.navigationDestination(for: HeroDetailsArgs.self) { args in
                HeroDetailsScreen(args: args)
            }
        }
    }
}
