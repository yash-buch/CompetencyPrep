//https://leetcode.com/problems/add-two-numbers/
package problems.leetcode;

public class ProblemAddTwoNumbers {
    public static void main(String[] args) {
        ProblemAddTwoNumbers obj = new ProblemAddTwoNumbers();
        ListNode l1 = obj.new ListNode(1);
        l1.next = obj.new ListNode(9);
        l1.next.next = obj.new ListNode(9);
        l1.next.next.next = obj.new ListNode(9);
        l1.next.next.next.next = obj.new ListNode(9);
        l1.next.next.next.next.next = obj.new ListNode(9);
        l1.next.next.next.next.next.next = obj.new ListNode(9);
        l1.next.next.next.next.next.next.next = obj.new ListNode(9);
        l1.next.next.next.next.next.next.next.next = obj.new ListNode(9);
        l1.next.next.next.next.next.next.next.next.next = obj.new ListNode(9);

        ListNode l2 = obj.new ListNode(9);
        //l2.next = obj.new ListNode(3);
        //l2.next.next = obj.new ListNode(3);//333

        ListNode l = obj.addTwoNumbers(l1, l2);
        while(l != null) {
            System.out.print(l.val);
            l = l.next;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null;
        ListNode ln = null;
        int carry = 0;
        while(l1 != null || l2 != null) {
            int sum = 0;
            if(l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if(l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            sum += carry;
            int res = sum%10;
            carry = sum/10;
            if(ln == null) {
                ln = new ListNode(res);
            } else {
                ln.next = new ListNode(res);
                ln = ln.next;
            }
            if(head == null) {
                head = ln;
            }

        }
        if(carry != 0) {
            ln.next = new ListNode(carry);
        }
        return head;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
