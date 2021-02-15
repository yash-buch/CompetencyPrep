package utilities.datastructures;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class BincTrie {
    TrieNode head = new TrieNode('*');
    ArrayList<ArrayList<Character>> queryResult;

    public void insert(String str) {
        TrieNode traverse = head;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            int idx = c-'a';
            if (traverse.chars[idx] == null) {
                traverse.chars[idx] = new TrieNode(c);
            }
            traverse = traverse.chars[idx];
            if (i == str.length()-1) traverse.isWordEnding = true;
        }
    }

    public void queryPrefix(String str) {
        queryResult = new ArrayList<>();
        TrieNode traverse = head;
        ArrayList<Character> list = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            int idx = c - 'a';
            if (traverse == null || traverse.chars[idx] == null) {
                return;
            }
            traverse = traverse.chars[idx];
            if (i < str.length()-1) list.add(traverse.id);
        }
        DFS(traverse, list);
    }

    private void DFS(TrieNode node, ArrayList<Character> list) {
        ArrayList<Character> _list = new ArrayList<>(list);
        _list.add(node.id);
        if (node.isWordEnding) queryResult.add(_list);
        for (int i = 0; i < 26; i++) {
            if (node.chars[i] != null) {
                DFS(node.chars[i], _list);
            }
        }
    }

    static class TrieNode {
        boolean isWordEnding;
        char id;
        TrieNode[] chars = new TrieNode[26];
        public TrieNode(char c) {
            id = c;
        }
    }

    @Test
    public void testBincTrie() {
        BincTrie obj = new BincTrie();
        obj.insert("abc");
        obj.insert("abcd");
        obj.insert("axyz");
        obj.queryPrefix("abc");
        for(List<Character> list : obj.queryResult) {
            for (char c : list) {
                System.out.print(c);
            }
            System.out.println();
        }
    }
}
