package problems.leetcode

import org.junit.Test

class RemoveNthNode {
    fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
        var trail: ListNode? = null
        var lead: ListNode? = head
        var hops = 0
        while (lead != null) {
            if (hops >= n) {
                trail = trail?.next?:head
            }
            lead = lead.next
            hops++
        }
        trail?.next = trail?.next?.next
        return if (trail == null) head?.next else head
    }

    class ListNode(var value: Int) {
        var next: ListNode? = null
    }

    @Test
    fun test_removeNtFromEnd() {
        val obj = RemoveNthNode()
        val head: ListNode? = ListNode(1)
                .apply {
                    next = ListNode(2)
                            .apply {
                                next = ListNode(3)
                                        .apply {
                                            next = ListNode(4)
                                                    .apply { next = ListNode(5) }
                                        }
                            }
                }
        var result: ListNode? = obj.removeNthFromEnd(head, 2)
        var actual: ListNode? = ListNode(1)
                .apply {
                    next = ListNode(2)
                            .apply {
                                next = ListNode(3)
                                        .apply { next = ListNode(5) }
                            }
                }
        while (actual != null) {
            assert(actual.compare(result))
            actual = actual.next
            result = result?.next
        }
    }

    fun ListNode.compare(node: ListNode?): Boolean {
        if (value != node?.value) {
            return false

        }
        return true
    }
}