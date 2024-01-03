import SwiftUI

@main
struct iOSApp: App {
    
	var body: some Scene {
		WindowGroup {
            ZStack {
                Color(hexCode: "#1C1C1C")
                    .ignoresSafeArea(.all)
                
                ContentView()
            }
        }
	}
}
