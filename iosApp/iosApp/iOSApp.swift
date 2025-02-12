import SwiftUI
import Shared

@main
struct iOSApp: App {
    
    init() {
        KoinIosHelper.companion.startKoin()
    }
    
    var body: some Scene {
        WindowGroup {
            NavGraph()
        }
    }
}
