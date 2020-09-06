package utilities.datastructures.inmemorydb.table

import utilities.datastructures.inmemorydb.table.bean.Grid
import utilities.datastructures.inmemorydb.table.bean.Items
import utilities.datastructures.inmemorydb.table.bean.QueryCmd

class Table {
    lateinit var tableName: String
    private lateinit var primaryKey: String
    private var tableMap: HashMap<String, HashMap<String, String>> = hashMapOf()
    private var primaryColumnSet: HashSet<String> = HashSet()
    private var defaultValMap: HashMap<String, String> = hashMapOf()
    private var setOfFieldNames: HashSet<String> = HashSet()

    enum class OPERATOR(private val value: String) {
        EQUALS("="), GREATER(">"), SMALLER("<"), NOT("!");

        fun asString(): String {
            return value
        }
    }

    enum class CONDITION(private val value: String) {
        AND("and"), OR("or");

        fun asString(): String {
            return value
        }
    }

    /**
     * Base method to create a table
     * @param createCmd : it is of the form >
     * <CREATE>_<TableName>_<primaryKeyName>_<fieldName>:<defaultValue>_..
     */
    fun create(createCmd: String): Boolean {
        if (tableMap.isNotEmpty()) return false
        val commandTokens = createCmd.split(" ")
        val cmd = commandTokens[0]
        if (cmd == "CREATE") {
            tableName = commandTokens[1]
            tableMap = hashMapOf()
            primaryKey = commandTokens[2]
            tableMap[primaryKey] = HashMap()
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
        return true
    }

    /**
     * Method to insert data into the database
     * @param items : items that need to be inserted into the database
     */
    fun insert(items: Items): Boolean {
        var isPrimaryKeyProvided = false
        var primKeyValue = ""
        val insertedFields: HashSet<String> = HashSet()
        for (item in items) {
            if (!setOfFieldNames.contains(item.first)) return false
            if (primaryKey == item.first) {
                isPrimaryKeyProvided = true
                primKeyValue = item.second
            }
            insertedFields.add(item.first)
        }
        if (!isPrimaryKeyProvided) return false

        primaryColumnSet.add(primKeyValue)

        for (item in items) {
            val map = tableMap[item.first]
            map?.put(primKeyValue, item.second)
        }

        for (field in setOfFieldNames) {
            if (!insertedFields.contains(field)) {
                val map = tableMap[field]
                map?.put(primKeyValue, defaultValMap[field]?:"")
            }
        }

        return true
    }

    /**
     * Return the query result
     * @param queryCommand : {queryString = where fieldName:>:value and fieldName:<:value
     * projection = {set of field names}}
     */
    fun query(queryCommand: QueryCmd): Grid {
        val queryString = queryCommand.query
        val queryTokens = queryString.split(" ")
        var queryParams = queryTokens[1].split(":")
        var fieldName = queryParams[0]
        var pkSet = tableMap[fieldName]!!.filter(queryTokens[1])
        var op: String? = null
        for (i in 2 until queryTokens.size) {
            when (i % 2) {
                0 -> {
                    op = queryTokens[i]
                }
                1 -> {
                    val condition = queryTokens[i]
                    queryParams = queryTokens[i].split(":")
                    fieldName = queryParams[0]
                    when (op) {
                        CONDITION.AND.asString() ->
                            pkSet = pkSet.and(tableMap[fieldName]!!.filter(condition))
                        CONDITION.OR.asString() ->
                            pkSet = pkSet.or(tableMap[fieldName]!!.filter(condition))
                    }
                }
            }
        }

        return formulateGrid(pkSet, queryCommand.projection)
    }

    private fun <K> formulateGrid(pkSet: Set<K>, projection: Set<String>? = null): Grid {
        val gridBuilder = Grid.Builder(projection?:setOfFieldNames)
        val columnList = setOfFieldNames.toList().filter { projection?.contains(it)?:true }
        columnList.forEach { it ->
            gridBuilder.addColumnToGrid(it, tableMap[it]!!.filterKeys { pkSet.contains(it as K) }.copy()) }
        return gridBuilder.build()
    }

    /**
     * Returns the filtered output based on condition
     * @param condition : fieldName:>:value
     */
    private fun <K, V> HashMap<K, V>.filter(condition: String): Set<K> {
        val conditionTokens = condition.split(":")
        val op = conditionTokens[1]
        val value = conditionTokens[2]
        val map = when (op) {
            OPERATOR.EQUALS.asString() -> this.filterValues { it == value }
            OPERATOR.GREATER.asString() -> this.filterValues { it.toString() > value }
            OPERATOR.SMALLER.asString() -> this.filterValues { it.toString() < value }
            OPERATOR.NOT.asString() -> this.filterValues { it.toString() != value }
            else -> mapOf()
        }
        return map.keys
    }

    /**
     * Return a copy of the original map
     */
    private fun <K, V> Map<K, V>.copy(): HashMap<K, V> {
        return HashMap(this)
    }

    /**
     * Return the intersection of 2 sets
     * @param set : Set to perform intersection with
     */
    private fun <K> Set<K>.and(set: Set<K>): Set<K> {
        return this.intersect(set)
    }

    /**
     * Return the union of 2 sets
     * @param set : Set to perform union with
     */
    private fun <K> Set<K>.or(set: Set<K>): Set<K> {
        return this.union(set)
    }
}