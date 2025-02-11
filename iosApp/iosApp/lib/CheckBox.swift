import SwiftUI

// For use cases where the count of CheckBoxes are known
struct CheckBox: View {
    var label: String
    @Binding var checked: Bool
    
    init(_ label: String, checked: Binding<Bool>) {
        self.label = label
        self._checked = checked
    }
    
    var body: some View {
        HStack {
            Image(systemName: (checked ? "checkmark.square" : "square"))
                .foregroundColor(.red)
            Text(label).padding(.leading)
        }.onTapGesture {
            checked.toggle()
        }
    }
}

// Easier handling of arbitrary number of CheckBoxes in a list
struct CheckBoxItem: View {
    var label: String
    @Binding var checkedDict: [String: Bool]
    
    init(_ label: String, checkedDict: Binding<[String: Bool]>) {
        self.label = label
        self._checkedDict = checkedDict
    }
    
    var body: some View {
        HStack {
            Image(systemName: (checkedDict[label]! ? "checkmark.square" : "square"))
                .foregroundColor(.red)
            Text(label).padding(.leading)
        }.onTapGesture {
            checkedDict[label] = !checkedDict[label]!
        }
    }
}

@available(iOS 17.0, *)
#Preview {
    @Previewable @State var checkedDict: [String: Bool] = [
        "I am checked": true,
        "I am unchecked": false
    ]

    VStack(alignment: .leading) {
        CheckBox("I am checked", checked: .constant(true))
        CheckBox("I am unchecked", checked: .constant(false))
        
        CheckBoxItem("I am checked", checkedDict: $checkedDict)
        CheckBoxItem("I am unchecked", checkedDict: $checkedDict)
    }
}
