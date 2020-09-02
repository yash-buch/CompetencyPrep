package utilities.datastructures.inmemorydb.exceptions

import java.lang.Exception

class IncorrectParameterException(private val actual: String, private val expected: String)
    : Exception() {

    override fun toString(): String {
        return message?: "Incorrect parameter exception"
    }

    override val message: String?
        get() = "expected $expected but found $actual"
}