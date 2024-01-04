import SwiftUI

struct ContentView: View {
    var body: some View {
        ScreenOnboarding()
            
    }
}

#Preview {
    ContentView()
        .environment(\.locale, .init(identifier: "ko_KR"))
}
