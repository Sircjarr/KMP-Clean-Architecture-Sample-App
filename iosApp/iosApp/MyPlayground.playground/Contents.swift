import ArrayExtensions
import DictExtensions

let search = "2"
let blacklist = ""
let list = ["1", "2", "3", "2", "2"]

let x = if true { 8 } else { 3 }
x

let n = if search.isEmpty { list } else {
    list.filter { e in
        e.contains(search)
    }
}.filter {
    true
}

let m = n.filter { e in
    !blacklist.contains(e)
}

let o = m.reduce([String: Int]()) { (dict, e) -> [String: Int] in
    var d = dict
    d[e] = Int(e)
    return d
    // x[y] = Int(y)
}

let p = m.groupBy { e in
    Int(e)!
}
print(p)

let q = p.sorted(by: { a, b -> Bool in
    return a.key < b.key
})
print(q)

let r = p.toSortedDictArray()
print(r)

public extension Array {
    func groupBy<T>(_ keySelector: (Element) -> T) -> [T: [Element]] {
        return self.reduce([T: [Element]]()) { (dict, e) -> [T: [Element]] in
            var d = dict
            d[keySelector(e), default: []] += [e]
            return d
        }
    }
}

public extension Dictionary where Key: Comparable {
    func toSortedDictArray() -> Array<(key: Key, value: Value)> {
        return sorted(by: { a, b -> Bool in
            return a.key < b.key
        })
    }
}
