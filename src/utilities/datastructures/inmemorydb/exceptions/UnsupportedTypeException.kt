package utilities.datastructures.inmemorydb.exceptions

import java.lang.Exception

class UnsupportedTypeException(private val param: String): Exception() {
    override fun toString(): String {
        return message?: "param not supported"
    }

    override val message: String?
        get() = "$param not supported"
}