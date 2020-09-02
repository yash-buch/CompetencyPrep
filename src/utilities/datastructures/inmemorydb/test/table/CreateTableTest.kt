package utilities.datastructures.inmemorydb.test.table

import org.junit.Test
import utilities.datastructures.inmemorydb.table.Table

class CreateTableTest: BaseTableTest() {

    @Test
    fun testCreate() {
        val table = Table()
        table.create(createCmd)
        assert(table.tableName == decipherTableName(createCmd))
        assert(getPrivateField("primaryKey").get(table) == decipherPrimaryKey(createCmd))
    }

    @Test
    fun testColumnMaps() {
        val table = Table()
        table.create(createCmd)
        val tableMap = getPrivateField("tableMap").get(table) as HashMap<String, HashMap<String, String>>
        val firstColumnMap = tableMap[decipherIthColumnName(createCmd)]
        val defaultValMap = getPrivateField("defaultValMap").get(table) as HashMap<String, String>
        assert(firstColumnMap?.size == 0)
        assert(defaultValMap[decipherIthColumnName(createCmd)] == decipherIthColumnDefVal(createCmd))

        val secondColumnMap = tableMap[decipherIthColumnName(createCmd, 2)]
        assert(secondColumnMap?.size == 0)
        assert(defaultValMap[decipherIthColumnName(createCmd)] == decipherIthColumnDefVal(createCmd))
    }

    @Test
    fun testPrimaryKeyList() {
        val table = Table()
        table.create(createCmd)
        assert((getPrivateField("primaryColumnSet").get(table) as HashSet<String>).isEmpty())
    }
}