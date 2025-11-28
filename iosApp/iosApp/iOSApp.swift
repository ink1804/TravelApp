import SwiftUI
import ComposeApp
import FirebaseCore

@main
struct iOSApp: App {

    init() {
       FirebaseApp.configure()
       IosKoinInitKt.doInitKoin()
    }

    var body: some Scene {
        WindowGroup {
            ContentView().onOpenURL { url in
                HandleDeepLinkKt.doHandleDeepLink(url: url)
            }
        }
    }
}
