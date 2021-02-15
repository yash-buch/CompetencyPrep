package problems.ctci;

import org.junit.Test;

public class FindSubTree {
    public boolean isSubTreeExists(Node one, Node two) {
        return DFS(one, two);
    }

    private boolean DFS(Node one, Node two) {
        if (one == null && two == null) return true;
        if (one == null || two == null) return false;
        boolean result = false;

        if(one.val == two.val) {
            result = DFS(one.left, two.left);
            if (result)
                result = DFS(one.right, two.right);
        }
        if (!result) {
            result = DFS(one.left, two);
            if (!result)
                result = DFS(one.right, two);
        }

        return result;
    }

    static class Node {
        int val;
        Node left;
        Node right;

        public Node(int v) {
            val = v;
        }
    }

    @Test
    public void testFindSubTree() {
        FindSubTree obj = new FindSubTree();

        Node one = new Node(1);
        Node two = new Node(3);
        Node three = new Node(2);
        Node four = new Node(-1);
        Node five = new Node(4);
        Node six = new Node(1);

        one.left = two;
        one.right = three;
        two.left = four;
        two.right = five;
        three.right = six;

        Node sub = new Node(3);
        sub.left = new Node(-1);
        sub.right = new Node(5);

        boolean result = obj.isSubTreeExists(one, sub);

        assert !result;
    }
}
