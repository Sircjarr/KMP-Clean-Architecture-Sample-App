import SwiftUI
import Shared

struct HeroesListScaffold: View {
    let viewState: HeroesListViewState
    
    @State private var search = ""
    @State var allClasses: [String: Bool]
    
    var heroMap: [String: [HeroListItem]] {
        let a = if search.isEmpty { viewState.list } else {
            viewState.list.filter {
                $0.name.lowercased().contains(search.lowercased())
            }
        }
        return a.filter {
            allClasses[$0.classification]!
        }.groupBy {
            $0.classification
        }
    }
    
    init(viewState: HeroesListViewState, allClasses: [String: Bool] = [:]) {
        self.viewState = viewState
        
        var m = [String: Bool]()
        self.viewState.list.forEach { e in
            m[e.classification] = true
        }
        self.allClasses = m
    }
    
    var body: some View {
        VStack {
            HeroesListTopBar(search: $search, allClasses: $allClasses)
            HeroesListContent(heroMap: heroMap, onHeroClicked: { hero in })
        }
    }
}
