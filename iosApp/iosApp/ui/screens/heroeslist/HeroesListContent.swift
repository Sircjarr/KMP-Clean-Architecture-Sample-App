import SwiftUI
import Shared
import Foundation

struct HeroesListContent: View {
    let heroMap: [String: [HeroListItem]]
    let onHeroClicked: (HeroListItem) -> Void
    
    var itemSize: Int {
        heroMap.values.joined().count
    }
    
    var body: some View {
        ScrollView {
            LazyVStack(alignment: .leading) {
                Section {
                    ForEach(Array(heroMap.keys).sorted(by: <), id: \.self) { classification in
                        Section(header: Text(classification).fontWeight(.bold)) {
                            ForEach(heroMap[classification]!, id: \.self) { hero in
                                HeroListItemRow(heroListItem: hero).onTapGesture {
                                    onHeroClicked(hero)
                                }
                            }
                        }
                    }
                } footer: {
                    HStack(alignment: .center) {
                        Spacer()
                        Text("\(itemSize)")
                        Spacer()
                    }.padding()
                }
            }
        }
    }
}

struct HeroListItemRow: View {
    let heroListItem: HeroListItem
    
    var body: some View {
        HStack {
            Text(heroListItem.name)
            Spacer()
            Color.gray.frame(width: 1 / UIScreen.main.scale)
            VStack {
                Text(String(NSString(format: "%.2f%%", heroListItem.winRate))).frame(width: 75)
                Text("Win rate").font(.system(size: 12, weight: .light))
            }
            Color.gray.frame(width: 1 / UIScreen.main.scale)
            VStack {
                Text(String(NSString(format: "%.2f%%", heroListItem.pickRate))).frame(width: 75)
                Text("Pick rate").font(.system(size: 12, weight: .light))
            }
        }
    }
}
