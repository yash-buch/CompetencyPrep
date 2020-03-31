package problems.eopi;

import org.junit.Test;

public class LeastCommonAncestor {

    private final int FOUND_NONE = 0;
    private final int FOUND_ONE = 1;
    private final int FOUND_TWO = 2;
    private final int FOUND_BOTH = 3;

    private int ancestor = -1;

    public int leastCommonAncestor(Node root, int nodeOne, int nodeTwo) {
        dfs(root, nodeOne, nodeTwo);
        return ancestor;
    }

    private int dfs(Node node, int one, int two) {
        int ans = FOUND_NONE;
        if (node != null) {
            if (node.val == one) {
                ans += FOUND_ONE;
            }
            if (node.val == two) {
                ans += FOUND_TWO;
            }
            ans += dfs(node.left, one, two);
            if (ans != FOUND_BOTH) {
                ans += dfs(node.right, one, two);
            }
            if (ancestor == -1 && ans == FOUND_BOTH) {
                ancestor = node.val;
            }
        }
        return ans;
    }

    class Node {
        Node left;
        Node right;
        int val;

        public Node(int val) {
            this.val = val;
        }
    }

    @Test
    public void test_leastCommonAncestor() {
        LeastCommonAncestor obj = new LeastCommonAncestor();
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.left.left = new Node(5);
        root.left.right = new Node(6);
        root.right.left = new Node(7);
        int result = obj.leastCommonAncestor(root, 3, 7);
        System.out.print(result);
        assert (result == 3);
    }
}
