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
            Text(hero.description_)
            
            // TODO: hero stats
            
            HStack {
                Text(String(winRate))
                Text(String(pickRate))
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
