package utilities.datastructures.inmemorydb.table.bean

class Grid private constructor(private val projection: Set<String>) {
    var gridMap: HashMap<String, HashMap<String, String>> = hashMapOf()
    private var rows: Set<String> = mutableSetOf()
    private val rowIterator: RowIterator = RowIterator()

    fun setUp(field: String?, order: ORDER?): Grid {
        val map = when(order) {
            ORDER.DSC -> {
                gridMap[field ?: projection.elementAt(0)]!!.toList()
                        .sortedBy { (_, value) -> value }.reversed().toMap()
            }
            else -> {
                gridMap[field ?: projection.elementAt(0)]!!.toList()
                        .sortedBy { (_, value) -> value }.toMap()
            }
        }
        rows = map.keys
        return this
    }

    fun getData(row: String, col: String): String {
        return gridMap[col]?.get(row)?:"not found"
    }

    fun getRowIterator(): RowIterator = rowIterator

    inner class RowIterator: Iterator<Pair<String, ColumnIterator>> {
        var rowIdx: Int = 0
        override fun hasNext(): Boolean = rowIdx < rows.size

        override fun next(): Pair<String, ColumnIterator> {
            val row = rows.elementAt(rowIdx++)
            return Pair(row, ColumnIterator(row))
        }

    }

    inner class ColumnIterator constructor(private val row: String): Iterator<String> {
        var columnIdx: Int = 0
        override fun hasNext(): Boolean = columnIdx < projection.size

        override fun next(): String {
            val column = projection.elementAt(columnIdx++)
            return gridMap[column]?.get(row)?:"not found"
        }

    }

    class Builder(projection: Set<String>) {
        private val grid = Grid(projection)
        private var sortField: String? = null
        private var sortOrder: ORDER? = null

        fun build(): Grid {
            return grid.setUp(sortField, sortOrder)
        }

        fun addColumnToGrid(name: String, map: HashMap<String, String>): Builder {
            grid.gridMap[name] = map
            return this
        }

        fun sort(field: String, order: ORDER) {
            sortField = field
            sortOrder = order
        }
    }

    enum class ORDER {
        ASC, DSC
    }
}