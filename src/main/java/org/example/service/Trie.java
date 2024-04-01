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
        TrieNode current = root;
        for (char ch : key.toCharArray()) {
            if (!current.children.containsKey(ch)) {
                current.children.put(ch, new TrieNode(value));
            }
            current = current.children.get(ch);
        }
    }

    /*public int search(String prefix) {
        TrieNode node = root;
        for (char c : prefix.toCharArray()) {
            if (!node.children.containsKey(c)) {
                return -1;
            }
            node = node.children.get(c);
        }
        return node.value;
    }*/

    public static class TrieNode {
        private final int value;
        private final Map<Character, TrieNode> children;

        public TrieNode(int value) {
            this.value = value;
            this.children = new HashMap<>();
        }
    }
}