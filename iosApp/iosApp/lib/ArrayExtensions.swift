public extension Array where Element: Hashable {
    
    // Return a map of lists grouped by the key returned in keySelector()
    func groupBy<T>(_ keySelector: (Element) -> T) -> [T: [Element]] {
        return self.reduce([T: [Element]]()) { (dict, e) -> [T: [Element]] in
            var d = dict
            d[keySelector(e), default: []] += [e]
            return d
        }
    }
    
    // Return an array of distinct values
    func distinct() -> Array<Element> {
        return Array(Set(self))
    }
}
