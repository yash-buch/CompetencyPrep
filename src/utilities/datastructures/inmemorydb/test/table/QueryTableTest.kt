package utilities.datastructures.inmemorydb.test.table

import org.junit.Test
import utilities.datastructures.inmemorydb.table.Table
import utilities.datastructures.inmemorydb.table.bean.Items
import utilities.datastructures.inmemorydb.table.bean.QueryCmd

class QueryTableTest: BaseTableTest() {

    @Test
    fun testQuery() {
        val table = Table()
        table.create(createCmd)
        var items = Items()
        items.addItem("mysingleId", "y.buch")
        items.addItem("name", "yash")
        items.addItem("empId", 125)
        table.insert(items)
        items = Items()
        items.addItem("mysingleId", "muskan.15")
        items.addItem("name", "muskan")
        items.addItem("empId", 321)
        table.insert(items)
        items = Items()
        items.addItem("mysingleId", "sid.b")
        items.addItem("name", "siddhi")
        items.addItem("empId", 435)
        table.insert(items)
        val grid = table.query(QueryCmd("where mysingleId:<:y.buch and empId:=:321"))
        while (grid.getRowIterator().hasNext()) {
            val pair = grid.getRowIterator().next()
            val row = pair.first
            val colItr = pair.second
            println("$row:")
            while (colItr.hasNext()) {
                print(colItr.next()+", ")
            }
            println()
        }
    }
}