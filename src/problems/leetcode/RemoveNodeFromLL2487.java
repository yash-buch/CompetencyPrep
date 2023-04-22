package problems.leetcode;

import org.junit.Test;

import java.util.Stack;

public class RemoveNodeFromLL2487 {
    public ListNode removeNodes(ListNode head) {
        Stack<int[]> stack = new Stack<>();
        ListNode node = head;
        int idx = 0;
        while (node != null) {
            while (!stack.isEmpty()) {
                if (stack.peek()[1] < node.val) {
                    stack.pop();
                } else {
                    break;
                }
            }
            stack.push(new int[]{idx, node.val});
            idx++;
            node = node.next;
        }
        System.out.println(stack);
        Stack<int[]> rStack = new Stack<>();
        while (!stack.isEmpty()) {
            rStack.push(stack.pop());
        }
        System.out.println(rStack);
        ListNode left = new ListNode(-1, head);
        ListNode aux = left;
        ListNode mid = head;
        ListNode right = head.next;
        idx = 0;
        while (mid != null) {
            if (idx != rStack.peek()[0]) {
                left.next = right;
            } else {
                rStack.pop();
                left = mid;
            }
            mid = right;
            if (right != null)
                right = right.next;
            idx++;
        }
        return aux.next;
    }

    @Test
    public void test() {
        ListNode head = new ListNode(5,
                new ListNode(2,
                        new ListNode(13,
                                new ListNode(3,
                                        new ListNode(8)))));
        RemoveNodeFromLL2487 obj = new RemoveNodeFromLL2487();
        ListNode node = obj.removeNodes(head);
        while(node != null) {
            System.out.print(node.val+"->");
            node = node.next;
        }
        System.out.println();
    }


    public static class ListNode {
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
}
