package problems.leetcode;

import org.junit.Before;
import org.junit.Test;

public class SwapNodes {

    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode result = head.next;
        ListNode a = null;
        ListNode b = head;
        ListNode c = head.next;
        while (c != null && b != null) {
            b.next = c.next;
            if (a != null) a.next = c;
            c.next = b;

            a = b;
            b = b.next;
            if (b != null) c = b.next;
        }
        return result;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    SwapNodes obj;
    @Before
    public void setup() {
        obj = new SwapNodes();
    }


    @Test
    public void test_swapPairsNullList() {
        assert null == obj.swapPairs(null);
    }

    @Test
    public void test_swapPairsSizeOneList() {
        ListNode expected = new ListNode(1);
        ListNode actual = obj.swapPairs(expected);
        assert expected.val == actual.val;
        assert actual.next == null;
    }

    @Test
    public void test_swapPairsEvenList() {
        ListNode input = new ListNode(1);
        input.next = new ListNode(2);
        input.next.next = new ListNode(3);
        input.next.next.next = new ListNode(4);

        ListNode expected = new ListNode(2);
        expected.next = new ListNode(1);
        expected.next.next = new ListNode(4);
        expected.next.next.next = new ListNode(3);

        ListNode actual = obj.swapPairs(input);

        assert elementWiseMatch(actual, expected);
    }

    @Test
    public void test_swapPairsOddList() {
        ListNode input = new ListNode(1);
        input.next = new ListNode(2);
        input.next.next = new ListNode(3);
        input.next.next.next = new ListNode(4);
        input.next.next.next.next = new ListNode(5);

        ListNode expected = new ListNode(2);
        expected.next = new ListNode(1);
        expected.next.next = new ListNode(4);
        expected.next.next.next = new ListNode(3);
        expected.next.next.next.next = new ListNode(5);

        ListNode actual = obj.swapPairs(input);

        assert elementWiseMatch(actual, expected);
    }

    private boolean elementWiseMatch(ListNode actual, ListNode expected) {
        while (actual != null && expected != null) {
            if (actual.val != expected.val) return false;
            actual = actual.next;
            expected = expected.next;
        }
        return true;
    }
}
