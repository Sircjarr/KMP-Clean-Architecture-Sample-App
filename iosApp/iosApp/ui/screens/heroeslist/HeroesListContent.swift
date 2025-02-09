import SwiftUI
import Shared

struct HeroesListContent: View {
    let heroMap: [String: [HeroListItem]]
    let onHeroClicked: (HeroListItem) -> Void
    
    var itemSize: Int {
        heroMap.values.joined().count
    }
    
    var body: some View {
        ScrollView {
            LazyVStack(alignment: .leading) {
                ForEach(Array(heroMap.keys).sorted(by: <), id: \.self) { classification in
                    Section(header: Text(classification)) {
                        ForEach(heroMap[classification]!, id: \.self) { hero in
                            Text(hero.name)
                        }
                    }
                }
            }
        }
    }
}
