package Learnings.WM202409.Trie;

import java.util.ArrayList;
import java.util.List;

public class Trie {


    TrieNode root;


    Trie() {

        root = new TrieNode();
    }


    public void insert(String s) {

        if (s == null || s.length() == 0) {
            return;
        }


        s = s.toLowerCase();
        TrieNode current = root;
        for (int i = 0; i < s.length(); i++) {

            char c = s.charAt(i);
            int index = c - 'a';


            if (current.children[index] == null) {

                TrieNode node = new TrieNode();
                current.children[index] = node;
                current = node;
            } else {

                current = current.children[index];
            }
        }

        current.isWord = true;

    }


    public boolean search(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }

        s = s.toLowerCase();
        TrieNode current = root;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int index = c - 'a';

            if (current.children[index] == null) {
                return false;
            } else {
                current = current.children[index];
            }
        }

        return current.isWord;
    }


    public boolean startsWith(String prefix) {
        if (prefix == null || prefix.length() == 0) {
            return false;
        }

        prefix = prefix.toLowerCase();
        TrieNode current = root;

        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            int index = c - 'a';

            if (current.children[index] == null) {
                return false;
            } else {
                current = current.children[index];
            }
        }

        return true;
    }


    public List<String> autocomplete(String prefix) {

        List<String> results = new ArrayList<>();

        if (prefix == null || prefix.length() == 0) return results;


        prefix = prefix.toLowerCase();

        TrieNode current = root;

        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            int index = c - 'a';

            if (current.children[index] == null) {

                return results;
            } else {

                current = current.children[index];
            }
        }


        dfs(current, new StringBuilder(prefix), results);

        return results;
    }

    private void dfs(TrieNode node, StringBuilder sb, List<String> results) {

        if (node.isWord) {

            results.add(sb.toString());
        }


        for (char c = 'a'; c <= 'z'; c++) {

            int index = c - 'a';

            if (node.children[index] != null) {

                sb.append(c);
                dfs(node.children[index], sb, results);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }


    public static void main(String[] args) {


        Trie trie = new Trie();
        trie.insert("apple");
        trie.insert("applepie");
        trie.insert("happy");
        trie.insert("hapless");
        trie.insert("haphazard");
        trie.insert("hazard");
        boolean search = trie.search("apple");
        System.out.println(search);
        search = trie.search("app");
        System.out.println(search);
        search = trie.startsWith("app");
        System.out.println(search);

        trie.insert("to");
        trie.insert("top");
        trie.insert("topo");


        System.out.println(trie.autocomplete("to"));
    }
}
