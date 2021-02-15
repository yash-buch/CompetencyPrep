package problems.eopi;

import org.junit.Test;

public class ListBasedIntSum {
    public Node sumLists(Node l1, Node l2) {
        Node l = null;
        Node t = null;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int a = l1 != null ? l1.val : 0;
            int b = l2 != null ? l2.val : 0;
            int sum = a + b + carry;
            int res = sum%10;
            carry = sum/10;
            Node n = new Node(res, null);
            if (t == null && l == null) {
                t = n;
                l = n;
            } else {
                t.next = n;
                t = n;
            }
            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;
        }

        if (carry != 0) {
            Node n = new Node(carry, null);
            t.next = n;
            t = n;
        }
        return l;
    }

    static class Node {
        int val;
        Node next;

        Node(){}
        Node(int v, Node n) {
            val = v;
            next = n;
        }
    }

    @Test
    public void test() {
        Node n = new Node(1, new Node(9, new Node(9, null)));
        Node n2 = new Node(1, new Node(9, new Node(9, null)));
        Node res = new Node(2, new Node(8, new Node(9, new Node(1, null))));
        ListBasedIntSum obj = new ListBasedIntSum();
        Node eval = obj.sumLists(n, n2);
        while(res != null && eval != null) {
            System.out.println(eval.val);
            assert res.val == eval.val;
            res = res.next;
            eval = eval.next;
        }
    }
}
