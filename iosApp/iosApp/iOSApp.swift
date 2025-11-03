import SwiftUI
import ComposeApp

@main
struct iOSApp: App {
    
    init() {
        KoinAppStarterKt.doInitKoin()
    }
    
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
