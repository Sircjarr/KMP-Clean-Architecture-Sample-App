import SwiftUI

struct HeroesListTopBar: View {
    @Binding var search: String
    @Binding var allClasses: [String: Bool]
    
    init(search: Binding<String>, allClasses: Binding<[String: Bool]>) {
        self._search = search
        self._allClasses = allClasses
    }
    
    var body: some View {
        VStack {
            HStack {
                Image(systemName: "magnifyingglass")
                
                TextField(text: $search) {
                    Text("Search")
                }
                        
                Menu {
                    ForEach(Array(allClasses.keys), id: \.self) { e in
                        Button {
                            allClasses[e] = !allClasses[e]!
                        } label: {
                            CheckBoxItem(e, checkedDict: $allClasses)
                        }
                    }
                } label: {
                    Image(systemName: "line.3.horizontal.decrease").foregroundColor(Color.marvelRed)
                }.menuActionDismissBehavior(.disabled)
                
            }.padding(8)
        }
    }
}
