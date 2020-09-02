package problems.leetcode

import org.junit.Test
import kotlin.test.assertEquals

class FourSum {
    fun fourSum(nums: IntArray, target: Int): List<List<Int>> {
        val res = HashSet<List<Int>>()
        nums.sort()
        for (i in 0..nums.lastIndex - 3) {
            for (j in nums.lastIndex downTo i + 3) {
                var low = i + 1
                var high = j - 1
                val tmp = target - nums[i] - nums[j]
                while (low < high) {
                    val sum = nums[low] + nums[high]
                    when {
                        sum == tmp -> {
                            res.add(listOf(nums[i], nums[low], nums[high], nums[j]))
                            low++
                            high--
                        }
                        sum < tmp -> {
                            low++
                        }
                        else -> {
                            high--
                        }
                    }
                }
            }

        }
        return res.toList()
    }

    @Test
    fun test_fourSum() {
        val obj = FourSum()
        val result = obj.fourSum(intArrayOf(1,2,3,4), 10)
        val actual = listOf(listOf(1,2,3,4))
        assertEquals(actual, result)
    }

}