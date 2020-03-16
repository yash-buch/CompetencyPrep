// https://leetcode.com/problems/merge-k-sorted-lists/
package problems.leetcode;

public class ProblemMergeKSortedLists {

    public static void main(String[] args) {
        ProblemMergeKSortedLists obj = new ProblemMergeKSortedLists();
        ListNode one = obj.new ListNode(1);
        one.next = obj.new ListNode(4);
        one.next.next = obj.new ListNode(5);

        ListNode two = obj.new ListNode(1);
        two.next = obj.new ListNode(3);
        two.next.next = obj.new ListNode(4);

        ListNode three = obj.new ListNode(2);
        three.next = obj.new ListNode(6);

        ListNode[] lists = new ListNode[] {null, two, three};

        ListNode node = obj.mergeKLists(lists);

        obj.printList(node);

    }

    private void printList(ListNode node) {
        while(node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length == 1)
            return lists[0];
        ListNode head = null;
        for (ListNode list : lists) {
            head = merge(head, list);
        }
        return head;
    }

    private ListNode merge(ListNode list1, ListNode list2) {
        ListNode head = null;
        ListNode node = null;
        while(list1 != null && list2 != null) {
            if(list1.val <= list2.val) {
                if(head == null) {
                    head = list1;
                    node = head;
                } else {
                    node.next = list1;
                    node = node.next;
                }
                list1 = list1.next;
            } else {
                if(head == null) {
                    head = list2;
                    node = head;
                } else {
                    node.next = list2;
                    node = node.next;
                }
                list2 = list2.next;
            }
        }

        while(list1 != null) {
            if(head == null) {
                head = list1;
                node = head;
            } else {
                node.next = list1;
                node = node.next;
            }
            list1 = list1.next;
        }

        while(list2 != null) {
            if(head == null) {
                head = list2;
                node = head;
            } else {
                node.next = list2;
                node = node.next;
            }
            list2 = list2.next;
        }

        return head;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
