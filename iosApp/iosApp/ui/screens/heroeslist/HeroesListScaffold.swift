import SwiftUI
import Shared

struct HeroesListScaffold: View {
    let viewState: HeroesListViewState
    let allClasses: [String]
    
    @State var search = ""
    @State var blacklist = ""
    
    var heroMap: [String: [HeroListItem]] {
        let a = if search.isEmpty { viewState.list } else {
            viewState.list.filter {
                $0.name.lowercased().contains(search.lowercased())
            }
        }
        return a.filter {
            !blacklist.contains($0.classification)
        }.groupBy {
            $0.classification
        }
    }
    
    init(viewState: HeroesListViewState) {
        self.viewState = viewState
        self.allClasses = Array(Set(viewState.list.map { e in
            "\(e.classification)"
        }))
    }
    
    var body: some View {
        VStack {
            HeroesListTopBar(search: search, blacklist: blacklist, allClasses: allClasses)
            HeroesListContent(heroMap: heroMap, onHeroClicked: { hero in })
        }
    }
}