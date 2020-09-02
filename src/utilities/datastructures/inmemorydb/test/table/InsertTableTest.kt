package utilities.datastructures.inmemorydb.test.table

import org.junit.Test
import utilities.datastructures.inmemorydb.table.Table
import utilities.datastructures.inmemorydb.table.bean.Items

class InsertTableTest: BaseTableTest() {

    @Test
    fun testInsert() {
        val table = Table()
        table.create(createCmd)
        val items = Items()
        items.addItem("mysingleId", "y.buch")
        items.addItem("name", "yash")
        items.addItem("empId", 125)
        assert(table.insert(items))
        assert((getPrivateField("primaryColumnSet").get(table) as HashSet<String>).size == 1)
        val tableMap = getPrivateField("tableMap").get(table) as HashMap<String, HashMap<String, String>>
        assert(tableMap["empId"]?.get("yash") == "125")
    }
}