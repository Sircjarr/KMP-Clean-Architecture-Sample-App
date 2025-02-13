import SwiftUI

struct HeroImageHeader: View {
    let onGlobeIconClicked: () -> Void
    
    var body: some View {
        Image(systemName: "globe").foregroundColor(.gray)
    }
}