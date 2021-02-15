package problems.leetcode;

import org.junit.Test;

public class BinTreeCams {
    private int numCameras = 0;

    public int minCameraCover(TreeNode root) {
        if (root.left == null && root.right == null) return 1;
        numCameras = 0;
        dfs(root);
        return numCameras;
    }

    private boolean dfs(TreeNode node) {
        if (node.left == null && node.right == null)
            return true;
        boolean shouldPlaceCam = false;
        if (node.left != null) {
            shouldPlaceCam = dfs(node.left) || shouldPlaceCam;
        }
        if (node.right != null) {
            shouldPlaceCam = dfs(node.right) || shouldPlaceCam;
        }
        if (shouldPlaceCam) numCameras++;
        return !shouldPlaceCam;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    @Test
    public void testBinTreeCams() {
        BinTreeCams obj = new BinTreeCams();
        TreeNode five = new TreeNode(0);
        TreeNode six = new TreeNode(0);
        TreeNode four = new TreeNode(0, five, six);
        TreeNode three = new TreeNode(0);
        TreeNode one = new TreeNode(0, three, four);
        TreeNode two = new TreeNode(0);
        TreeNode root = new TreeNode(0, one, two);

        int result = obj.minCameraCover(root);
        System.out.println(result);
        assert 3 == result;
    }
}
