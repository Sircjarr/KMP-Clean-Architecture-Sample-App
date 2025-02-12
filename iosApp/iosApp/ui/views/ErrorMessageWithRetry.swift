import SwiftUI

struct ErrorMessageWithRetry: View {
    let msg: String
    let onRetryButtonClicked: () -> Void
    
    init(_ msg: String, onRetryButtonClicked: @escaping () -> Void) {
        self.msg = msg
        self.onRetryButtonClicked = onRetryButtonClicked
    }
    
    var body: some View {
        VStack {
            Text(msg).multilineTextAlignment(.center)
            Button(action: onRetryButtonClicked, label: { Text("Retry") })
        }
    }
}
