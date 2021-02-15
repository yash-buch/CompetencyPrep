package problems.techiedelight;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

public class PrintBinTree {

    public void printTopToBottom(Node root) {
        ArrayDeque<ArrayDeque<Integer>> store = buildStore(root);
        while (!store.isEmpty()) {
            ArrayDeque<Integer> q = store.pollFirst();
            while(!q.isEmpty()) {
                System.out.print(q.pollLast()+",");
                if (!q.isEmpty())
                    System.out.print(q.pollFirst()+",");
            }
        }
    }

    public void printBottomToTop(Node root) {
        ArrayDeque<ArrayDeque<Integer>> store = buildStore(root);
        while (!store.isEmpty()) {
            ArrayDeque<Integer> q = store.pollLast();
            while(!q.isEmpty()) {
                System.out.print(q.pollLast()+",");
                if (!q.isEmpty())
                    System.out.print(q.pollFirst()+",");
            }
        }
    }

    private ArrayDeque<ArrayDeque<Integer>> buildStore(Node root) {
        ArrayDeque<ArrayDeque<Integer>> store = new ArrayDeque<>();
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        store.add(new ArrayDeque<>());
        int elemPoppedCurLevel = 0;
        int elemAddedCurLevel = 1;
        int elemAddedNxtLevel = 0;
        while(!q.isEmpty()) {
            Node node = q.remove();
            elemPoppedCurLevel++;
            store.peekLast().offerFirst(node.val);
            if (node.left != null) {
                q.add(node.left);
                elemAddedNxtLevel++;
            }
            if (node.right != null) {
                q.add(node.right);
                elemAddedNxtLevel++;
            }
            if (elemPoppedCurLevel == elemAddedCurLevel) {
                elemAddedCurLevel = elemAddedNxtLevel;
                elemAddedNxtLevel = 0;
                elemPoppedCurLevel = 0;
                if (!q.isEmpty())
                    store.add(new ArrayDeque<>());
            }
        }
        return store;
    }

    static class Node {
        int val;
        Node left;
        Node right;
        Node(int v) {
            val = v;
        }
    }

    @Test
    public void testPrintBinTree() {
        PrintBinTree obj = new PrintBinTree();
        Node node = new Node(1);
        node.left = new Node(2);
        node.right = new Node(3);
        node.left.left = new Node(4);
        node.left.right = new Node(5);
        node.right.left = new Node(6);
        node.right.right = new Node(7);
        node.left.left.left = new Node(8);
        node.left.left.right = new Node(9);
        node.left.right.left = new Node(10);
        node.left.right.right = new Node(11);
        node.right.left.left = new Node(12);
        node.right.left.right = new Node(13);
        node.right.right.left = new Node(14);
        node.right.right.right = new Node(15);
        obj.printBottomToTop(node);
        System.out.println();
        obj.printTopToBottom(node);
    }
}
