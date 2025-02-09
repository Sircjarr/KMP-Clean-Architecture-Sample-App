import SwiftUI

struct HeroesListTopBar: View {
    @State var search: String
    @State var blacklist: String
    let allClasses: [String]
    
    @State var isExpanded = false
    
    var body: some View {
        VStack {
            HStack {
                TextField(text: $search) {
                    Text("Search")
                }
                
                Image(systemName: "line.3.horizontal.decrease")
            }
        }
    }
}