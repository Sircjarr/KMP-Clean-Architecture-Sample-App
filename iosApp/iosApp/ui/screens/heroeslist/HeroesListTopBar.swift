import SwiftUI

struct HeroesListTopBar: View {
    @State var search: String
    
    @State var isExpanded = false
    
    var body: some View {
        Text("Top Bar")
    }
}