package problems.self;

import org.junit.Test;

public class ReversingLinkedList {

    public Node reverseLinkedList(Node head) {
        if (head == null) return null;
        if (head.next == null) return head;
        Node l = null;
        Node m = head;
        Node r;
        while (m != null) {
            r = m.next;
            m.next = l;
            l = m;
            m = r;
        }
        return l;
    }

    static class Node {
        int val;
        Node next;
        public Node(int  v, Node n) {
            val = v;
            next = n;
        }
        public Node(int v) {
            val = v;
        }
    }

    @Test
    public void testReverseLinkedList() {
        ReversingLinkedList obj = new ReversingLinkedList();
        Node head = new Node(1, new Node(2, new Node(3, new Node(4))));
        Node reverse = obj.reverseLinkedList(head);
        while(reverse != null) {
            System.out.print(reverse.val+"->");
            reverse = reverse.next;
        }
        System.out.println("null");
    }
}
