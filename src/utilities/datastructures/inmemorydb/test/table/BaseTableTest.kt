package utilities.datastructures.inmemorydb.test.table

import utilities.datastructures.inmemorydb.table.Table
import java.lang.reflect.Field

open class BaseTableTest {
    protected val createCmd = "CREATE Test name mysingleId:x.yz empId:000"

    protected fun decipherTableName(cmd: String): String = cmd.split(" ")[1]

    protected fun decipherPrimaryKey(cmd: String): String = cmd.split(" ")[2]
            .split(":")[0]

    protected fun decipherIthColumnName(cmd: String, i: Int = 1): String = cmd.split(" ")[i+2]
            .split(":")[0]

    protected fun decipherIthColumnDefVal(cmd: String, i: Int = 1): String = cmd.split(" ")[i+2]
            .split(":")[1]

    protected fun getPrivateField(privateField: String): Field {
        val field = Table::class.java.getDeclaredField(privateField)
        field.isAccessible = true
        return field
    }
}