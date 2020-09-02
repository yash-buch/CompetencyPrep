package utilities.datastructures.inmemorydb.table

import utilities.datastructures.inmemorydb.table.bean.Items

class Table {
    lateinit var tableName: String
    private lateinit var primaryKey: String
    private var tableMap: HashMap<String, HashMap<String, String>> = hashMapOf()
    private var primaryColumnSet: HashSet<String> = HashSet()
    private var defaultValMap: HashMap<String, String> = hashMapOf()
    private var setOfFieldNames: HashSet<String> = HashSet()


    /**
     * base method to create a table
     * @param createCmd : it is of the form >
     * <CREATE>_<TableName>_<primaryKeyName>_<fieldName>:<defaultValue>_..
     */
    fun create(createCmd: String) {
        val commandTokens = createCmd.split(" ")
        val cmd = commandTokens[0]
        if (cmd == "CREATE") {
            tableName = commandTokens[1]
            tableMap = hashMapOf()
            primaryKey = commandTokens[2]
            setOfFieldNames.add(primaryKey)
            for (i in 3 until commandTokens.size) {
                val fieldData = commandTokens[i].split(":")
                val fieldName = fieldData[0]
                val fieldDefaultValue = fieldData[1]
                setOfFieldNames.add(fieldName)
                defaultValMap[fieldName] = fieldDefaultValue
                tableMap[fieldName] = HashMap()
            }
        }
    }

    fun insert(items: Items): Boolean {
        var isPrimaryKeyProvided = false
        var primKeyValue = ""
        for (item in items) {
            if (!setOfFieldNames.contains(item.first)) return false
            if (primaryKey == item.first) {
                isPrimaryKeyProvided = true
                primKeyValue = item.second
            }
        }

        if (!isPrimaryKeyProvided) return false

        primaryColumnSet.add(primKeyValue)

        for (item in items) {
            val map = tableMap[item.first]
            map?.put(primKeyValue, item.second)
        }
        return true
    }
}