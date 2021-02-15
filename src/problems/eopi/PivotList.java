package problems.eopi;

import org.junit.Test;

import java.util.ArrayList;

public class PivotList {
    // Assuming pivot is present in the list
    public Node pivotList(Node l, int pivot) {
        if ( l == null) return l;//check if this can be combined in the logic
        Node lt_start = null;
        Node lt_end = null;
        Node et_start = null;
        Node et_end = null;
        Node gt_start = null;
        Node gt_end = null;
        while (l != null) {
            Node node = l;
            l = l.next;
            if (node.val < pivot) {
                if (lt_start == null) {
                    lt_start = node;
                    lt_end = node;
                } else {
                    lt_end.next = node;
                    lt_end = lt_end.next;
                }
            } else if (node.val == pivot) {
                if (et_start == null) {
                    et_start = node;
                    et_end = node;
                } else {
                    et_end.next = node;
                    et_end = et_end.next;
                }
            } else {
                if (gt_start == null) {
                    gt_start = node;
                    gt_end = node;
                } else {
                    gt_end.next = node;
                    gt_end = gt_end.next;
                }
            }
        }

        lt_end.next = et_start;
        et_end.next = gt_start;
        return lt_start;
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
        PivotList.Node n = new PivotList.Node(3,
                new PivotList.Node(2,
                        new PivotList.Node(2,
                                new Node(11,
                                        new Node(7,
                                                new Node(5,
                                                        new Node(11,
                                                                new Node(7, null))))))));
        PivotList obj = new PivotList();
        PivotList.Node eval = obj.pivotList(n, 7);
        ArrayList<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(2);
        list.add(2);
        list.add(5);
        list.add(7);
        list.add(7);
        list.add(11);
        list.add(11);
        for(int i : list) {
            System.out.println(eval.val);
            assert i == eval.val;
            eval = eval.next;
        }
    }
}
