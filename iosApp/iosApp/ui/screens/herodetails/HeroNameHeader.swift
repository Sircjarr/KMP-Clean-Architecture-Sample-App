import SwiftUI

struct HeroNameHeader: View {
    let name: String
    let iconUrl: String
    
    var body: some View {
        HStack {
            Text(name).font(.title)
            Spacer()
            // TODO: image
        }
    }
}