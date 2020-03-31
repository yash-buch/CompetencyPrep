package problems.eopi;

import org.junit.Test;

public class ValidateBalancedBinTree {
    boolean imBalanced = false;

    public boolean isBinaryTreeBalanced(Node root) {
        processSubTree(root);
        return !imBalanced;
    }

    private int processSubTree(Node root) {
        int leftH = 0;
        int rightH = 0;
        int height = 0;
        if(root.left != null)
            leftH = processSubTree(root.left);
        if(!imBalanced && root.right != null)
            rightH = processSubTree(root.right);
        if(Math.abs(leftH-rightH) <= 1) {
            height = Math.max(leftH, rightH) + 1;
        } else {
            imBalanced = true;
        }
        return height;
    }

    class Node {
        Node left;
        Node right;
    }

    @Test
    public void test_isBinaryTreeBalancedTrue() {
        ValidateBalancedBinTree obj = new ValidateBalancedBinTree();
        Node root = new Node();
        root.left = new Node();
        root.right = new Node();
        boolean result = obj.isBinaryTreeBalanced(root);
        System.out.print(result);
        assert (result);
    }

    @Test
    public void test_isBinaryTreeBalancedFalse() {
        ValidateBalancedBinTree obj = new ValidateBalancedBinTree();
        Node root = new Node();
        root.left = new Node();
        root.left.left = new Node();
        boolean result = obj.isBinaryTreeBalanced(root);
        System.out.print(result);
        assert (!result);

        root.left = null;
        root.right = new Node();
        root.right.right = new Node();
        result = obj.isBinaryTreeBalanced(root);
        System.out.print(result);
        assert (!result);
    }
}
