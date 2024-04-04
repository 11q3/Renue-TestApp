package org.example.util;

import java.util.*;

public class Trie {
    private static class TrieNode {
        boolean isEndOfWord;
        List<TrieNode> children;
        int codePoint;
        List<Integer> values;

        public TrieNode() {
            children = new ArrayList<>();
            values = new ArrayList<>();
            isEndOfWord = false;
            codePoint = -1;
        }

        public void addChild(int codePoint, TrieNode node) {
            node.codePoint = codePoint;
            children.add(node);
        }

        public TrieNode getChild(int codePoint) {
            for (TrieNode node : children) {
                if (node.codePoint == codePoint) {
                    return node;
                }
            }
            return null;
        }
    }

    private final TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String key, List<Integer> values) {
        TrieNode currentNode = root;
        for (int i = 0; i < key.length(); i++) {
            int codePoint = key.codePointAt(i);
            TrieNode childNode = currentNode.getChild(codePoint);
            if (childNode == null) {
                childNode = new TrieNode();
                currentNode.addChild(codePoint, childNode);
            }
            currentNode = childNode;
        }
        currentNode.values = values;
        currentNode.isEndOfWord = true;
    }

    public Collection<? extends List<Integer>> search(String key) {
        TrieNode currentNode = root;
        for (int i = 0; i < key.length(); i++) {
            int codePoint = key.codePointAt(i);
            TrieNode childNode = currentNode.getChild(codePoint);
            if (childNode == null) {
                return Collections.emptyList();
            }
            currentNode = childNode;
        }
        return getValues(currentNode);
    }

    private Collection<? extends List<Integer>> getValues(TrieNode node) {
        List<List<Integer>> values = new LinkedList<>();
        if (node.isEndOfWord) {
            values.add(node.values);
        }
        for (TrieNode child : node.children) {
            values.addAll(getValues(child));
        }
        return values;
    }
}