package utilities.datastructures.inmemorydb.table.bean

class Grid private constructor(private val projection: Set<String>) {
    var gridMap: HashMap<String, HashMap<String, String>> = hashMapOf()
    private var rows: MutableSet<String> = mutableSetOf()
    private val rowIterator: RowIterator = RowIterator()

    fun setUp(): Grid {
        rows = gridMap[projection.elementAt(0)]!!.keys
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
        fun build(): Grid {
            return grid.setUp()
        }

        fun addColumnToGrid(name: String, map: HashMap<String, String>): Builder {
            grid.gridMap[name] = map
            return this
        }
    }
}