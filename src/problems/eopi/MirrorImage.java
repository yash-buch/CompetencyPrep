// check if root.left and root. right are mirror images of each other
package problems.eopi;

import org.junit.Test;

public class MirrorImage {

    public boolean areMirrorImages(Node root) {
        return checkSymmetric(root.left, root.right);
    }

    private boolean checkSymmetric(Node left, Node right) {
        if(left == null && right == null) {
            return true;
        } else if(left != null && right != null) {
            return left.val == right.val  && checkSymmetric(left.left, right.right)
                    && checkSymmetric(left.right, right.left);
        }
        return false;
    }

    class Node {
        Node left;
        Node right;
        int val;
        public Node (int val) {
            this.val = val;
        }
    }

    @Test
    public void test_areMirrorImages() {
        MirrorImage obj = new MirrorImage();
        Node root = new Node(1);
        root.left = new Node(1);
        root.right = new Node(1);
        root.left.left = new Node(1);
        root.left.right = new Node(1);
        root.right.right = new Node(1);
        root.right.left = new Node(1);
        boolean result = obj.areMirrorImages(root);
        assert (result);
    }
}
