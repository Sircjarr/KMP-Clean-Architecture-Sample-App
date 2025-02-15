import SwiftUI

struct HeroImageHeader: View {
    let imageUrl: String
    let onGlobeIconClicked: () -> Void
    
    var body: some View {
        Rectangle().overlay {
            HStack {
                Spacer()
                VStack {
                    Image(systemName: "globe").foregroundColor(.gray).frame(width: 40, height: 40)
                    Spacer().frame(height: .infinity)
                }
            }
            AsyncImage(
                url: URL(string: imageUrl),
                content: { image in
                    image.resizable()
                        .aspectRatio(contentMode: .fit)
                        .frame(width: 70, height: 70)
                },
                placeholder: {
                    ProgressView()
                }
            )
        }
    }
}
