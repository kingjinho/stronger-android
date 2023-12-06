//
//  ScreenOnboarding.swift
//  iosApp
//
//  Created by Jinho Shin on 2023/12/06.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct ScreenOnboarding: View {
    var body: some View {
        TabView {
            ScreenOnboardingWelcome()
            ScreenOnboardingPermission()
        }
        .tabViewStyle(DefaultTabViewStyle())
        .indexViewStyle(PageIndexViewStyle(backgroundDisplayMode: .never))
        .padding(EdgeInsets(top: 48, leading: 20, bottom: 48, trailing: 20))
    }
}

struct ScreenOnboarding_Previews: PreviewProvider {
    static var previews: some View {
        ScreenOnboarding()
    }
}
