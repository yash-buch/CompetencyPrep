package problems.self;

import org.junit.Test;
import utilities.algorithms.BincMinMaxHeap;
import utilities.exceptions.HeapOverFlowException;

import java.util.Comparator;
import java.util.List;

public class BeakerFillingOrder {

    public int[] getBeakerFillingOrder(Node root, int n) {
        Comparator comparator = new NodeComparator();
        BincMinMaxHeap<Node> heap = new BincMinMaxHeap<>(Node.class, comparator, n);
        int[] result = new int[n];
        int index = 0;
        try {
            heap.insert(root);
            while(heap.size() > 0) {
                Node filledNode = heap.remove();
                result[index++] = filledNode.key;
                int waterFilled = filledNode.val;
                Node left = filledNode.left;
                if (left != null) {
                    left.val += waterFilled;
                    heap.insert(left);
                }
                Node right = filledNode.right;
                if (right != null) {
                    right.val += waterFilled;
                    heap.insert(right);
                }
            }
        } catch (HeapOverFlowException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void preOrder(Node node, List<Node> preOrderList) {
        if (node == null) return;
        preOrderList.add((node));
        preOrder(node.left, preOrderList);
        preOrder(node.right, preOrderList);
    }

    class Node {
        Node left;
        Node right;
        int val;
        int key;

        public Node(int key, int val) {
            this.val = val;
            this.key = key;
        }
    }

    class NodeComparator implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            if (o1.val - o2.val < 0) {
                return -1;
            } else if (o1.val - o2.val > 0) {
                return 1;
            }
            return 0;
        }
    }

    @Test
    public void test_getBeakerFillingOrder() {
        BeakerFillingOrder obj = new BeakerFillingOrder();
        Node root = new Node(0, 50);
        root.left = new Node(1, 13);
        root.left.left = new Node(3, 7);
        root.left.right = new Node(4, 3);
        root.left.right.left = new Node(6, 3);
        root.right = new Node(2, 15);
        root.right.right = new Node(5, 3);
        root.right.right.right = new Node(7, 7);
        root.right.right.right.left = new Node(8, 5);
        //int[] truth = new int[] {0,1,2,5,3,4,7,6,8};
        int[] truth = new int[] {0,1,2,4,5,6,3,7,8};
        int[] result = obj.getBeakerFillingOrder(root, 9);
        assert (truth.length == result.length);
        for(int i = 0; i < truth.length; i++) {
            assert (truth[i] == result[i]);
        }
    }
}
