package Atlassian.Karat;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class WordSearch2 {


    class TrieNode {

        TrieNode[] children;
        boolean isEndOfWord;

        TrieNode() {

            this.children = new TrieNode[26];
            this.isEndOfWord = false;
        }
    }

    TrieNode root = new TrieNode();

    public List<String> findWords(char[][] board, String[] words) {

        Set<String> result = new HashSet<>();

        boolean[][] visited = new boolean[board.length][board[0].length];

        populateTrie(words);

        for (int i = 0; i < board.length; i++) {

            for (int j = 0; j < board[i].length; j++) {

                int index = board[i][j] - 'a';
                if (root.children[index] != null) {

                    find(board, i, j, root, "", result, visited);
                }
            }
        }

        return new LinkedList<>(result);

    }

    public void find(char[][] board, int i, int j, TrieNode node, String word, Set<String> result,
                     boolean[][] visited) {

        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || visited[i][j]
                || node.children[board[i][j] - 'a'] == null) {
            return;
        }

        visited[i][j] = true;

        node = node.children[board[i][j] - 'a'];

        if (node.isEndOfWord) {

            result.add(word + board[i][j]);
        }

        find(board, i - 1, j, node, word + board[i][j], result, visited);
        find(board, i + 1, j, node, word + board[i][j], result, visited);
        find(board, i, j - 1, node, word + board[i][j], result, visited);
        find(board, i, j + 1, node, word + board[i][j], result, visited);


        visited[i][j] = false;

    }

    public void populateTrie(String[] words) {

        for (String word : words) {
            TrieNode current = root;
            for (int i = 0; i < word.length(); i++) {

                char c = word.charAt(i);
                int index = c - 'a';

                if (current.children[index] == null) {

                    TrieNode node = new TrieNode();
                    current.children[index] = node;
                    current = node;
                } else {

                    current = current.children[index];
                }
            }

            current.isEndOfWord = true;
        }
    }


    public static void main(String[] args) {


        char[][] board = {
                {'o', 'a', 'a', 'n'},
                {'e', 't', 'a', 'e'},
                {'i', 'h', 'k', 'r'},
                {'i', 'f', 'l', 'v'}
        };


        String[] words = {"oath", "pea", "eat", "rain"};

        WordSearch2 wordSearch2 = new WordSearch2();

        for (String wordFoundInBoard : wordSearch2.findWords(board, words)) {

            System.out.print(wordFoundInBoard + " ");

        }

    }
}
