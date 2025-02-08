import SwiftUI

struct HeroesListContent: View {
    
    @State var search = ""
    
    var body: some View {
        HeroesListTopBar(search: search)
    }
}