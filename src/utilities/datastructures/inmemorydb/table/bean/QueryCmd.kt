package utilities.datastructures.inmemorydb.table.bean

data class QueryCmd(val query: String, val projection: Set<String>? = null)