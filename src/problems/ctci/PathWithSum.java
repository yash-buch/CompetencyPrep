package problems.ctci;

import org.junit.Test;

public class PathWithSum {
    public int findPathWithSum(Node root, int sum) {
        return DFS(sum, 0, root)/2;
    }

    private int DFS(int target, int sum, Node current) {
        int _count = 0;
        //default case
        if (current == null) return _count;
        //case: self == target
        if (current.val == target) _count++;
        //case: end here
        if (current.val + sum == target) _count++;
        //case: begin here
        _count += DFS(target, current.val, current.left);
        _count += DFS(target, current.val, current.right);
        //case: add current and pass on
        _count += DFS(target, sum + current.val, current.left);
        _count += DFS(target, sum + current.val, current.right);
        return _count;
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
    public void testPathWithSum() {
        PathWithSum obj = new PathWithSum();
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

        int count = obj.findPathWithSum(one, 3);

        assert 4 == count;
    }
}
