package problems.leetcode

import org.junit.Test
import kotlin.test.assertEquals

class GenerateParenthesis {

    fun generateParenthesis(n: Int): List<String> {
        return if (n == 0) listOf("") else eval(n)
    }

    private fun eval(n: Int, nextChoice: String = "(", oc: Int = 1, cc: Int = 0): List<String> {
        var openList: List<String> = emptyList()
        var closedList: List<String> = emptyList()
        if (oc < n) {
            openList = eval(n, "(", oc+1, cc)
            openList = appendToList(nextChoice, openList)
        }
        if (cc < n && cc < oc) {
            closedList = eval(n, ")", oc, cc+1)
            closedList = appendToList(nextChoice, closedList)
        }
        var result: List<String> = mutableListOf()
        openList.forEach { result = result + it }
        closedList.forEach { result = result + it }
        return if (result.isEmpty()) listOf(nextChoice) else result
    }

    private fun appendToList(token: String, list: List<String>): List<String> {
        var resultList: List<String> = mutableListOf()
        list.forEach { resultList = resultList + (token + it) }
        return resultList
    }

    @Test
    fun test_generateParenthesis() {
        val obj = GenerateParenthesis()
        val expected = obj.generateParenthesis(3)
        val actual = listOf("((()))",
                "(()())",
                "(())()",
                "()(())",
                "()()()")
        //val actual = listOf("()")
        assertEquals(expected, actual)
    }

    @Test
    fun test_appendToList() {
        val result = appendToList("!", mutableListOf("a", "b"))
        val actual = listOf("!a", "!b")
        assertEquals(actual, result)
    }
}