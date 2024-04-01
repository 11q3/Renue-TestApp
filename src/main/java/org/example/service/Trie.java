package org.example.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Trie {
    private final TrieNode root;

    public Trie() {
        root = new TrieNode(0);
    }

    public void insert(String key, int value) {
        TrieNode currentNode = root;
        for (char ch : key.toCharArray()) {
            if (!currentNode.children.containsKey(ch)) {
                currentNode.children.put(ch, new TrieNode(value));
            }
            currentNode = currentNode.children.get(ch);
        }
        currentNode.isEndOfWord = true;
    }

    public String search(String key) {
        TrieNode node = root;
        for(int i = 0; i < key.length(); i++) {
            char ch = key.charAt(i);
            if(node.children.containsKey(ch)) {
                node = node.children.get(ch);
            } else {
                return "";
            }
        }
        return String.valueOf(node.value);
    }

    public static class TrieNode {
        private final int value;
        boolean isEndOfWord;
        private final Map<Character, TrieNode> children;

        public TrieNode(int value) {
            this.value = value;
            this.children = new HashMap<>();
        }
    }
}