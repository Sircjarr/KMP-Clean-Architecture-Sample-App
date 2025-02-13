import SwiftUI

extension Double {
    var winRateColor: Color {
        return switch self {
        case 50...100: Color.green
        case 30...50: Color.yellow
            default: Color.red
        }
    }
    
    var pickRateColor: Color {
        return switch self {
        case 12...100: Color.green
        case 5...12: Color.yellow
            default: Color.red
        }
    }
}
