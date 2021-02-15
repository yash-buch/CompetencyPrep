package problems.ctci;

import org.junit.Test;

import java.util.*;

public class LevelOrderTraversal {

    public ArrayList<ArrayList<Node>> levelOrderTraversal(Node root) {
        Set<Node> nodes = new HashSet<>();
        ArrayList<ArrayList<Node>> result = new ArrayList<>();
        DFS(root, nodes);
        BFS(root, nodes, result);

        return result;
    }

    private void BFS(Node root, Set<Node> set, ArrayList<ArrayList<Node>> result) {
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        ArrayList<Node> list = null;
        while(!q.isEmpty()) {
            Node n = q.remove();
            if (set.contains(n)) {
                if (list != null) result.add(list);
                list = new ArrayList<>();
            }
            list.add(n);
            for (Node child : n.children) {
                if (child != null) q.add(child);
            }
        }
        result.add(list);
    }

    private void DFS(Node parent, Set<Node> nodes) {
        nodes.add(parent);
        for (Node child : parent.children) {
            if (child != null) {
                DFS(child, nodes);
                break;
            }
        }
    }

    static class Node {
        int val;
        Node[] children;

        public Node(int v, Node[] nodes) {
            val = v;
            children = nodes;
        }
    }


    @Test
    public void testLevelOrderTraversal() {
        LevelOrderTraversal obj = new LevelOrderTraversal();
        Node eight = new Node(8, new Node[1]);
        Node seven = new Node(7, new Node[1]);
        Node six = new Node(6, new Node[1]);
        Node five = new Node(5, new Node[1]);
        Node four = new Node(4, new Node[]{null, eight});
        Node three = new Node(3, new Node[]{five, six, seven});
        Node two = new Node(2, new Node[]{four});
        Node one = new Node(1, new Node[]{two, three});

        ArrayList<ArrayList<Node>> list = obj.levelOrderTraversal(one);

        for(ArrayList<Node> l: list) {
            for(Node node : l) {
                System.out.print(node.val+",");
            }
            System.out.println();
        }
    }
}
