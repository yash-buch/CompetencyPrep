package problems.leetcode;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

public class TwoKeyKeyboard {
    static final int COPY = 0;
    static final int PASTE = 1;

    public int minSteps(int n) {
        if (n == 1) {
            return 0;
        }
        return bfs(n);
    }

    public int bfs(int n) {
        Queue<Node> q = new LinkedList<>();
        int depth = 0;
        Node first = new Node(COPY, 1);
        first.numCopied = 1;
        q.offer(first);
        while (!q.isEmpty()) {
            Queue<Node> aux = new LinkedList<>();
            depth++;
            while (!q.isEmpty()) {
                Node node = q.poll();
                if (node.as == n) {
                    return depth;
                }
                if (node.as == COPY) {
                    //paste
                    int as = node.as + node.numCopied;
                    if (as > n) {
                        continue;
                    }
                    if (as == n) {
                        return depth + 1;
                    }
                    Node _node = new Node(PASTE, as);
                    _node.numCopied = node.numCopied;
                    aux.offer(_node);
                } else {
                    int as = node.as;
                    //copy
                    Node _node = new Node(COPY, as);
                    _node.numCopied = as;
                    aux.offer(_node);
                    //paste
                    as += node.numCopied;
                    if (as > n) {
                        continue;
                    }
                    if (as == n) {
                        return depth + 1;
                    }
                    _node = new Node(PASTE, as);
                    _node.numCopied = node.numCopied;
                    aux.offer(_node);

                }
            }
            q = aux;
        }
        return depth;
    }

    class Node {
        int op;
        int as;
        int numCopied;

        Node(int _op, int _as) {
            op = _op;
            as = _as;
        }
    }

    @Test
    public void test() {
        TwoKeyKeyboard obj = new TwoKeyKeyboard();
        int ret = obj.minSteps(111);
        System.out.println("ret:"+ret);
    }
}
