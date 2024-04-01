package org.example.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Trie {
    private final TrieNode root;

    public Trie() {
        root = new TrieNode(null); //TODO check what is better here, Integer and null value or int and -1 value
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

    public List<String> search(String key) {
        TrieNode node = root;
        for (int i = 0; i < key.length(); i++) {
            char ch = key.charAt(i);
            if (node.children.containsKey(ch)) {
                node = node.children.get(ch);
            } else {
                return new ArrayList<>();
            }
        }
        return getValues(node);
    }

    private List<String> getValues(TrieNode node) {
        List<String> values = new ArrayList<>();
        if (node.isEndOfWord) {
            values.add(String.valueOf(node.value));
        }
        for (TrieNode child : node.children.values()) {
            values.addAll(getValues(child));
        }
        return values;
    }

    public static class TrieNode {
        private final Integer value;
        boolean isEndOfWord;
        private final Map<Character, TrieNode> children;

        public TrieNode(Integer value) {
            this.value = value;
            this.children = new HashMap<>();
        }
    }
}