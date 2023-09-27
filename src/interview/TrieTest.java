package interview;

import java.util.List;

public class TrieTest {
    Node root = new Node('*');

    public void insert(String str) {

    }

    public List<String> queryPrefix(String prefix) {
        return null;
    }

    public void remove(String prefix) {

    }

    class Node {
        Node[] ar = new Node[26];
        char ch;
        boolean isWordEnd;
        Node(char c) {
            ch = c;
        }
    }
}
