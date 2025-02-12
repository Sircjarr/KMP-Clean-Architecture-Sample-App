import SwiftUI

struct LoadingMessage: View {
    let msg: String
    
    init(_ msg: String) {
        self.msg = msg
    }
    
    var body: some View {
        VStack {
            ProgressView()
            Text(msg).multilineTextAlignment(.center)
        }
    }
}
