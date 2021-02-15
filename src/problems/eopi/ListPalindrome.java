package problems.eopi;

import org.junit.Test;

public class ListPalindrome {
    // Assuming pivot is present in the list
    public boolean isPalindrome(Node node) {
        if (node == null) return false;
        int len = 0;
        Node t = node;
        while (t != null) {
            len++;
            t = t.next;
        }

        if (len == 1) return true;
        else if (len == 2) {
            return node.val == node.next.val;
        } else if (len == 3) {
            return node.val == node.next.next.val;
        }

        int m = len/2;

        Node l;
        Node c;
        Node r;
        Node mid = node;

        for (int i = 0; i < m; i++) {
            mid = mid.next;
        }
        l = mid.next;
        c = l.next;
        r = c.next;
        l.next = null;
        while (c != null) {
            c.next = l;
            l = c;
            c = r;
            r = r != null ? r.next : null;
        }
        mid.next = l;
        t = node;
        mid = mid.next;
        while (mid != null) {
            if (t.val != mid.val)
                return false;
            mid = mid.next;
            t = t.next;
        }
        return true;
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
        Node n = new Node(3,
                new Node(2,
                        new Node(2,
                                        new Node(2,
                                                new Node(2,
                                                        new Node(3, null))))));

        ListPalindrome obj = new ListPalindrome();
        boolean res = obj.isPalindrome(n);
        assert res;
    }
}
