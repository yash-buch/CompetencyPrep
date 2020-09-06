package utilities.datastructures.inmemorydb.table.bean

class Items: Iterable<Pair<String, String>> {
    private var itemSet:HashSet<Pair<String, String>> = HashSet()

    fun addItem(column:String, value: String) {
        itemSet.add(Pair(column, value))
    }

    fun addItem(column:String, value: Boolean) {
        itemSet.add(Pair(column, value.toString()))
    }

    fun addItem(column:String, value: Int) {
        itemSet.add(Pair(column, value.toString()))
    }

    fun addItem(column:String, value: Float) {
        itemSet.add(Pair(column, value.toString()))
    }

    fun addItem(column:String, value: Long) {
        itemSet.add(Pair(column, value.toString()))
    }

    fun getItems(): Set<Pair<String, String>> {
        return itemSet
    }

    override fun iterator(): Iterator<Pair<String, String>> {
        return itemSet.iterator()
    }
}