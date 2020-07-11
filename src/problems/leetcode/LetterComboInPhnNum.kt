package problems.leetcode

import org.junit.Test
import kotlin.test.assertEquals

class LetterComboInPhnNum {
    fun letterCombinations(digits: String): List<String>? {

        return eval(digits)
    }

    private fun eval(digits: String, idx: Int = 0): List<String> {
        if (idx >= digits.length) {
            return emptyList()
        }
        val listFromDigit: List<String> = getListForDigit(digits[idx])
        val listFromNextDigit: List<String> = eval(digits, idx+1)
        var finalList: List<String> = emptyList()
        for(char in listFromDigit) {
            for(str in listFromNextDigit) {
                finalList += char+str
            }
        }
        return if (finalList.isEmpty()) { listFromDigit }
        else finalList
    }

    private fun getListForDigit(digit: Char): List<String> {
        return when(digit) {
            '2' -> mutableListOf("a", "b", "c")
            '3' -> mutableListOf("d", "e", "f")
            '4' -> mutableListOf("g", "h", "i")
            '5' -> mutableListOf("j", "k", "l")
            '6' -> mutableListOf("m", "n", "o")
            '7' -> mutableListOf("p", "q", "r", "s")
            '8' -> mutableListOf("t", "u", "v")
            '9' -> mutableListOf("w", "x", "y", "z")
            else -> throw IllegalArgumentException()
        }
    }

    @Test
    fun test_letterCombinations() {
        val obj = LetterComboInPhnNum()
        val result = obj.letterCombinations("23")
        val actual = listOf("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf")
        assertEquals(result, actual)
    }

    @Test
    fun test_letterCombinationsReverse() {
        val obj = LetterComboInPhnNum()
        val result = obj.letterCombinations("32")
        val actual = listOf("da","db","dc","ea","eb","ec","fa","fb","fc")
        assertEquals(result, actual)
    }

    @Test
    fun test_letterCombinationsEmpty() {
        val obj = LetterComboInPhnNum()
        val result = obj.letterCombinations("")
        val actual = emptyList<String>()
        assertEquals(result, actual)
    }
}