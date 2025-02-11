public extension Dictionary where Key: Comparable {
    
    // Ordered map
    // Return an array of key-value tuples sorted by the natural ordering of the keys
    func toSortedDictArray() -> Array<(key: Key, value: Value)> {
        return sorted(by: { a, b -> Bool in
            return a.key < b.key
        })
    }
}