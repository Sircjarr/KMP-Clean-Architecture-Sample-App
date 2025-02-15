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
    let winRate: Double
    let pickRate: Double
    
    init(heroListItem: HeroListItem) {
        self.heroListItem = heroListItem
        winRate = heroListItem.winRate
        pickRate = heroListItem.pickRate
    }
    
    var body: some View {
        HStack {
            AsyncImage(
                url: URL(string: heroListItem.imageUrl),
                content: { image in
                    image.resizable()
                        .aspectRatio(contentMode: .fit)
                        .frame(width: 70, height: 70)
                },
                placeholder: {
                    ProgressView()
                }
            )
            Text(heroListItem.name).font(.system(size: 14))
            Spacer()
            Color.gray.frame(width: 1 / UIScreen.main.scale)
            VStack {
                Text(String(NSString(format: "%.2f%%", winRate))).frame(width: 75).foregroundColor(winRate.winRateColor)
                Text("Win rate").font(.system(size: 12, weight: .light))
            }
            Color.gray.frame(width: 1 / UIScreen.main.scale)
            VStack {
                Text(String(NSString(format: "%.2f%%", pickRate))).frame(width: 75).foregroundColor(pickRate.pickRateColor)
                Text("Pick rate").font(.system(size: 12, weight: .light))
            }
        }
    }
}
