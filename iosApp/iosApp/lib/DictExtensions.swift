public extension Dictionary where Key: Comparable {
    func toSortedDictArray() -> Array<(key: Key, value: Value)> {
        return sorted(by: { a, b -> Bool in
            return a.key < b.key
        })
    }
}