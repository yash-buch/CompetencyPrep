package utilities.datastructures.inmemorydb.table.bean

class Items: Iterable<Pair<String, String>> {
    private var itemList:List<Pair<String, String>> = mutableListOf()

    fun addItem(column:String, value: String) {
        itemList = itemList + Pair(column, value)
    }

    fun addItem(column:String, value: Boolean) {
        itemList = itemList + Pair(column, value.toString())
    }

    fun addItem(column:String, value: Int) {
        itemList = itemList + Pair(column, value.toString())
    }

    fun addItem(column:String, value: Float) {
        itemList = itemList + Pair(column, value.toString())
    }

    fun addItem(column:String, value: Long) {
        itemList = itemList + Pair(column, value.toString())
    }

    fun getItems(): List<Pair<String, String>> {
        return itemList
    }

    override fun iterator(): Iterator<Pair<String, String>> {
        return itemList.iterator()
    }
}