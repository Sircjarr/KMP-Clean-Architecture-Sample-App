import SwiftUI

struct HeroesListTopBar: View {
    @Binding var search: String
    @Binding var blacklist: String
    let allClasses: [String]
    
    @State var isExpanded = false
    
    var body: some View {
        VStack {
            HStack {
                Image(systemName: "magnifyingglass")
                
                TextField(text: $search) {
                    Text("Search")
                }
                
                Image(systemName: "line.3.horizontal.decrease")
            }.padding()
        }
    }
}
