package problems.hackerrank;

import java.util.List;

public class AmznDemo1 {
    public static List<List<String>> searchSuggestions(List<String> repository, String customerQuery) {
        // Write your code here
        return null; //change this
    }

    class TrieNode {
        char ch;
        TrieNode[] next = new TrieNode[27];
        public TrieNode(char val) {
            ch = val;
        }
    }

    class Trie {
        TrieNode root = new TrieNode('$');
        int wordEndIdx = 26;

        public void add(String word) {
            word = word.toLowerCase();
            add(root, word, 0);
        }

        private void add(TrieNode node, String word, int index) {
            char ch = word.charAt(index);
            int idx = ch-97;
            if (node.next[idx] == null) {
                node.next[idx] = new TrieNode(ch);
            }
            if (index == word.length()-1) {
                node.next[wordEndIdx] = new TrieNode('*');
            } else {
                add(node.next[idx], word, index + 1);
            }
        }
    }
}
