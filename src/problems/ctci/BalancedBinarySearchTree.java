package problems.ctci;

import org.junit.Test;

public class BalancedBinarySearchTree {
    public Node buildBST(int[] in) {
        int mid = (in.length-1)/2;
        Node parent = new Node(in[mid]);
        Node left = new Node(-1);
        Node right = new Node(-1);
        parent.left = left;
        parent.right = right;
        divideNConquer(left, 0, mid-1, in);
        divideNConquer(right, mid+1, in.length-1, in);
        return parent;
    }

    private void divideNConquer(Node current, int l, int r, int[] arr) {
        if (l > r) return;
        int mid = (r + l)/2;
        current.val = arr[mid];
        Node left = new Node(-1);
        Node right = new Node(-1);
        current.left = left;
        current.right = right;
        divideNConquer(left, l, mid-1, arr);
        divideNConquer(right, mid+1, r, arr);
    }

    static class Node {
        int val;
        Node left;
        Node right;
        Node(int v) {
            val = v;
        }
    }

    @Test
    public void testBalancedBST() {
        BalancedBinarySearchTree obj = new BalancedBinarySearchTree();
        int[] in = new int[] {1,2,3,4,5,6,7,8,9,10};
        Node root = obj.buildBST(in);
        System.out.println("finish");
    }
}
