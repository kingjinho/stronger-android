//
//  ScreenOnboardingExplanation.swift
//  iosApp
//
//  Created by Jinho Shin on 2023/12/06.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct ScreenOnboardingWelcome: View {
    var body: some View {
        VStack(alignment: HorizontalAlignment.leading) {
            Text("msg_onboarding_welcome_headline".localized)
                .font(.system(size: 48, weight: .medium))
            Spacer()
                .frame(height: 48)
            
            HStack {
                Image("ic_workout_onboarding")
                    .frame(width: 37, height: 37)

            }
            
            Spacer()
            
            Button {
                
            } label: {
                Text("msg_btn_start_working_out".localized)
                    .frame(maxWidth: .infinity, maxHeight: 48)
            }
            .background(Color(red: CGFloat((0xBB86FC & 0xFF0000) >> 16) / 255.0,
                        green: CGFloat((0xBB86FC & 0x00FF00) >> 8) / 255.0,
                        blue: CGFloat(0xBB86FC & 0x0000FF) / 255.0))
            .foregroundColor(.white)
            .buttonStyle(.borderless)
            .cornerRadius(8)
        }
    }
}

struct ScreenOnboardingWelcome_Previews: PreviewProvider {
    static var previews: some View {
        ScreenOnboardingWelcome()
    }
}
