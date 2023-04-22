package problems.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class AllNodesDistanceK863 {
    List<Integer> result;

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        result = new ArrayList<>();
        dfs(root, 0, target.val, k);
        return result;
    }

    private int dfs(TreeNode node, int dist, int target, int k) {
        //already found
        if (dist > 0) {
            if (dist == k) {
                result.add(node.val);
                return 0;
            }
            if (node.left != null) {
                dfs(node.left, dist + 1, target, k);
            }
            if (node.right != null) {
                dfs(node.right, dist + 1, target, k);
            }
            return 0;
        } else {
            //not found and cur is not target
            if (target != node.val) {
                int ret = 0;
                if (node.left != null) {
                    ret = dfs(node.left, dist, target, k);
                }
                if (ret == k) {
                    result.add(node.val);
                    return ret+1;
                }
                if (node.right != null) {
                    ret = dfs(node.right, ret == 0 ? 0 : ret + 1, target, k);
                }
                if (ret == k) {
                    result.add(node.val);
                    return ret+1;
                }
                if (node.left != null && ret != 0) {
                    ret = dfs(node.left, ret + 1, target, k);
                }
                if (ret == k) {
                    result.add(node.val);
                    return ret+1;
                }
                return ret == 0 ? 0 : ret + 1;
            }
            //not found and cur is target
            else {
                if (node.left != null) {
                    dfs(node.left, 1, target, k);
                }
                if (node.right != null) {
                    dfs(node.right, 1, target, k);
                }
                return 1;
            }
        }
    }

    @Test
    public void test() {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(1);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(2);
        AllNodesDistanceK863 obj = new AllNodesDistanceK863();
        List<Integer> ans = obj.distanceK(root, root.left.right, 1);
        System.out.println(ans);
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
