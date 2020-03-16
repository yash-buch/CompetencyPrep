//https://leetcode.com/problems/reverse-nodes-in-k-group/
package problems.leetcode;

public class ProblemReverseKGroup {

    public static void main(String[] args) {
        ProblemReverseKGroup obj = new ProblemReverseKGroup();
        ListNode head = obj.new ListNode(1);
        head.next = obj.new ListNode(2);
        head.next.next = obj.new ListNode(3);
        head.next.next.next = obj.new ListNode(4);
        head.next.next.next.next = obj.new ListNode(5);
        ListNode node = obj.reverseKGroup(head, 6);

        obj.printList(node);
    }


    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null) return null;
        int len = 0;

        ListNode t = head;
        while(t != null) {
            len++;
            t = t.next;
        }
        if(k > len) return head;
        int q = len/k;
        ListNode first;
        ListNode last;
        ListNode prevLast = null;
        ListNode ret = null;
        ListNode mid = head;
        ListNode right = mid.next;
        for(int i = 0; i < q; i++) {
            ListNode left = null;
            first = mid;
            for(int j = 0; j < k; j++) {
                mid.next = left;
                left = mid;
                mid = right;
                if(right != null) right = right.next;
            }
            last = left;
            if(prevLast != null) prevLast.next = last;
            first.next = mid;
            prevLast = first;
            if(ret == null) ret = left;

        }



        return ret;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    private void printList(ListNode node) {
        while(node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
    }
}
