import SwiftUI
import Shared

struct HeroDetailsScaffold: View {
    let hero: HeroDetails
    let args: HeroDetailsArgs
    let onGlobeIconClicked: () -> Void
    
    var body: some View {
        VStack {
            HeroImageHeader(onGlobeIconClicked: onGlobeIconClicked)
            HeroNameHeader(name: hero.name, iconUrl: hero.iconUrl)
            HeroDetailsContent(hero: hero, winRate: args.winRate, pickRate: args.pickRate)
        }
    }
}

struct HeroDetailsContent: View {
    let hero: HeroDetails
    let winRate: Double
    let pickRate: Double
        
    var body: some View {
        VStack(alignment: .leading) {
            
            Text(hero.classification).padding(4).background(Color.gold).foregroundColor(.white)
            Spacer().frame(height: 16)
            Text(hero.description_)
            Spacer().frame(height: 16)
                        
            GroupBox("Base stats") {
                VStack(alignment: .leading) {
                    Divider()
                    ForEach(hero.stats, id: \.self) { stat in
                        HStack {
                            Text(stat.title)
                            Spacer()
                            Text(stat.value)
                        }
                    }
                }
            }
            
            Spacer().frame(height: 16)
            
            Grid(horizontalSpacing: 16) {
                GridRow {
                    RoundedRectangle(cornerRadius: 10)
                        .aspectRatio(1, contentMode: .fit)
                        .foregroundColor(Color(UIColor.secondarySystemBackground))
                        .overlay {
                            VStack {
                                Text(String(winRate) + "%").font(.system(size: 26)).foregroundColor(winRate.winRateColor)
                                Text("Win rate")
                            }
                        }
                    
                    RoundedRectangle(cornerRadius: 10)
                        .aspectRatio(1, contentMode: .fit)
                        .foregroundColor(Color(UIColor.secondarySystemBackground))
                        .overlay {
                            VStack {
                                Text(String(pickRate) + "%").font(.system(size: 26)).foregroundColor(pickRate.pickRateColor)
                                Text("Pick rate")
                            }
                        }
                }
            }
        }
    }
}

#Preview {
    HeroDetailsContent(
        hero: FakeData.companion.heroDetailsViewState.heroDetails!,
        winRate: 8.45,
        pickRate: 10.99
    )
}
