//
//  StringExt.swift
//  iosApp
//
//  Created by Jinho Shin on 2023/11/28.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation

extension String {
    var localized: String {
            return NSLocalizedString(self, tableName: "Localizable", value: self, comment: "")
        }
}
