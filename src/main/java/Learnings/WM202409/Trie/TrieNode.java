package Learnings.WM202409.Trie;

public class TrieNode {


    TrieNode[] children;
    boolean isWord;


    TrieNode() {

        this.children = new TrieNode[26];
        this.isWord = false;
    }
}
