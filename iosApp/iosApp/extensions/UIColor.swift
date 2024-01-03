//
//  UIColor.swift
//  iosApp
//
//  Created by Jinho Shin on 1/3/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

extension Color {
    
    init(hexCode: String, alpha: CGFloat = 1.0) {
        var hexFormatted: String = hexCode.trimmingCharacters(in: CharacterSet.whitespacesAndNewlines).uppercased()
        
        if hexFormatted.hasPrefix("#") {
            hexFormatted = String(hexFormatted.dropFirst())
        }
        
        assert(hexFormatted.count == 6, "Invalid hex code used")
        
        var rbgValue: UInt64 = 0
        
        Scanner(string: hexFormatted).scanHexInt64(&rbgValue)
        
        self.init(red: CGFloat((rbgValue & 0xFF0000) >> 16) / 255.0,
                  green: CGFloat((rbgValue & 0x00FF00) >> 8) / 255.0,
                  blue: CGFloat(rbgValue & 0x0000FF) / 255.0)
    }
}
