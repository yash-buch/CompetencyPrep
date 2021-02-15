package problems.ctci;

import org.junit.Test;

public class CommonAncestor {
    char result = '-';

    public char commonAncestor(Node root, char a, char b) {
        DFS(root, a, b);
        return result;
    }

    private boolean[] DFS(Node current, char a, char b) {
        if (current == null) return null;
        char c = current.val;
        boolean[] found = new boolean[2];
        if (c == a) found[0] = true;
        if (c == b) found[1] = true;
        boolean[] res = DFS(current.left, a, b);
        if (res != null) {
            found[0] = found[0] || res[0];
            found[1] = found[1] || res[1];
        }
        if (found[0] && found[1]) {
            if (result == '-')
                result = c;
            return found;
        }
        res = DFS(current.right, a, b);
        if (res != null) {
            found[0] = found[0] || res[0];
            found[1] = found[1] || res[1];
        }
        if (found[0] && found[1]) {
            if (result == '-')
                result = c;
        }
        return found;
    }


    static class Node {
        char val;
        Node left;
        Node right;

        public Node(char v) {
            val = v;
        }
    }


    @Test
    public void testCommonAncestor() {
        CommonAncestor obj = new CommonAncestor();

        Node a = new Node('a');
        Node b = new Node('b');
        Node c = new Node('c');
        Node d = new Node('d');
        Node e = new Node('e');
        Node f = new Node('f');

        a.left = b;
        b.left = c;
        b.right = d;
        d.left = e;
        a.right = f;

        char res = obj.commonAncestor(a, 'c', 'e');

        assert res == 'b';
    }
}
