public extension Array {
    func groupBy<T>(_ keySelector: (Element) -> T) -> [T: [Element]] {
        return self.reduce([T: [Element]]()) { (dict, e) -> [T: [Element]] in
            var d = dict
            d[keySelector(e), default: []] += [e]
            return d
        }
    }
}